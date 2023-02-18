package org.openjfx.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.Test;

public class TestVelocityException {

//	@Test
//	public void TestVelocityExceptionContructorVelocityMsg() {
//		String message = "This is a message by Cameron.";
//		VelocityException exception = new VelocityException(message);
//		assertEquals(message, exception.getMessage());
//	}
	
	@Test
	public void TestVelocityExceptionConstructorVelocityCause() {
//		VelocityException throwValue = new VelocityException();
//		try {
//			throw new Exception(throwValue);
//			
//		} catch (Exception e) {
//			assertEquals(throwValue, e.getCause());
//		}
//		System.setProperty("log4j.configurationFile", "/logs/log4j2.xml");
		Logger logger = LogManager.getLogger(TestVelocityException.class);
		logger.debug("This is a debug message");
	    logger.info("This is an info message");
	    logger.warn("This is a warning message");
	    logger.error("This is an error message");
	}
	
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
