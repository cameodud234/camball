package org.openjfx.physics;

import org.jblas.DoubleMatrix;

public class Physics {
	
	private final double framerate;
	
	private double pixelToMeter;
	private double screenWidth;
	private double screenHeight;
	
	public Physics(double framerate, double pixelToMeter, double screenWidth, double screenHeight) {
		
		this.framerate = framerate;
		this.pixelToMeter = pixelToMeter;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
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
	
	public double getScreenWidth() {
		return screenWidth;
	}
	
	public double getScreenHeight() {
		return screenHeight;
	}
	
	@Override
	public int hashCode() {
		int result = Double.hashCode(framerate);
		result = 31 * result + Double.hashCode(pixelToMeter);
		result = 31 * result + Double.hashCode(screenWidth);
		result = 31 * result + Double.hashCode(screenHeight);
		return result;
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