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
		double speedX = 9;
		double speedY = 33;
		ball.setVelocity(speedX, speedY);
		assertEquals(ball.getVelocity().getSpeedX(), speedX);
	}
	
	@Test
	public void testBallSetColor() {
		Paint color = Color.ALICEBLUE;
		ball.setColor(color);
		assertEquals(ball.getColor(), color);
		assertEquals(ball.getCircle().getFill(), color);
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
	
	@Test
	public void testBallMove() {
		double deltaX = 29;
		double deltaY = -3.6;
		double beforeCenterX = ball.getCenterX();
		double beforeCenterY = ball.getCenterY();
		ball.move(deltaX, deltaY);
		assertEquals(ball.getCenterX(), beforeCenterX + deltaX);
		assertEquals(ball.getCenterY(), beforeCenterY + deltaY);
	}

}
