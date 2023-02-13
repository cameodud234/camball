package org.openjfx.physics;


import java.util.Arrays;

import org.jblas.DoubleMatrix;

public class Velocity {
	
	private DoubleMatrix velocity;
	
	public Velocity(double speedX, double speedY) {
		velocity = new DoubleMatrix(Arrays.asList(speedX, speedY));
	}
	
	public Velocity(DoubleMatrix velocity) {
		if(velocity.isRowVector()) {
			velocity.transpose();
			if(velocity.rows != 2) {
				throw new VelocityException();
			}
		}
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
	
	public boolean equals(Velocity velocity) {
		if(this.velocity.get(0) == velocity.getSpeedX() &&
				this.velocity.get(1) == velocity.getSpeedY()) {
			return true;
		}
		return false;
	}
	
	public double getAngleBetween(Velocity velocity) {
		
		double angleRad = Math.acos(this.velocity.dot(velocity.getVelocity()) / (this.velocity.norm2() * velocity.getVelocity().norm2()));
		
		return angleRad;
		
	}

}
