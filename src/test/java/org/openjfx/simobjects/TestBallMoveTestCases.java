package org.openjfx.simobjects;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;
import org.openjfx.physics.Position;
import org.openjfx.physics.Velocity;

public class TestBallMoveTestCases {
	
	public static Stream<Arguments> TestBallMoveTestCasesArgs() {
		return Stream.of(
				Arguments.of(new Velocity(2.0,3.0), new Position(4.0, 0.25), new Position(4.33333333, 0.75)),
				Arguments.of(new Velocity(9.0,30.0), new Position(0.75, 0.25), new Position(2.25, 5.25))
//				Arguments.of(new Velocity(5.5,6.9), new Position(0.45833333, 599.75), new Position(1.375, 599.175)),
//				Arguments.of(new Velocity(-36.0, 56.0), new Position(4.0, 30.0), new Position(10.0, 39.33333333))
		);
	}
	
}