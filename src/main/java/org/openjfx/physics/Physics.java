package org.openjfx.physics;

import org.jblas.DoubleMatrix;

public class Physics {
	
	private final double framerate;
	
	private double pixelToMeter;
	private double screenWidthX;
	private double screenWidthY;
	
	public Physics(double framerate, double pixelToMeter, double screenWidthX, double screenWidthY) {
		
		this.framerate = framerate;
		this.pixelToMeter = pixelToMeter;
		this.screenWidthX = screenWidthX;
		this.screenWidthY = screenWidthY;
		
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
	
	public double getScreenWidthX() {
		return screenWidthX;
	}
	
	public double getScreenWidthY() {
		return screenWidthY;
	}
	
	@Override
	public int hashCode() {
		int result = Double.hashCode(framerate);
		result = 31 * result + Double.hashCode(pixelToMeter);
		result = 31 * result + Double.hashCode(screenWidthX);
		result = 31 * result + Double.hashCode(screenWidthY);
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