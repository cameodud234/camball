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
	Ball ball = new Ball(velocity, position, radius, color, mass, physics);
	
	@Test
	public void testBallConstructorVelocity() {
		assertEquals(ball.getVelocity(), velocity);
	}
	
	@Test
	public void testBallConstructorPosition() {
		assertEquals(ball.getPosition(), position);
		assertEquals(ball.getCenterX(), position.getX());
		assertEquals(ball.getCenterY(), position.getY());
	}
	
	@Test
	public void testBallConstructorMass() {
		assertEquals(ball.getMass(), mass);
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
	public void testBallSetVelocity() {
		Velocity newVelocity = new Velocity(45,87);
		Ball newBall = new Ball(newVelocity, position, radius, color, mass, physics);
		assertEquals(newBall.getVelocity(), newVelocity);
	}
	
	@Test
	public void testBallSetPosition() {
		Position newPosition = new Position(240, 300);
		Ball newBall = new Ball(velocity, newPosition, radius, color, mass, physics);
		assertEquals(newBall.getPosition(), newPosition);
	}
	
	@Test
	public void testBallSetMass() {
		double newMass = 50;
		Ball newBall = new Ball(velocity, position, radius, color, newMass, physics);
		assertEquals(newBall.getMass(), newMass);
	}
	
	@Test
	public void testBallEquals() {
		Ball newBall = new Ball(velocity, position, radius, color, mass, physics);
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
		
		Ball newBall = new Ball(newVelocity, newPosition, newRadius, newColor, newMass, newPhysics);
		assertNotEquals(ball, newBall);
	}
	
	@ParameterizedTest
	@MethodSource("TestBallMoveTestCasesArgs")
	public void testBallMove(Velocity velocity, Position position, Position finalPosition) {
		Ball newBall = new Ball(velocity, position, radius, color, mass, physics);
		DoubleMatrix pixelMoveRate = physics.getPixelMoveRate(velocity);
		newBall.move(pixelMoveRate);
		double decimalPlaces = 2;
		assertTrue(equalsWithTolerance(newBall.getPosition().getX(), finalPosition.getX(), decimalPlaces));
	    assertTrue(equalsWithTolerance(newBall.getPosition().getY(), finalPosition.getY(), decimalPlaces));
	}
	
	public static boolean equalsWithTolerance(double a, double b, double decimalPlaces) {
	    double tolerance = Math.pow(10, -decimalPlaces);
	    return Math.abs(a - b) < tolerance;
	}

}
