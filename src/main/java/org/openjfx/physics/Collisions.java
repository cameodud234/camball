package org.openjfx.physics;

import java.util.ArrayList;
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
	 
	public boolean containsCollisions() {
		return false;
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
//		if(collidingBalls.size() != collisions.getCollidingBalls().size()) {
//			return false;
//		}
		
		return balls.equals(collisions.getBalls());
//		return balls.equals(collisions.getBalls()) &&
//				collidingBalls.equals(collisions.getCollidingBalls());
	}

}
