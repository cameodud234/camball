package org.openjfx.physics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jblas.DoubleMatrix;
import org.openjfx.objects.Ball;

public class Collisions {
	
	public static List<Velocity> calculateCollisionVelocities(Ball o1, Ball o2) throws VelocityException { 
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
		
		List<Velocity> velocities = new ArrayList<>();
		
		velocities.add(velocity1);
		velocities.add(velocity2);
		
		return velocities;
		
	}
	
	public static Map<Ball, Ball> calculateCollisions(List<Ball> balls) {
		
		Map<Ball, Ball> collidingBalls = new HashMap<Ball, Ball>();
		
		List<Ball> ballList = new ArrayList<>();
		
		for(Ball ball: balls) {
			ballList.add(ball);
		}
		
		Collections.sort(ballList, new BallPositionComparatorX());
		
		if(ballList.size() < 2) {
			return collidingBalls;
		}
		
		for(int i = 1; i < ballList.size(); i++) {
			if(isColliding(ballList.get(i-1), ballList.get(i))) {
				collidingBalls.put(ballList.get(i-1), ballList.get(i));
				collidingBalls.put(ballList.get(i), ballList.get(i-1));
			}
		}
		
		if(collidingBalls.isEmpty()) {
			return collidingBalls;
		}
		
		List<Ball> prunedBallList = new ArrayList<>();
		
		for(Ball ball: ballList) {
			if(collidingBalls.containsKey(ball)) {
				prunedBallList.add(ball);
			}
		}
		
		
		Collections.sort(prunedBallList, new BallPositionComparatorY());
	
		collidingBalls.clear();
		
		if(prunedBallList.size() < 2) {
			return collidingBalls;
		}
		
		for(int i = 1; i < prunedBallList.size(); i++) {
			if(isColliding(prunedBallList.get(i-1), prunedBallList.get(i))) {
				collidingBalls.put(prunedBallList.get(i-1), prunedBallList.get(i));
				collidingBalls.put(prunedBallList.get(i), prunedBallList.get(i-1));
			}
		}
		
		return collidingBalls;
		
	}
	
	
	private static class BallPositionComparatorX implements Comparator<Ball> {
		@Override
		public int compare(Ball o1, Ball o2) {
			return Double.compare(o1.getCenterX(), o2.getCenterX());
		}
	}
	
	private static class BallPositionComparatorY implements Comparator<Ball> {
		@Override
		public int compare(Ball o1, Ball o2) {
			return Double.compare(o1.getCenterY(), o2.getCenterY());
		}
	}
	
	private static boolean isColliding(Ball o1, Ball o2) {
		
		if(Double.compare(distance(o1, o2), o1.getRadius() + o2.getRadius()) == 1) {
			return false;
		}
		
		return true;
	}
	
	private static double distance(Ball o1, Ball o2) {
		double dx = o1.getCenterX() - o2.getCenterX();
		double dy = o1.getCenterY() - o2.getCenterY();
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}

}
