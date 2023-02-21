package org.openjfx.physics;


import java.util.Arrays;

import org.jblas.DoubleMatrix;

public class Velocity {
	
	private static final String VELOCITY_EXCEPTION_MESSAGE = "The dimension of velocity must be 2, not {}";
	
	private DoubleMatrix velocity;
	
	public Velocity() {
		velocity = new DoubleMatrix(2);
	}
	
	public Velocity(double speedX, double speedY) {
		velocity = new DoubleMatrix(Arrays.asList(speedX, speedY));
	}
	
	public Velocity(DoubleMatrix velocity) throws VelocityException {
		if(velocity.rows != 2) {
			throw new VelocityException(VELOCITY_EXCEPTION_MESSAGE, velocity.rows);
		}
		this.velocity = new DoubleMatrix(2);
		this.velocity.put(0,  velocity.get(0));
		this.velocity.put(1, velocity.get(1));
	}
	
	public double getSpeedX() {
		return velocity.get(0);
	}
	
	public double getSpeedY() {
		return velocity.get(1);
	}
	
	public DoubleMatrix getVelocity() {
		return velocity;
	}
	
	public void setSpeedX(double speedX) {
		velocity.put(0, speedX);
	}
	
	public void setSpeedY(double speedY) {
		velocity.put(1, speedY);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Velocity velocity = (Velocity) o;
		return Double.compare(this.velocity.get(0), velocity.getSpeedX()) == 0 && 
				Double.compare(this.velocity.get(1), velocity.getSpeedY()) == 0;
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
