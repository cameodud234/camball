package org.openjfx.simobjects;


import org.openjfx.physics.Velocity;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball {
	
	private Velocity velocity;
	private double centerX;
	private double centerY;
	private double radius;
	private Paint color;
	private Circle circle;
	
	private static String ILLEGALPOSITIONARGUMENT = "Position must be greater than zero.";
	private static String ILLEGALRADIUSARGUMENT = "Radius must be greater than zero.";
	

	public Ball(Velocity velocity, double centerX, double centerY,
					double radius, Paint color) {
		
		this.velocity = new Velocity(velocity.getSpeedX(), velocity.getSpeedY());
		if(centerX < 0 || centerY < 0) {
			throw new IllegalArgumentException(ILLEGALPOSITIONARGUMENT);
		}
		this.centerX = centerX;
		this.centerY = centerY;
//		 need to test if radius is greater than zero.
		if(radius < 0) {
			throw new IllegalArgumentException(ILLEGALRADIUSARGUMENT);
		}
		this.radius = radius;
		this.color = color;
		
		this.circle = new Circle(this.centerX, this.centerY, this.radius);
		this.circle.setFill(color);
		
	}
	
	public Ball(Ball ball) {
		this.centerX = ball.getCenterX();
		this.centerY = ball.getCenterY();
		this.color = ball.getColor();
	}
	
	
	public void move(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		
		this.circle.setCenterX(this.centerX);
		this.circle.setCenterY(this.centerY);
		
	}
	
	public Velocity getVelocity() {
		return velocity;
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
	
	public void setVelocity(double speedX, double speedY) {
		this.velocity.setSpeedX(speedX);
		this.velocity.setSpeedY(speedY);
	}
	
	public void setVelocity(Velocity velocity) { 
		this.velocity.setSpeedX(velocity.getSpeedX());
		this.velocity.setSpeedY(velocity.getSpeedY());
	}
	
	public void setCenterX(double centerX) {
		this.circle.setCenterX(centerX);
	}
	
	public void setCenterY(double centerY) {
		this.circle.setCenterY(centerY);
	}

	public void setRadius(double radius) {
		if(radius < 0) {
			throw new IllegalArgumentException(ILLEGALRADIUSARGUMENT);
		}
		this.radius = radius;
		circle.setRadius(radius);

	}


	public void setColor(Paint color) {
		this.color = color;
	}


	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Ball ball = (Ball) o;
		return velocity.equals(ball.velocity) &&
				Double.compare(centerX, ball.getCenterX()) == 0 &&
				Double.compare(centerY, ball.getCenterY()) == 0 &&
				Double.compare(radius, ball.getRadius()) == 0 &&
				color.equals(ball.getColor()) &&
				circle.equals(ball.getCircle());
	}
	
}
