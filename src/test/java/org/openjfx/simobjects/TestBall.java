package org.openjfx.simobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openjfx.physics.Velocity;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class TestBall {
	
	Velocity velocity = new Velocity(1,2);
	double centerX = 100;
	double centerY = 40;
	double radius = 10;
	Paint color = Color.BLUE;
	
	Ball ball = new Ball(
			velocity, centerX, 
			centerY, radius, 
			color
	);
	
	@Test
	public void testBallConstructorVelocity() {
		assertEquals(ball.getVelocity(), velocity);
	}
	
	@Test
	public void testBallConstructorCenterX() {
		assertEquals(ball.getCenterX(), centerX);
	}
	
	@Test
	public void testBallConstructorCenterY() {
		assertEquals(ball.getCenterY(), centerY);
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
		Ball newBall = new Ball(ball);
	}
	
	@Test 
	public void testBallSetVelocity() {
		Velocity velocity = new Velocity(5,6);
		ball.setVelocity(velocity);
		assertTrue(ball.getVelocity().equals(velocity));
	}
	
	@Test
	public void testBallSetRadius() {
		double radius = 15;
		ball.setRadius(radius);
		assertEquals(ball.getRadius(), radius);
		assertEquals(ball.getCircle().getRadius(), radius);
	}
	
	@Test
	public void testBallSetCircle() {
		double centerX = 50;
		double centerY = 20;
		double radius = 8;
		Color color = Color.AQUA;
		Circle circle = new Circle(centerX, centerY, radius, color);
		ball.setCircle(circle);
		assertEquals(ball.getCenterX(), centerX);
		assertEquals(ball.getCenterY(), centerY);
		assertEquals(ball.getRadius(), radius);
		assertEquals(ball.getColor(), color);
		assertEquals(ball.getCircle(), circle);
	}

}
