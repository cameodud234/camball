package org.openjfx.physics;

import java.util.Arrays;
import java.util.stream.Stream;

import org.jblas.DoubleMatrix;
import org.junit.jupiter.params.provider.Arguments;

public class TestPhysicsTestCases {

	public static Stream<Arguments> TestPhysicsGetPixelMoveRateCases() {
		return Stream.of(
				Arguments.of(new Velocity(2.0,3.0), new DoubleMatrix(Arrays.asList(0.16666667, 0.25))),
				Arguments.of(new Velocity(9.0,30.0), new DoubleMatrix(Arrays.asList(0.75, 2.5))),
				Arguments.of(new Velocity(5.5,6.9), new DoubleMatrix(Arrays.asList(0.45833333, 0.575)))
		);
	}
	
}
