package org.openjfx.simobjects;

import java.util.Arrays;
import java.util.stream.Stream;

import org.jblas.DoubleMatrix;
import org.junit.jupiter.params.provider.Arguments;
import org.openjfx.physics.Velocity;

public class TestBallMoveTestCases {
	
	public static Stream<Arguments> TestBallMoveTestCases() {
		return Stream.of(
				Arguments.of(new Velocity(2.0,3.0), new DoubleMatrix(Arrays.asList(0.16666667, 0.25)), Arrays.asList(2,5), ),
				Arguments.of(new Velocity(9.0,30.0), new DoubleMatrix(Arrays.asList(0.75, 2.5))),
				Arguments.of(new Velocity(5.5,6.9), new DoubleMatrix(Arrays.asList(0.45833333, 0.575))),
				Arguments.of(new Velocity(-36, 56), new DoubleMatrix(Arrays.asList(-5.66666667, 9.333333333)))
		);
	}
	
}