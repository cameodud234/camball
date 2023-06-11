package org.openjfx.simobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.jblas.DoubleMatrix;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openjfx.objects.Ball;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Position;
import org.openjfx.physics.Velocity;

import javafx.scene.paint.Color;

public class TestBall extends TestBallMoveTestCases {
	
	Velocity velocity = new Velocity(-23, 44);
	Position position = new Position(250, 540);
	double mass = 10;
	double radius = 0.1;
	Color color = Color.ALICEBLUE;
	double framerate = 120;
	double screenWidth = 500;
	double screenHeight = 700;
	double pixelToMeter = 20;
	Physics physics = new Physics(framerate, pixelToMeter, screenWidth, screenHeight);
	Ball ball = new Ball(position, radius, color, physics);
	
	@Test
	public void testBallConstructorPosition() {
		assertEquals(ball.getPosition(), position);
		assertEquals(ball.getCenterX(), position.getX());
		assertEquals(ball.getCenterY(), position.getY());
	}
	
	@Test
	public void testBallConstructorCenterXY() {
		double centerX = 34;
		double centerY = 235;
		Ball newBall = new Ball(centerX, centerY, centerY, color, physics);
		assertEquals(newBall.getCenterX(), centerX);
		assertEquals(newBall.getCenterY(), centerY);
	}
	 
	@Test
	public void testBallConstructorBoundWidth() {
		assertEquals(ball.getBoundWidth(), screenWidth);
	}
	
	@Test
	public void testBallConstructorBoundHeight() {
		assertEquals(ball.getBoundHeight(), screenHeight);
	}
	
	@Test
	public void testBallConstructorRadius() {
		assertEquals(ball.getRadius(), radius);
	}
	
	@Test 
	public void testBallConstructorColor() {
		assertEquals(ball.getFill(), color);
	}
	
	
	@Test
	public void testBallSetPosition() {
		Position newPosition = new Position(240, 300);
		Ball newBall = new Ball(newPosition, radius, color, physics);
		assertEquals(newBall.getPosition(), newPosition);
	}

	
	@Test
	public void testBallEquals() {
		Ball newBall = new Ball(position, radius, color, physics);
		assertEquals(ball, newBall);
	}
	
	@Test 
	public void testBallNotEquals() {
		Velocity newVelocity = new Velocity(34,65);
		Position newPosition = new Position(244,545);
		double newRadius = 45;
		Color newColor = Color.AZURE;
		double newMass = 34;
		Physics newPhysics = new Physics(60, pixelToMeter, 1000, 1000);
		
		Ball newBall = new Ball(newPosition, newRadius, newColor, newPhysics);
		assertNotEquals(ball, newBall);
	}

}
