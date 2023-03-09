package org.openjfx.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.jblas.DoubleMatrix;
import org.junit.jupiter.api.Test;

public class TestVelocity {
	
	double speedX = 5;
			
	double speedY = 2;
	
	Velocity velocity = new Velocity(speedX, speedY);
	
	@Test
	void TestVelocityConstructorSpeedX() {
		assertEquals(velocity.getVelocityX(), speedX);
	}
	
	@Test
	void TestVelocityConstructorSpeedY() {
		assertEquals(velocity.getVelocityY(), speedY);
	}
	
	@Test
	void TestVelocityConstructorDoubleMatrixVelocity() throws VelocityException {
		DoubleMatrix newVelocityArg = new DoubleMatrix(Arrays.asList(13.0, 64.0));
		Velocity newVelocity = new Velocity(newVelocityArg);
		assertEquals(newVelocity.getVelocityX(), newVelocityArg.get(0));
		assertEquals(newVelocity.getVelocityY(), newVelocityArg.get(1));
	}
	
	@Test
	void TestVelocityConstructorDoubleMatrixVelocityTranspose() throws VelocityException {
		DoubleMatrix newVelocityArg = new DoubleMatrix(Arrays.asList(13.0, 64.0));
		Velocity newVelocity = new Velocity(newVelocityArg);
		newVelocityArg = newVelocityArg.transpose();
		assertEquals(newVelocity.getVelocityX(), newVelocityArg.get(0));
		assertEquals(newVelocity.getVelocityY(), newVelocityArg.get(1));
	}
	
	@Test
	void TestVelocityConstructorDoubleMatrixVelocityThrows() throws VelocityException {
		Throwable exception = assertThrows(VelocityException.class, () -> {
			DoubleMatrix newVelocityArg = new DoubleMatrix(Arrays.asList(13.0, 64.0, 67.0));
			Velocity newVelocity = new Velocity(newVelocityArg);
		});
		String expectedMessage = "The dimension of velocity must be 2, not 3";
		String s = exception.getMessage();
		assertEquals(expectedMessage, exception.getMessage());
	}
	
	@Test
	void TestSetSpeedX() {
		double newSpeedX = 8;
		velocity.setVelocityX(newSpeedX);
		assertEquals(velocity.getVelocityX(), newSpeedX);
	}
	
	@Test
	void TestSetSpeedY() {
		double newSpeedY = 49;
		velocity.setVelocityY(newSpeedY);
		assertEquals(velocity.getVelocityY(), newSpeedY);
	}
	
	@Test 
	void TestGetAngleBetween() {
		Velocity newVelocity = new Velocity(4, 3);
		assertEquals(velocity.getAngleBetween(newVelocity), 0.26299473168091936);
	}
	
	@Test
	void TestEquals() {
		Velocity newVelocity = new Velocity(speedX, speedY);
		assertEquals(velocity, newVelocity);
	}
	
	@Test
	void TestNotEquals() {
		Velocity newVelocity = new Velocity(speedX, 34);
		assertNotEquals(velocity, newVelocity);
	}
	

}
