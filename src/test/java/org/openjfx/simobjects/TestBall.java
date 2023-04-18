package org.openjfx.simobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
	double radius = 0.1;
	Color color = Color.ALICEBLUE;
	double framerate = 120;
	double screenWidth = 500;
	double screenHeight = 700;
	double pixelToMeter = 20;
	Physics physics = new Physics(framerate, pixelToMeter, screenWidth, screenHeight);
	Ball ball = new Ball(velocity, position, radius, color, physics);
	
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
		Ball newBall = new Ball(newVelocity, position, radius, color, physics);
		assertEquals(newBall.getVelocity(), newVelocity);
	}
	
	@Test
	public void testBallSetPosition() {
		Position newPosition = new Position(240, 300);
		Ball newBall = new Ball(velocity, newPosition, radius, color, physics);
		assertEquals(newBall.getPosition(), newPosition);
	}
	
	@ParameterizedTest
	@MethodSource("TestBallMoveTestCasesArgs")
	public void testBallMove(Velocity velocity, Position position, Position finalPosition) {
		Ball newBall = new Ball(velocity, position, radius, color, physics);
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
