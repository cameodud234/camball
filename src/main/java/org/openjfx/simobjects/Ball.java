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
	
	private static String ILLEGALRADIUSARGUMENT = "Radius must be greater than zero.";
	
	
	public Ball(Velocity velocity, double centerX, double centerY,
					double radius, Paint color) {
		
		this.velocity = new Velocity(velocity.getSpeedX(), velocity.getSpeedY());
		this.centerX = centerX;
		this.centerY = centerY;
//		 need to test if radius is greater than zero.
		if(radius < 0) {
			throw new IllegalArgumentException(ILLEGALRADIUSARGUMENT);
		}
		this.radius = radius;
		this.color = color;
		
		circle = new Circle(this.centerX, this.centerY, this.radius);
		circle.setFill(this.color);
		
	}
	
	public Ball(Ball ball) {
		centerX = ball.getCenterX();
		centerY = ball.getCenterY();
		color = ball.getColor();
	}
	
	public void move(double deltaX, double deltaY) {
		this.centerX += deltaX;
		this.centerY += deltaY;
		
		circle.setCenterX(centerX);
		circle.setCenterY(centerY);
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
		this.centerX = centerX;
		this.circle.setCenterX(centerX);
	}
	
	public void setCenterY(double centerY) {
		this.centerY = centerY;
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
		circle.setFill(color);
	}

	public void setCircle(Circle circle) {
		setCenterX(circle.getCenterX());
		setCenterY(circle.getCenterY());
		setRadius(circle.getRadius());
		setColor(circle.getFill());
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
