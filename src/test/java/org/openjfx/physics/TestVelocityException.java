package org.openjfx.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class TestVelocityException extends TestVelocityExceptionTestCases {

	@ParameterizedTest
	@MethodSource("TestVelocityExceptionContructorVelocityMessageCases")
	public void TestVelocityExceptionContructorVelocityMessage(String message) {
		VelocityException velocityException = new VelocityException(message);
		assertEquals(message, velocityException.getMessage());
	}
	
	@ParameterizedTest
	@MethodSource("TestVelocityExceptionContructorVelocityMessageMessageArgsCases")
	public <T> void TestVelocityExceptionContructorVelocityMessageMessageArgs(String message, String actualMessage, T messageArg) {
		VelocityException velocityException = new VelocityException(message, messageArg);
		String createdMessage = velocityException.getMessage();
		assertEquals(createdMessage, actualMessage);
	}
	
	
	@Test
	public void TestVelocityExceptionConstructorVelocityCause() {
		VelocityException throwValue = new VelocityException();
		try {
			throw new Exception(throwValue);
			
		} catch (Exception e) {
			assertEquals(throwValue, e.getCause());
		}
	}
	
//	@Test
//	public void TestVelocityExceptionConstructorVelocityCauseCauseMessageArgs() {
//		IllegalArgumentException cause = new IllegalArgumentException();
//		String message = "Velocity dimension needs to be 2, not {0}";
//		VelocityException throwValue = new VelocityException(message, cause, 3);
//		try {
//			throw throwValue;
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
