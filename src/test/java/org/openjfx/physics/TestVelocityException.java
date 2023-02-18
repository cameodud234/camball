package org.openjfx.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestVelocityException {

	@Test
	public void TestVelocityExceptionContructorVelocityMsg() {
		String message = "This is a message by Cameron.";
		VelocityException exception = new VelocityException(message);
		assertEquals(message, exception.getMessage());
	}
	
//	@Test
//	public void TestVelocityExceptionConstructorVelocityCause() {
//		VelocityException throwValue = new VelocityException();
//		try {
//			throw new Exception(throwValue);
//			
//		} catch (Exception e) {
//			assertEquals(throwValue, e.getCause());
//		}
//	}
	
//	@Test
//	public void TestVelocityExceptionConstructorVelocityMsgCause() {
//		
//		String message = "This is a message by Cameron.";
//		String localMessage = "This is a local message by Cameron.";
//		VelocityException throwValue = new VelocityException(localMessage);
//		try {
//			throw new Exception(message, throwValue);
//			
//		} catch (Exception e) {
//			assertEquals(localMessage, e.getLocalizedMessage());
//			assertEquals(throwValue, e.getCause());
//		}
//	}
	
}
