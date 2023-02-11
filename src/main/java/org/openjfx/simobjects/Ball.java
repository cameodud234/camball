package org.openjfx.simobjects;


import org.openjfx.physics.Velocity;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball {
	
	private Velocity velocity;
	private double positionX;
	private double positionY;
	private int windowSizeX;
	private int windowSizeY;
	private double radius;
	private Paint color;
	private Circle circle;
	
	
	
	final private static String setVelocityError = "Velocity "
			+ "must contain 2 double values.";
	

	public Ball(Velocity velocity, double positionX, double positionY, 
					double radius, Paint color, 
						final int windowSizeX, final int windowSizeY) {
		
		this.velocity = new Velocity(velocity.getSpeedX(), velocity.getSpeedY());
		this.positionX = positionX;
		this.positionY = positionY;
		this.radius = radius;
		this.color = color;
		this.windowSizeX = windowSizeX;
		this.windowSizeY = windowSizeY;
		
		this.circle = new Circle(this.positionX, this.positionY, this.radius);
		this.circle.setFill(color);
		
	}
	
	public Ball(Ball ball) {
		this.positionX = ball.getPositionX();
		this.positionY = ball.getPositionY();
		this.color = ball.getColor();
		this.windowSizeX = ball.getWindowSizeX();
		this.windowSizeY = ball.getWindowSizeY();
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
	

	public int getWindowSizeX() {
		return windowSizeX;
	}


	public int getWindowSizeY() {
		return windowSizeY;
	}
	
	public void setVelocity(Velocity velocity) { 
		this.velocity.setSpeedX(velocity.getSpeedX());
		this.velocity.setSpeedY(velocity.getSpeedY());
	}

	public void setWindowSizeX(int windowSizeX) {
		this.windowSizeX = windowSizeX;
	}


	public void setWindowSizeY(int windowSizeY) {
		this.windowSizeY = windowSizeY;
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
	
}
