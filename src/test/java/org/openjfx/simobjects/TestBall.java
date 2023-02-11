package org.openjfx.simobjects;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openjfx.physics.Velocity;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class TestBall {
	
	Velocity velocity = new Velocity(1,2);
	double positionX = 100;
	double positionY = 40;
	double radius = 10;
	Paint color = Color.BLUE;
	final int windowSizeX = 1000;
	final int windowSizeY = 1000;
	
	Ball ball = new Ball(
			velocity, positionX, 
			positionY, radius, 
			color, windowSizeX, windowSizeY
	);
	
	@Test
	public void testBallConstructorVelocity() {
		assertTrue(ball.getVelocity().equals(velocity));
	}
	
	@Test
	public void testBallConstructorPositionX() {
		assertTrue(ball.getPositionX() == positionX);
	}
	
	@Test
	public void testBallConstructorPositionY() {
		assertTrue(ball.getPositionY() == positionY);
	}
	
	@Test
	public void testBallConstructorRadius() {
		assertTrue(ball.getRadius() == radius);
	}
	
	@Test 
	public void testBallConstructorColor() {
		assertTrue(ball.getColor().equals(color));
	}
	
	@Test
	public void testBallConstructorWindowSizeX() {
		assertTrue(ball.getWindowSizeX() == windowSizeX);
	}
	
	@Test 
	public void testBallConstructorWindowSizeY() {
		assertTrue(ball.getWindowSizeY() == windowSizeY);
	}
	
	@Test 
	public void testBallSetVelocity() {
		Velocity velocity = new Velocity(5,6);
		ball.setVelocity(velocity);
		assertTrue(ball.getVelocity().equals(velocity));
	}

}
