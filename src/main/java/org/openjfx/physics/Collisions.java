package org.openjfx.physics;

import java.util.ArrayList;
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
		
		DoubleMatrix v1_afterFirstTerm = v1_before.mul( ((o1.getMass() - o2.getMass()) / (o1.getMass() + o2.getMass())) );
		
		DoubleMatrix v1_afterSecondTerm = v2_before.mul( 2 * o2.getMass() / (o1.getMass() + o2.getMass()) );
		
		DoubleMatrix v2_afterFirstTerm = v1_before.mul( 2 * o1.getMass() / (o1.getMass() + o2.getMass()) );
		DoubleMatrix v2_afterSecondTerm = v2_before.mul( (o2.getMass() - o1.getMass()) / (o1.getMass() + o2.getMass()) );
		
		
		DoubleMatrix v1_after = v1_afterFirstTerm.add(v1_afterSecondTerm);
		DoubleMatrix v2_after = v2_afterFirstTerm.add(v2_afterSecondTerm);
		
		Velocity velocity1 = new Velocity(v1_after);
		Velocity velocity2 = new Velocity(v2_after);
		
		double epsilon = Math.pow(10, -4);
		
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
		
		return true;
	}
	
	private static double distance(BallState o1, BallState o2) {
		double dx = o1.getPosition().getX() - o2.getPosition().getX();
		double dy = o1.getPosition().getY() - o2.getPosition().getY();
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}

}
