package org.openjfx.simobjects;

import java.lang.Thread.State;

import org.jblas.DoubleMatrix;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Position;
import org.openjfx.physics.Velocity;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
	
	private final Physics physics;
	private final double boundX;
	private final double boundY;
	
	private DoubleMatrix pixelMoveRate;
	private Velocity velocity;
	private Position position;
	private double deltaX;
	private double deltaY;
	
	public Ball(Velocity velocity, Physics physics, Position position,
			double boundX, double boundY, double radius, Paint color) {
		
		super(position.getPositionX(), position.getPositionY(), radius, color);
		
		this.velocity = new Velocity(velocity.getVelocityX(), velocity.getVelocityY());
		
		this.position = new Position(position.getPositionX(), position.getPositionY());
		
		this.boundX = boundX;
		this.boundY = boundY;
		
		this.physics = new Physics(physics.getFramerate(), physics.getPixelToMeter(), 
				this.boundX, this.boundY);
		 
		pixelMoveRate = this.physics.getPixelMoveRate(this.velocity);
		
		deltaX = pixelMoveRate.get(0);
		deltaY = pixelMoveRate.get(1);
	
	}
	
	public void move() {
		
		pixelMoveRate = this.physics.getPixelMoveRate(velocity);
		
		if(super.getCenterX() - super.getRadius() <= 0) {
        	deltaX = Math.abs(pixelMoveRate.get(0));
        }
        else if(super.getCenterX() + super.getRadius() >= boundX) {
        	deltaX = -Math.abs(pixelMoveRate.get(0));
        }
        
        if(super.getCenterY() - super.getRadius() <= 0) {
        	deltaY = Math.abs(pixelMoveRate.get(1));
        }
        else if(super.getCenterY() + super.getRadius() >= boundY) {
        	deltaY = -Math.abs(pixelMoveRate.get(1));
        }
        
        super.setCenterX(super.getCenterX() + deltaX);
        super.setCenterY(super.getCenterY() + deltaY);
        
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
	
	public Position getPosition() {
		return position;
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
	
	public void setPosition(double positionX, double positionY) {
		position.setPositionX(positionX);
		position.setPositionY(positionY);
		super.setCenterX(position.getPositionX());
		super.setCenterY(position.getPositionY());
	}
	
	public void setPosition(Position position) {
		this.position.setPositionX(position.getPositionX());
		this.position.setPositionY(position.getPositionY());
		
		super.setCenterX(this.position.getPositionX());
		super.setCenterY(this.position.getPositionY());
		
	}
	
	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}

	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
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
