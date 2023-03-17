package org.openjfx.simobjects;

import org.jblas.DoubleMatrix;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Position;
import org.openjfx.physics.Velocity;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
	
	private final Physics physics;
	
	private Velocity velocity;
	private double boundX;
	private double boundY;
	private double deltaX;
	private double deltaY;
	
	public Ball(Velocity velocity, Physics physics, Position position,
			double boundX, double boundY, double radius, Paint color) {
		
		super(position.getPositionX(), position.getPositionY(), radius, color);
		
		this.velocity = new Velocity(velocity.getVelocityX(), velocity.getVelocityY());
		
		this.boundX = boundX;
		this.boundY = boundY;
		
		this.physics = new Physics(physics.getFramerate(), physics.getPixelToMeter(), 
				this.boundX, this.boundY);
		 
		DoubleMatrix pixelMoveRate = this.physics.getPixelMoveRate(this.velocity);
		
		deltaX = pixelMoveRate.get(0);
		deltaY = pixelMoveRate.get(1);
	
	}
	
	public double getBoundX() {
		return boundX;
	}

	public double getBoundY() {
		return boundY;
	}

	public double getDeltaY() {
		return deltaY;
	}

	public double getDeltaX() {
		return deltaX;
	}
	
	public Velocity getVelocity() {
		return velocity;
	}
	
	public Physics getPhysics() {
		return physics;
	}
	
	public void setVelocity(double velocityX, double velocityY) {
		this.velocity.setVelocityX(velocityX);
		this.velocity.setVelocityY(velocityY);
	}
	
	public void setVelocity(Velocity velocity) {
		this.velocity = new Velocity();
		this.velocity.setVelocityX(velocity.getVelocityX());
		this.velocity.setVelocityY(velocity.getVelocityY());
	}
	
	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}

	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}
	
	public void setBoundX(double boundX) {
		this.boundX = boundX;
	}
	
	public void setBoundY(double boundY) {
		this.boundY = boundY;
	}
	
	@Override
	public int hashCode() {
		int result = velocity.hashCode();
		result = 31 * result + physics.hashCode();
		result = 31 * result + Double.hashCode(super.getCenterX());
		result = 31 * result + Double.hashCode(super.getCenterY());
		result = 31 * result + Double.hashCode(boundX);
		result = 31 * result + Double.hashCode(boundY);
		result = 31 * result + Double.hashCode(deltaX);
		result = 31 * result + Double.hashCode(deltaY);
		result = 31 * result + Double.hashCode(super.getRadius());
		result = 31 * result + super.getFill().hashCode();
		
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Ball ball = (Ball) o;
		return velocity.equals(ball.velocity) &&
				physics.equals(ball.getPhysics()) &&
				Double.compare(super.getCenterX(), ball.getCenterX()) == 0 &&
				Double.compare(super.getCenterY(), ball.getCenterY()) == 0 &&
				Double.compare(boundX, ball.boundX) == 0 &&
				Double.compare(boundY, ball.boundY) == 0 &&
				Double.compare(super.getRadius(), ball.getRadius()) == 0 &&
				super.getFill().equals(ball.getFill());
	}
	
}
