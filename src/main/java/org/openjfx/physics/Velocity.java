package org.openjfx.physics;


import java.util.Arrays;

import org.jblas.DoubleMatrix;

public class Velocity {
	
	private static final String VELOCITY_EXCEPTION_MESSAGE = "The dimension of velocity must be 2, not {0}";
	
	private DoubleMatrix velocity;
	
	public Velocity() {
		velocity = new DoubleMatrix(2);
	}
	
	public Velocity(double velocityX, double velocityY) {
		velocity = new DoubleMatrix(Arrays.asList(velocityX, velocityY));
	}
	
	public Velocity(DoubleMatrix velocity) throws VelocityException {
		if(velocity.rows != 2) {
			throw new VelocityException(VELOCITY_EXCEPTION_MESSAGE, velocity.rows);
		}
		this.velocity = new DoubleMatrix(2);
		this.velocity.put(0,  velocity.get(0));
		this.velocity.put(1, velocity.get(1));
	}
	
	public double getX() {
		return velocity.get(0);
	}
	
	public double getY() {
		return velocity.get(1);
	}
	
	public DoubleMatrix getVelocity() {
		return velocity;
	}
	
	public void setX(double velocityX) {
		velocity.put(0, velocityX);
	}
	
	public void setY(double velocityY) {
		velocity.put(1, velocityY);
	}
	
	@Override
	public int hashCode() {
		int result = velocity.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Velocity velocity = (Velocity) o;
		return Double.compare(this.velocity.get(0), velocity.getX()) == 0 && 
				Double.compare(this.velocity.get(1), velocity.getY()) == 0;
	}
	
	public double getAngleBetween(Velocity velocity) {
		
		double angleRad = 
				Math.acos(
					this.velocity.dot(velocity.getVelocity()) / 
					(this.velocity.norm2() * velocity.getVelocity().norm2())
		);

		return angleRad;
		
	}
}
