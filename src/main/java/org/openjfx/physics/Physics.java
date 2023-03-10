package org.openjfx.physics;

import org.jblas.DoubleMatrix;
import org.openjfx.simobjects.Ball;

public class Physics {
	
	private final double framerate;
	
	private double pixelToMeter;
	
	public Physics(double framerate, double pixelToMeter) {
		
		this.framerate = framerate;
		this.pixelToMeter = pixelToMeter;
		
	}
	
	public DoubleMatrix getPixelMoveRate(Velocity velocity) {
		return (velocity.getVelocity().mul(pixelToMeter)).div(framerate);
	}
	
	public void setPixelToMeter(double pixelToMeter) {
		this.pixelToMeter = pixelToMeter;
	}
	
	public double getFramerate() {
		return framerate;
	}
	
	public double getPixelToMeter() {
		return pixelToMeter;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Physics physics = (Physics) o;
		return Double.compare(framerate, physics.getFramerate()) == 0 &&
				Double.compare(pixelToMeter, getPixelToMeter()) == 0;
	}

}