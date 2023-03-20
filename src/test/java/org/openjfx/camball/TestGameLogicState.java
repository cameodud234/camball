package org.openjfx.camball;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Position;
import org.openjfx.physics.Velocity;
import org.openjfx.simobjects.Ball;

import javafx.scene.paint.Color;

public class TestGameLogicState {
	
	double positionX = 400;
	double positionY = 300;
	double velocityX = 8;
	double velocityY = 10;
	Color color = Color.ALICEBLUE;
	double screenWidthX = 400;
	double screenWidthY = 300;
	double radius = 30;
	Velocity velocity = new Velocity(velocityX, velocityY);
	Position position = new Position(positionX, positionY);
	double framerate = 60;
	double pixelToMeter = 10;
	Physics physics = new Physics(framerate, pixelToMeter, screenWidthX, screenWidthY);
	
	Ball ball = new Ball(velocity, physics, position, screenWidthX, screenWidthY, radius, color);
	
	@Test
	void TestGameLogicGetPosition() {
		assertEquals(ball.getPosition(), position);
	}
	
	@Test
	void TestGameLogicGetVelocity() {
		assertEquals(ball.getVelocity(), velocity);
	}
	

}