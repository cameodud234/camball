package org.openjfx.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class TestVelocity {
	
	double speedX = 5;
			
	double speedY = 2;
	
	Velocity velocity = new Velocity(speedX, speedY);
	
	@Test
	void TestVelocityConstructorSpeedX() {
		assertEquals(velocity.getSpeedX(), speedX);
	}
	
	@Test
	void TestVelocityConstructorSpeedY() {
		assertEquals(velocity.getSpeedY(), speedY);
	}
	
	@Test
	void TestSetSpeedX() {
		double newSpeedX = 8;
		velocity.setSpeedX(newSpeedX);
		assertEquals(velocity.getSpeedX(), newSpeedX);
	}
	
	@Test
	void TestSetSpeedY() {
		double newSpeedY = 49;
		velocity.setSpeedY(newSpeedY);
		assertEquals(velocity.getSpeedY(), newSpeedY);
	}
	
	@Test 
	void TestGetAngleBetween() {
		Velocity newVelocity = new Velocity(4, 3);
		assertEquals(velocity.getVelocity().dot(newVelocity.getVelocity()), 0.7334681181845473);
		
	}
	
	@Test
	void TestEquals() {
		Velocity newVelocity = new Velocity(speedX, speedY);
		
		Velocity newNewVelocity = new Velocity(speedX, 34);
		
		assertEquals(velocity, newVelocity);
		
		assertNotEquals(velocity, newNewVelocity);
	}
	

}
