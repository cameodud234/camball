package org.openjfx.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.jblas.DoubleMatrix;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class TestPhysics extends TestPhysicsTestCases {
	
	double framerate = 120;
	
	double pixelToMeter = 10;
	
	double screenWidthX = 400;
	double screenWidthY = 300;
	
	Physics physics = new Physics(framerate, pixelToMeter, screenWidthX, screenWidthY);
	
	@Test
	void TestPhysicsConstructorFramerate() {
		assertEquals(physics.getFramerate(), framerate);
	}
	
	@Test
	void TestPhysicsConstructorPixelToMeter() {
		assertEquals(physics.getPixelToMeter(), pixelToMeter);
	}
	
	@ParameterizedTest
	@MethodSource("TestPhysicsGetPixelMoveRateCases")
	void TestPhysicsGetPixelMoveRate(Velocity velocity, DoubleMatrix actualValue) {
		
		assertTrue(physics.getPixelMoveRate(velocity).compare(actualValue, Math.pow(10, -4.0)));
	}
	
	@Test
	void TestPhysicsEquals() {
		Physics newPhysics = new Physics(framerate, pixelToMeter, screenWidthX, screenWidthY);
		assertEquals(physics, newPhysics);
	}
	
	@Test
	void TestPhysicsHashCode() {
		Physics p1 = new Physics(framerate, pixelToMeter, screenWidthX, screenWidthY);
		Physics p2 = new Physics(framerate, pixelToMeter, screenWidthX, screenWidthY);
		double newFramerate = 50;
		Physics p3 = new Physics(newFramerate, pixelToMeter, screenWidthX, screenWidthY);
		
		assertEquals(p1, p2);
		assertNotEquals(p2,  p3);
	}

}