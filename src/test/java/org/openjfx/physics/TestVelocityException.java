package org.openjfx.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	public <T> void TestVelocityExceptionContructorVelocityMessageMessageArgs(String message, T messageArg, String actualMessage) {
		VelocityException velocityException = new VelocityException(message, messageArg);
		String createdMessage = velocityException.getMessage();
		assertEquals(createdMessage, actualMessage);
	}
	
	
	@ParameterizedTest
	@MethodSource("TestVelocityExceptionConstructorVelocityCauseCases")
	public void TestVelocityExceptionConstructorVelocityCause(Throwable throwValue) {
		try {
			throw new Exception(throwValue);
			
		} catch (Exception e) {
			assertEquals(throwValue, e.getCause());
		}
	}
	
	@ParameterizedTest
	@MethodSource("TestVelocityExceptionConstructorVelocityMessageCauseMessageArgsCases")
	public <T> void TestVelocityExceptionConstructorVelocityMessageCauseMessageArgs1(String message, T messageArg, Throwable throwValue, String actualMessage) {
		VelocityException velocityException = new VelocityException(message, throwValue, messageArg);
		String createdMessage = velocityException.getMessage();
		assertEquals(createdMessage, actualMessage);
	}
	
	@ParameterizedTest
	@MethodSource("TestVelocityExceptionConstructorVelocityMessageCauseMessageArgsCases")
	public <T> void TestVelocityExceptionConstructorVelocityMessageCauseMessageArgs2(String message, T messageArg, Throwable throwValue, String actualMessage) {
		VelocityException velocityException = new VelocityException(message, throwValue, messageArg);
		try {
			throw velocityException;
		} catch (Exception e) {
			assertEquals(throwValue, e.getCause());
		}
	}
	
}