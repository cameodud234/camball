package org.openjfx.simobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openjfx.objects.Ball;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Position;
import org.openjfx.physics.Velocity;

import javafx.scene.paint.Color;

public class TestBall {
	
	Velocity velocity = new Velocity(-23, 44);
	Position position = new Position(250, 540);
	double radius = 20;
	Color color = Color.ALICEBLUE;
	double framerate = 120;
	double screenWidth = 500;
	double screenHeight = 700;
	double pixelToMeter = 10;
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
		assertEquals(ball.getVelocity(), newVelocity);
	}
	
	@Test
	public void testBallSetPosition() {
		Position newPosition = new Position(240, 300);
		ball.setPosition(position);
		assertEquals(ball.getPosition(), newPosition);
	}

}
