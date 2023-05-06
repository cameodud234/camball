package org.openjfx.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openjfx.objects.BallState;

import javafx.scene.paint.Color;

public class TestBallState {

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
	BallState ballState = new BallState(velocity, position, radius, color, mass, physics);
	
	@Test
	public void testBallStateConstructorPosition() {
		assertEquals(ballState.getPosition(), position);
	}
	
	@Test
	public void testBallStateConstructorVelocity() {
		assertEquals(ballState.getVelocity(), velocity);
	}
	
	@Test
	public void testBallStateConstructorMass() {
		assertEquals(ballState.getMass(), mass);
	}
	
	@Test
	public void testBallStateConstructorRadius() {
		assertEquals(ballState.getRadius(), radius);
	}
	
	@Test
	public void testBallStateConstructorColor() {
		assertEquals(ballState.getColor(), color);
	}
	
	@Test
	public void testBallStateConstructorPhysics() {
		assertEquals(ballState.getPhysics(), physics);
	}
	
}
