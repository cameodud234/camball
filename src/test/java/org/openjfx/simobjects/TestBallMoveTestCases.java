package org.openjfx.simobjects;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;
import org.openjfx.physics.Velocity;

public class TestBallMoveTestCases {
	
	public static Stream<Arguments> TestBallMoveTestCasesArgs() {
		return Stream.of(
				Arguments.of(new Velocity(2.0,3.0), Arrays.asList(4.0, 0.25), Arrays.asList(4.33333333, 0.75)),
				Arguments.of(new Velocity(9.0,30.0), Arrays.asList(0.75, 2.5), Arrays.asList(2.25, 7.5)),
				Arguments.of(new Velocity(5.5,6.9), Arrays.asList(0.45833333, 599.75), Arrays.asList(1.375, 599.175)),
				Arguments.of(new Velocity(-36.0, 56.0), Arrays.asList(4.0, 30.0), Arrays.asList(10.0, 39.33333333))
		);
	}
	
}