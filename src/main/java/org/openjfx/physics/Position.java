package org.openjfx.physics;

import java.util.Arrays;

import org.jblas.DoubleMatrix;

public class Position {
	
	private DoubleMatrix position;
	
	private static final String POSITION_EXCEPTION_MESSAGE = "The dimension of position must be 2, not {}";
	
	public Position() {
		position = new DoubleMatrix(2);
	}
	
	public Position(double positionX, double positionY) { 
		position = new DoubleMatrix(Arrays.asList(positionX, positionY));
	}
	
	public Position(DoubleMatrix position) throws PositionException {
		if(position.rows != 2) {
			throw new PositionException(POSITION_EXCEPTION_MESSAGE, position.rows);
		}
		this.position = new DoubleMatrix(2);
		this.position.put(0, position.get(0));
		this.position.put(1, position.get(1));
	}
	
	public double getPositionX() {
		return position.get(0);
	}
	
	public double getPositionY() {
		return position.get(1);
	}
	
	public DoubleMatrix getPosition() {
		return position;
	}
	
	public void setPositionX(double positionX) {
		position.put(0, positionX);
	}
	
	public void setPositionY(double positionY) {
		position.put(1, positionY);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Position position = (Position) o;
		return Double.compare(this.position.get(0), position.getPositionX()) == 0 && 
				Double.compare(this.position.get(1), position.getPositionY()) == 0;
	}
	
	public double getAngleBetween(Position position) {
		
		double angleRad = 
				Math.acos( 
					this.position.dot(position.getPosition()) / 
					(this.position.norm2() * position.getPosition().norm2())
		);

		return angleRad;
		
	}

}