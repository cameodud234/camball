package org.openjfx.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.jblas.DoubleMatrix;
import org.junit.jupiter.api.Test;

public class TestPosition {
	
	double positionX = 5;
			
	double positionY = 2;
	
	Position position = new Position(positionX, positionY);
	
	@Test
	void TestPositionConstructorPositionX() {
		assertEquals(position.getX(), positionX);
	}
	
	@Test
	void TestPositionConstructorPositionY() {
		assertEquals(position.getY(), positionY);
	}
	
	@Test
	void TestPositionConstructorDoubleMatrixPosition() throws PositionException {
		DoubleMatrix newPositionArg = new DoubleMatrix(Arrays.asList(13.0, 64.0));
		Position newPosition = new Position(newPositionArg);
		assertEquals(newPosition.getX(), newPositionArg.get(0));
		assertEquals(newPosition.getY(), newPositionArg.get(1));
	}
	
	@Test
	void TestPositionConstructorDoubleMatrixPositionTranspose() throws PositionException {
		DoubleMatrix newPositionArg = new DoubleMatrix(Arrays.asList(13.0, 64.0));
		Position newPosition = new Position(newPositionArg);
		newPositionArg = newPositionArg.transpose();
		assertEquals(newPosition.getX(), newPositionArg.get(0));
		assertEquals(newPosition.getY(), newPositionArg.get(1));
	}
	
	@Test
	void TestPositionConstructorDoubleMatrixPositionThrows() throws PositionException {
		Throwable exception = assertThrows(PositionException.class, () -> {
			DoubleMatrix newPositionArg = new DoubleMatrix(Arrays.asList(13.0, 64.0, 67.0));
			Position newPosition = new Position(newPositionArg);
		});
		String expectedMessage = "The dimension of position must be 2, not 3";
		assertEquals(expectedMessage, exception.getMessage());
	}
	
	@Test
	void TestSetPositionX() {
		double newPositionX = 8;
		position.setX(newPositionX);
		assertEquals(position.getX(), newPositionX);
	}
	
	@Test
	void TestSetPositionY() {
		double newPositionY = 49;
		position.setY(newPositionY);
		assertEquals(position.getY(), newPositionY);
	}
	
	@Test 
	void TestGetAngleBetween() {
		Position newPosition = new Position(4, 3);
		assertEquals(position.getAngleBetween(newPosition), 0.26299473168091936);
	}
	
	@Test
	void TestEquals() {
		Position newPosition = new Position(positionX, positionY);
		assertEquals(position, newPosition);
	}
	
	@Test
	void TestNotEquals() {
		Position newPosition = new Position(2, positionY);
		assertNotEquals(position, newPosition);
		newPosition.setX(positionX);
		newPosition.setY(32);
		assertNotEquals(position, newPosition);
		newPosition.setX(342);
		newPosition.setY(32);
		assertNotEquals(position, newPosition);
		
	}
	

}
