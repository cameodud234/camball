package org.openjfx.physics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jblas.DoubleMatrix;
import org.openjfx.objects.Ball;
import org.openjfx.objects.BallState;

public class Collisions {
	
	public static List<Velocity> calculateCollisionVelocities(BallState o1, BallState o2) throws VelocityException { 
		DoubleMatrix v1_before = o1.getVelocity().getVelocity();
		DoubleMatrix v2_before = o2.getVelocity().getVelocity();
		
		DoubleMatrix position1 = o1.getPosition().getPosition();
		DoubleMatrix position2 = o2.getPosition().getPosition();
		
		DoubleMatrix positionVector = position2.sub(position1);
		DoubleMatrix velocityVector = v2_before.sub(v1_before);
		
		double epsilon = Math.pow(10, -4);
		
		DoubleMatrix v1_afterFirstTerm = v1_before.mul( ((o1.getMass() - o2.getMass()) / (o1.getMass() + o2.getMass())) );
		
		DoubleMatrix v1_afterSecondTerm = v2_before.mul( 2 * o2.getMass() / (o1.getMass() + o2.getMass()) );
		
		DoubleMatrix v2_afterFirstTerm = v1_before.mul( 2 * o1.getMass() / (o1.getMass() + o2.getMass()) );
		DoubleMatrix v2_afterSecondTerm = v2_before.mul( (o2.getMass() - o1.getMass()) / (o1.getMass() + o2.getMass()) );
		
		DoubleMatrix v1_after = v1_afterFirstTerm.add(v1_afterSecondTerm);
		DoubleMatrix v2_after = v2_afterFirstTerm.add(v2_afterSecondTerm);
		
		
		double dot_position_velocity = Math.abs(positionVector.dot(velocityVector));
		double magnitude_position_velocity = positionVector.norm2() * velocityVector.norm2();
		
		if(Double.compare(Math.abs(dot_position_velocity - magnitude_position_velocity), epsilon) > 0) {
			Logger log = LogManager.getLogger(Collisions.class);
			log.info("Difference in collision detected.");
			double radianVector = Math.acos(dot_position_velocity / magnitude_position_velocity);
//			double angleVector = Math.toDegrees(radianVector);
		
			DoubleMatrix rotationMatrix = new DoubleMatrix(
					new double[][] {
						{ Math.cos(radianVector), -Math.sin(radianVector) },
						{ Math.sin(radianVector), Math.cos(radianVector) }
					}
			);
			
			v1_after = rotationMatrix.mmul(v1_after);
			v2_after = rotationMatrix.mmul(v2_after);
			
		}
		
		Velocity velocity1 = new Velocity(v1_after);
		Velocity velocity2 = new Velocity(v2_after);
		
		if(Double.compare(Math.abs(velocity1.getX()), epsilon) < 0) {
			velocity1.setX(0);
		}
		if(Double.compare(Math.abs(velocity1.getY()), epsilon) < 0) {
			velocity1.setY(0);
		}
		if(Double.compare(Math.abs(velocity2.getX()), epsilon) < 0) {
			velocity2.setX(0);
		}
		if(Double.compare(Math.abs(velocity2.getY()), epsilon) < 0) {
			velocity2.setY(0);
		}
		
		
		List<Velocity> velocities = new ArrayList<>();
		
		velocities.add(velocity1);
		velocities.add(velocity2);
		
		return velocities;
		
	}
	
	public static Map<BallState, BallState> calculateCollisions(List<BallState> ballStates) {
		
		Map<BallState, BallState> collidingBallStates = new HashMap<BallState, BallState>();
		
		List<BallState> ballStateList = new ArrayList<>();
		
		for(BallState ballState: ballStates) {
			ballStateList.add(ballState);
		}
		
		Collections.sort(ballStateList, new BallPositionComparator('X'));
		
		if(ballStateList.size() < 2) {
			return collidingBallStates;
		}
		
		for(int i = 1; i < ballStateList.size(); i++) {
			if(isColliding(ballStateList.get(i-1), ballStateList.get(i))) {
				collidingBallStates.put(ballStateList.get(i-1), ballStateList.get(i));
				collidingBallStates.put(ballStateList.get(i), ballStateList.get(i-1));
			}
		}
		
		if(collidingBallStates.isEmpty()) {
			return collidingBallStates;
		}
		
		List<BallState> prunedBallStateList = new ArrayList<>();
		
		for(BallState ballState: ballStateList) {
			if(collidingBallStates.containsKey(ballState)) {
				prunedBallStateList.add(ballState);
			}
		}
		
		
		Collections.sort(prunedBallStateList, new BallPositionComparator('Y'));
	
		collidingBallStates.clear();
		
		if(prunedBallStateList.size() < 2) {
			return collidingBallStates;
		}
		
		for(int i = 1; i < prunedBallStateList.size(); i++) {
			if(isColliding(prunedBallStateList.get(i-1), prunedBallStateList.get(i))) {
				collidingBallStates.put(prunedBallStateList.get(i-1), prunedBallStateList.get(i));
				collidingBallStates.put(prunedBallStateList.get(i), prunedBallStateList.get(i-1));
			}
		}
		
		return collidingBallStates;
		
	}
	
	private static class BallPositionComparator implements Comparator<BallState> {
		
		public char axis;
		Logger log = LogManager.getLogger(BallPositionComparator.class);
		
		public BallPositionComparator(char axis) {
			try {
				if(axis == 'X' || axis == 'Y') {
					this.axis = axis;
					return;
				}
				throw new IllegalArgumentException("Axis must be 'X' or 'Y'");
			}
			catch (IllegalArgumentException e) {
				log.error(e.getCause() + " : " + e.getMessage());
			}
			catch (Exception e) { 
				log.error(e.getCause() + " : " + e.getMessage());
			}
		}
		
		@Override
		public int compare(BallState o1, BallState o2) {
			if(axis == 'X') {
				return Double.compare(o1.getPosition().getX(), o2.getPosition().getX());
			}
			return Double.compare(o1.getPosition().getY(), o2.getPosition().getY());
		}
	}

	private static boolean isColliding(BallState o1, BallState o2) {
		
		if(Double.compare(distance(o1, o2), o1.getRadius() + o2.getRadius()) == 1) {
			return false;
		}
		
		// Section below handles case where we have 
		// a "collision" where the balls are moving away from 
		// each other but have not had enough time to get out 
		// of the collision detection zone.
		
		DoubleMatrix position1 = o1.getPosition().getPosition();
		DoubleMatrix position2 = o2.getPosition().getPosition();
		
		DoubleMatrix velocity1 = o1.getVelocity().getVelocity();
		DoubleMatrix velocity2 = o2.getVelocity().getVelocity();
		
		DoubleMatrix relativePosition = position2.sub(position1);
		DoubleMatrix relativeVelocity = velocity2.sub(velocity1);
		
		double orientation = relativePosition.dot(relativeVelocity);
		
		if(orientation > 0) {
			return false;
		}
		
		return true;
	}
	
	private static double distance(BallState o1, BallState o2) {
		double dx = o1.getPosition().getX() - o2.getPosition().getX();
		double dy = o1.getPosition().getY() - o2.getPosition().getY();
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}

}
