package org.openjfx.simobjects;


import org.jblas.DoubleMatrix;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Velocity;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball {
	
	private final Physics physics;
	
	private Velocity velocity;
	private double centerX;
	private double centerY;
	private double boundX;
	private double boundY;
	private double deltaX;
	private double deltaY;
	private double radius;
	private Paint color;
	private Circle circle;
	
	private static String ILLEGALRADIUSARGUMENT = "Radius must be greater than zero.";
	
	
	public Ball(Velocity velocity, Physics physics, double centerX, double centerY,
			double boundX, double boundY, double radius, Paint color) {
		
		this.velocity = new Velocity(velocity.getVelocityX(), velocity.getVelocityY());
		
		this.physics = new Physics(physics.getFramerate(), physics.getPixelToMeter());
		
		this.centerX = centerX;
		this.centerY = centerY;
		
		this.boundX = boundX;
		this.boundY = boundY;
		
		// need to test if radius is less than zero.
		if(radius < 0) {
			throw new IllegalArgumentException(ILLEGALRADIUSARGUMENT);
		}
		
		this.radius = radius;
		
		this.color = color;
		
		circle = new Circle(this.centerX, this.centerY, this.radius, this.color);
		
		DoubleMatrix pixelMoveRate = this.physics.getPixelMoveRate(this.velocity);
		
		deltaX = pixelMoveRate.get(0);
		deltaY = pixelMoveRate.get(1);
		
	}
	 
	public void move() {
		
		DoubleMatrix pixelMoveRate = physics.getPixelMoveRate(velocity);
		
		if(centerX - radius <= 0) {
        	deltaX = pixelMoveRate.get(0);
        }
        else if(centerX + radius >= boundX) {
        	deltaX = -pixelMoveRate.get(0);
        }
        
        if(centerY - radius <= 0) {
        	deltaY = pixelMoveRate.get(1);
        }
        else if(centerY + radius >= boundY) {
        	deltaY = -pixelMoveRate.get(1);
        }
        
        centerX += deltaX;
        centerY += deltaY;
        circle.setCenterX(centerX);
        circle.setCenterY(centerY);
		
	}
	
	public Velocity getVelocity() {
		return velocity;
	}
	
	public Physics getPhysics() {
		return physics;
	}
	
	public double getCenterX() {
		return centerX;
	}
	
	public double getCenterY() {
		return centerY;
	}

	
	public double getRadius() {
		return radius;
	}
	
	public Paint getColor() {
		return color;
	}
	
	
	public Circle getCircle() {
		return circle;
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
	
	public void setCenterX(double centerX) {
		this.centerX = centerX;
		circle.setCenterX(this.centerX);
	}
	
	public void setCenterY(double centerY) {
		this.centerY = centerY;
		circle.setCenterY(this.centerY);
	}

	public void setRadius(double radius) {
		if(radius < 0) {
			throw new IllegalArgumentException(ILLEGALRADIUSARGUMENT);
		}
		this.radius = radius;
		circle.setRadius(this.radius);
	}

	public void setColor(Paint color) {
		this.color = color;
		circle.setFill(this.color);
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Ball ball = (Ball) o;
		return velocity.equals(ball.velocity) &&
				physics.equals(ball.getPhysics()) &&
				Double.compare(centerX, ball.getCenterX()) == 0 &&
				Double.compare(centerY, ball.getCenterY()) == 0 &&
				Double.compare(boundX, ball.boundX) == 0 &&
				Double.compare(boundY, ball.boundY) == 0 &&
				Double.compare(radius, ball.getRadius()) == 0 &&
				color.equals(ball.getColor());
	}
	
}
