package org.openjfx.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.jblas.DoubleMatrix;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class TestPhysics extends TestPhysicsTestCases {
	
	double framerate = 120;
	
	double pixelToMeter = 10;
	
	Physics physics = new Physics(framerate, pixelToMeter);
	
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
		Physics newPhysics = new Physics(framerate, pixelToMeter);
		assertEquals(physics, newPhysics);
	}

}