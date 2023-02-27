package org.openjfx.physics;

import org.jblas.DoubleMatrix;

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

}
