package org.openjfx.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.jblas.DoubleMatrix;
import org.junit.jupiter.api.Test;

public class TestVelocity {
	
	double velocityX = 5;
			
	double velocityY = 2;
	
	Velocity velocity = new Velocity(velocityX, velocityY);
	
	@Test
	void TestVelocityConstructorVelocityX() {
		assertEquals(velocity.getVelocityX(), velocityX);
	}
	
	@Test
	void TestVelocityConstructorVelocityY() {
		assertEquals(velocity.getVelocityY(), velocityY);
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
	void TestSetVelocityX() {
		double newVelocityX = 8;
		velocity.setVelocityX(newVelocityX);
		assertEquals(velocity.getVelocityX(), newVelocityX);
	}
	
	@Test
	void TestSetVelocityY() {
		double newVelocityY = 49;
		velocity.setVelocityY(newVelocityY);
		assertEquals(velocity.getVelocityY(), newVelocityY);
	}
	
	@Test 
	void TestGetAngleBetween() {
		Velocity newVelocity = new Velocity(4, 3);
		assertEquals(velocity.getAngleBetween(newVelocity), 0.26299473168091936);
	}
	
	@Test
	void TestHashCode() {
		Velocity v1 = new Velocity(87, 39);
		Velocity v2 = new Velocity(87, 39);
		Velocity v3 = new Velocity(89, 4930);
		
		int hashCode1 = v1.hashCode();
		int hashCode2 = v2.hashCode();
		int hashCode3 = v3.hashCode();
		
		assertEquals(hashCode1, hashCode2);
		assertNotEquals(hashCode2, hashCode3);
		
	}
	
	@Test
	void TestEquals() {
		Velocity newVelocity = new Velocity(velocityX, velocityY);
		assertEquals(velocity, newVelocity);
	}
	
	@Test
	void TestNotEquals() {
		Velocity newVelocity = new Velocity(velocityX, 34);
		assertNotEquals(velocity, newVelocity);
	}
	

}
