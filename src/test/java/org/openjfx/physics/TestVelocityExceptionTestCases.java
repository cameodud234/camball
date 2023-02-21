package org.openjfx.physics;

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
				Arguments.of("Velocity dimension needs to be 2, not {0}", "Velocity dimension needs to be 2, not 3", 3),
				Arguments.of("Velocity dimension needs to be 2, not {0}", "Velocity dimension needs to be 2, not 3.75", 3.75),
				Arguments.of("This is another message with a value: {0}", "This is another message with a value: Cameron", "Cameron")
		);
	}
	
}
