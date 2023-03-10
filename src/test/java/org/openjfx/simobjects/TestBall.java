package org.openjfx.simobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Velocity;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class TestBall extends TestBallMoveTestCases {
	
	Velocity velocity = new Velocity(1,2);
	double centerX = 100;
	double centerY = 40;
	double boundX = 800;
	double boundY = 600;
	double radius = 10;
	Paint color = Color.BLUE;
	
	double framerate = 60;
	double pixelToMeter = 10;
	
	Physics physics = new Physics(framerate, pixelToMeter, boundX, boundY);
	
	Ball ball = new Ball(
			velocity, physics, 
			centerX, centerY,
			boundX, boundY,
			radius, color
	);
	
	@Test
	public void testBallConstructorVelocity() {
		assertEquals(ball.getVelocity(), velocity);
	 }
	
	@Test
	public void testBallConstructorPhysics() {
		assertEquals(ball.getPhysics(), physics);
	}

	
	@Test
	public void testBallConstructorRadius() {
		assertEquals(ball.getRadius(), radius);
	}
	
	@Test
	public void testBallConstructorColor() {
		assertEquals(ball.getColor(), color);
	}
	
	@Test
	public void testBallConstructorBall() {
//		Ball newBall = new Ball(ball);
	}
	
	@Test
	public void testBallConstructorNegativeRadiusException() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			double radius = -1;
			new Ball(velocity, physics, centerX, centerY, boundX, boundY, radius, color);
		});
		String expectedMessage = "Radius must be greater than zero.";
		assertEquals(expectedMessage, exception.getMessage());
	}
	
	@Test
	public void testBallConstructorSetNegativeRadiusException() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			double radius = -1;
			ball.setRadius(radius);
		});
		String expectedMessage = "Radius must be greater than zero.";
		assertEquals(expectedMessage, exception.getMessage());
	}
	
	@Test
	public void testBallSetCenterX() {
		double centerX = 12;
		ball.setCenterX(centerX);
		assertEquals(ball.getCenterX(), centerX);
		assertEquals(ball.getCircle().getCenterX(), centerX);
	}
	
	@Test
	public void testBallSetCenterY() {
		double centerY = 12;
		ball.setCenterY(centerY);
		assertEquals(ball.getCenterY(), centerY);
		assertEquals(ball.getCircle().getCenterY(), centerY);
	}
	
	@Test
	public void testBallSetRadius() {
		double radius = 15;
		ball.setRadius(radius);
		assertEquals(ball.getRadius(), radius);
		assertEquals(ball.getCircle().getRadius(), radius);
	}
	
	@Test 
	public void testBallSetVelocityWithVelocity() {
		Velocity velocity = new Velocity(5,6);
		ball.setVelocity(velocity);
		assertEquals(ball.getVelocity(), velocity);
	}
	
	@Test
	public void testBallSetVelocityWithSpeedXSpeedY() {
		double velocityX = 9;
		double velocityY = 33;
		ball.setVelocity(velocityX, velocityY);
		assertEquals(ball.getVelocity().getVelocityX(), velocityX);
		assertEquals(ball.getVelocity().getVelocityY(), velocityY);
	}
	
	@Test
	public void testBallSetColor() {
		Paint color = Color.ALICEBLUE;
		ball.setColor(color);
		assertEquals(ball.getColor(), color);
		assertEquals(ball.getCircle().getFill(), color);
	}
	
	@Test
	public void testBallEquals() {
		Ball newBall = new Ball(velocity, physics, centerX, centerY, boundX, boundY, radius, color);
		assertEquals(ball, newBall);
	}
	
	@Test
	public void testBallHashCode() {
		Velocity newVelocity = new Velocity(432, 539);
		Ball ball1 = new Ball(velocity, physics, centerX, centerY, boundX, boundY, radius, color);
		Ball ball2 = new Ball(velocity, physics, centerX, centerY, boundX, boundY, radius, color);
		Ball ball3 = new Ball(newVelocity, physics, centerX, centerY, boundX, boundY, radius, color);
		
		int hash1 = ball1.hashCode();
		int hash2 = ball2.hashCode();
		int hash3 = ball3.hashCode();
		
		assertEquals(hash1, hash2);
		assertNotEquals(hash2, hash3);
		
	}
	
	@ParameterizedTest
	@MethodSource("TestBallMoveTestCasesArgs")
	public void testBallMove(Velocity velocity, List<Double> centers, List<Double> actualCenterAfter) {
		double boundX = 500;
		double boundY = 600;
		double centerX = centers.get(0);
		double centerY = centers.get(1);
		Ball ball = new Ball(velocity, physics, centerX, centerY, boundX, boundY, radius, color);
		ball.move();
		double epsilonX = Math.abs(ball.getCenterX() - actualCenterAfter.get(0)) / actualCenterAfter.get(0);
		double epsilonY = Math.abs(ball.getCenterY() - actualCenterAfter.get(1)) / actualCenterAfter.get(1);
		assertTrue(epsilonX < 0.05);
		assertTrue(epsilonY < 0.05);
	}

}
