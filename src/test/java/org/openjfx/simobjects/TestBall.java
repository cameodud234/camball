package org.openjfx.simobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
	
	Ball ball = new Ball(
			velocity, positionX, 
			positionY, radius, 
			color
	);
	
//	@Test
//	public void testBallConstructorVelocity() {
//		assertEquals(ball.getVelocity(), velocity);
//	}
	
	@Test
	public void testBallConstructorPositionX() {
		assertEquals(ball.getPositionX(), positionX);
	}
	
	@Test
	public void testBallConstructorPositionY() {
		assertEquals(ball.getPositionY(), positionY);
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

}
