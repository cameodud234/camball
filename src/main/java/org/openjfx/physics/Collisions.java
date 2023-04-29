package org.openjfx.physics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openjfx.objects.Ball;

public class Collisions {
	
	private List<Ball> balls;
	
	private Map<Ball, Ball> collidingBalls;
	
	public Collisions(List<Ball> balls) {
		this.balls = new ArrayList<Ball>(balls);
		collidingBalls = new HashMap<Ball, Ball>();
	}
	
	public void updateBallStates(List<Ball> balls) {
		// doesn't work if balls list changes in size
		for(int i = 0 ; i < this.balls.size(); i++) {
			Ball thisBall = this.balls.get(i);
			Ball parameterBall = balls.get(i);
			
			thisBall.setPosition(parameterBall.getPosition());
			
		}
	}
	
	public void calculateCollisions() {
		
		Map<Ball, Ball> temporaryCollidingBalls = new HashMap<Ball, Ball>();
		
		List<Ball> ballList = new ArrayList<>();
		
		for(Ball ball: balls) {
			ballList.add(ball);
		}
		
		Collections.sort(ballList, new BallPositionComparatorX());
		
		if(ballList.size() < 2) {
			collidingBalls.clear();
			return;
		}
		
		for(int i = 1; i < ballList.size(); i++) {
			if(isColliding(ballList.get(i-1), ballList.get(i))) {
				temporaryCollidingBalls.put(ballList.get(i-1), ballList.get(i));
				temporaryCollidingBalls.put(ballList.get(i), ballList.get(i-1));
			}
		}
		
		if(temporaryCollidingBalls.isEmpty()) {
			collidingBalls.clear();
			return;
		}
		
		List<Ball> prunedBallList = new ArrayList<>();
		
		for(Ball ball: ballList) {
			if(temporaryCollidingBalls.containsKey(ball)) {
				prunedBallList.add(ball);
			}
		}
		
		
		Collections.sort(prunedBallList, new BallPositionComparatorY());
		
		if(prunedBallList.size() < 2) {
			collidingBalls.clear();
			return;
		}
		
		for(int i = 1; i < prunedBallList.size(); i++) {
			if(isColliding(prunedBallList.get(i-1), prunedBallList.get(i))) {
				collidingBalls.put(prunedBallList.get(i-1), prunedBallList.get(i));
				collidingBalls.put(prunedBallList.get(i), prunedBallList.get(i-1));
			}
		}
	}
	
	public void updateCollidingBalls() {
		calculateCollisions();
	}
	 
	public boolean containsCollisions() {
		return collidingBalls.size() != 0;
	}
	
	public List<Ball> getBalls() {
		return new ArrayList<Ball>(balls);
	}
	
	public Map<Ball, Ball> getCollidingBalls() {
		return new HashMap<Ball,Ball>(collidingBalls);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		
		Collisions collisions = (Collisions) o;
		
		if(balls.size() != collisions.getBalls().size()) {
			return false;
		}
		
		if(collidingBalls.size() != collisions.getCollidingBalls().size()) {
			return false;
		}
		
		return balls.equals(collisions.getBalls()) &&
				collidingBalls.equals(collisions.getCollidingBalls());
	}
	
	
	private class BallPositionComparatorX implements Comparator<Ball> {
		@Override
		public int compare(Ball o1, Ball o2) {
			return Double.compare(o1.getCenterX(), o2.getCenterX());
		}
	}
	
	private class BallPositionComparatorY implements Comparator<Ball> {
		@Override
		public int compare(Ball o1, Ball o2) {
			return Double.compare(o1.getCenterY(), o2.getCenterY());
		}
	}
	
	private boolean isColliding(Ball o1, Ball o2) {
		
		if(Double.compare(distance(o1, o2), o1.getRadius() + o2.getRadius()) == 1) {
			return false;
		}
		
		return true;
	}
	
	private double distance(Ball o1, Ball o2) {
		double dx = o1.getCenterX() - o2.getCenterX();
		double dy = o1.getCenterY() - o2.getCenterY();
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}

}
