package org.openjfx.simobjects;


import org.openjfx.physics.Velocity;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball {
	
	private Velocity velocity;
	private double positionX;
	private double positionY;
	private double radius;
	private Paint color;
	private Circle circle;
	
	
	private static String illegalPosition = "Position must be greater than zero.";
	

	public Ball(Velocity velocity, double positionX, double positionY, 
					double radius, Paint color) {
		
		this.velocity = new Velocity(velocity.getSpeedX(), velocity.getSpeedY());
		if(positionX < 0 || positionY < 0) throw new IllegalArgumentException(illegalPosition);
		this.positionX = positionX;
		this.positionY = positionY;
		this.radius = radius;
		this.color = color;
		
		this.circle = new Circle(this.positionX, this.positionY, this.radius);
		this.circle.setFill(color);
		
	}
	
	public Ball(Ball ball) {
		this.positionX = ball.getPositionX();
		this.positionY = ball.getPositionY();
		this.color = ball.getColor();
	}
	
	
	public void move(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		
		this.circle.setCenterX(this.positionX);
		this.circle.setCenterY(this.positionY);
		
	}
	
	public Velocity getVelocity() {
		return velocity;
	}
	

	public double getPositionX() {
		return positionX;
	}
	
	public double getPositionY() {
		return positionY;
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
	
	public void setVelocity(Velocity velocity) { 
		this.velocity.setSpeedX(velocity.getSpeedX());
		this.velocity.setSpeedY(velocity.getSpeedY());
	}

	public void setRadius(double radius) {
		this.radius = radius;
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
				Double.compare(positionX, ball.getPositionX()) == 0 &&
				Double.compare(positionY, ball.getPositionY()) == 0 &&
				Double.compare(radius, ball.getRadius()) == 0 &&
				color.equals(ball.getColor()) &&
				circle.equals(ball.getCircle());
				   
				
	}
	
}
