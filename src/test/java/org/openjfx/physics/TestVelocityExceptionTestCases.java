package org.openjfx.physics;

import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class TestVelocityExceptionTestCases {
	public static Stream<Arguments> TestVelocityExceptionContructorVelocityMessageCases() {
		return Stream.of(
				Arguments.of("Velocity dimension needs to be 2"),
				Arguments.of("This is another message"),
				Arguments.of("This is another message")
		);
	}
	
	public static Stream<Arguments> TestVelocityExceptionContructorVelocityMessageMessageArgsCases() {
		return Stream.of(
				Arguments.of("Velocity dimension needs to be 2, not {0}", 3, "Velocity dimension needs to be 2, not 3"),
				Arguments.of("Velocity dimension needs to be 2, not {0}", 3.75, "Velocity dimension needs to be 2, not 3.75"),
				Arguments.of("This is another message with a value: {0}", "Cameron", "This is another message with a value: Cameron")
		);
	}
	
	public static Stream<Arguments> TestVelocityExceptionConstructorVelocityCauseCases() {
		return Stream.of(
				Arguments.of(new VelocityException()),
				Arguments.of(new IllegalArgumentException()),
				Arguments.of(new TimeoutException())
		);
	}
	
	public static Stream<Arguments> TestVelocityExceptionConstructorVelocityMessageCauseMessageArgsCases() {
		return Stream.of(
				Arguments.of("Velocity dimension needs to be 2, not {0}", 3, new IllegalArgumentException(), "Velocity dimension needs to be 2, not 3: java.lang.IllegalArgumentException"),
				Arguments.of("Velocity dimension needs to be 2, not {0}", 3.75, new RuntimeException(), "Velocity dimension needs to be 2, not 3.75: java.lang.RuntimeException"),
				Arguments.of("This is another message with a value: {0}", "Cameron", new TimeoutException(), "This is another message with a value: Cameron: java.util.concurrent.TimeoutException")
		);
	}
	
}