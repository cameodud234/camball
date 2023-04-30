package org.openjfx.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.openjfx.objects.Ball;

public class TestCollisions extends BallIntializers {
	
	Ball ball1 = new Ball(velocity1, position1, radius1, color1, mass1, physics);
	
	Ball ball2 = new Ball(velocity2, position2, radius2, color2, mass2, physics);
	
	Ball ball3 = new Ball(velocity3, position3, radius3, color3, mass3, physics);
	
	Ball ball4 = new Ball(velocity4, position4, radius4, color4, mass4, physics);
	
	List<Ball> balls = new ArrayList<Ball>(List.of(ball1, ball2, ball3, ball4));
	
//	Collisions collisions = new Collisions(balls);
//	
//	@Test
//	public void testCollisionsConstructorBall() {
//		assertEquals(collisions.getBalls(), balls);
//	}
//	
//	@Test
//	public void testCollisionsCalculateCollisions() {
//		Collisions newCollisions = new Collisions(balls);
//		Map<Ball, Ball> actualCollidingBalls = new HashMap<>();
//		actualCollidingBalls.put(ball1, ball4);
//		actualCollidingBalls.put(ball4, ball1);
//		newCollisions.calculateCollisions();
//		assertEquals(newCollisions.getCollidingBalls(), actualCollidingBalls);
//	}
//	
//	@Test
//	public void testCollisionsGetCollidingBalls() {
//		Map<Ball, Ball> collidingBalls = new HashMap<>();
//		assertEquals(collisions.getCollidingBalls(), collidingBalls);
//	}
//	
//	@Test
//	public void testCollisionsEquals() {
//		List<Ball> newBalls = new ArrayList<Ball>(List.of(ball1, ball2, ball3, ball4));
//		Collisions newCollisions1 = new Collisions(newBalls);
//		Collisions newCollisions2 = collisions;
//		
//		assertEquals(collisions, newCollisions1);
//		assertEquals(collisions, newCollisions2);
//	}
//	
// 	@Test
//	public void testCollisionsNotEquals() {
//		List<Ball> newBalls1 = new ArrayList<Ball>(List.of(ball1, ball2));
//		Collisions newCollisions1 = new Collisions(newBalls1);
//		
//		Ball newBall = new Ball(velocity1, position1, radius3, color2, mass1, physics);
//		List<Ball> newBalls2 = new ArrayList<Ball>(List.of(newBall, newBall, newBall, newBall));
//		Collisions newCollisions2 = new Collisions(newBalls2);
//		
//		assertNotEquals(collisions, newCollisions1);
//		assertNotEquals(collisions, newCollisions2);
//	}
//	
//	@Test
//	public void testCollisionsContainsCollision() {
//		Collisions newCollisions = new Collisions(balls);
//		newCollisions.calculateCollisions();
//	}
	
}