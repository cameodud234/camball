package org.openjfx.physics;

import java.util.List;

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
		DoubleMatrix privateVelocity = (velocity.getVelocity().mul(pixelToMeter)).div(framerate);
		return new DoubleMatrix(List.of(privateVelocity.get(0), privateVelocity.get(1)));
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
				Double.compare(pixelToMeter, physics.getPixelToMeter()) == 0 &&
				Double.compare(screenWidth, physics.getScreenWidth()) == 0 &&
				Double.compare(screenHeight, physics.getScreenHeight()) == 0;
	}

}