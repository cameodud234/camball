package org.openjfx.objects;

import org.openjfx.physics.Physics;
import org.openjfx.physics.Position;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball {
	
	private double boundWidth;
	private double boundHeight;
	private Circle circle;
	
	public Ball(double centerX, double centerY, double radius, Color color, Physics physics) {
		circle = new Circle(centerX, centerY, radius, color);
		boundWidth = physics.getScreenWidth();
		boundHeight = physics.getScreenHeight();
	}
	
	public Ball(Position position, double radius, Color color, Physics physics) {
		circle = new Circle(position.getX(), position.getY(), radius, color);
		boundWidth = physics.getScreenWidth();
		boundHeight = physics.getScreenHeight();
	}
	
	public void setPosition(Position position) {
		circle.setCenterX(position.getX());
		circle.setCenterY(position.getY());
	}
	
	public Position getPosition() {
		return new Position(circle.getCenterX(), circle.getCenterY());
	}
	
	public void setCenterX(double centerX) {
		circle.setCenterX(centerX);
	}
	
	public double getCenterX() {
		return circle.getCenterX();
	}
	
	public void setCenterY(double centerY) {
		circle.setCenterY(centerY);
	}
	
	public double getCenterY() {
		return circle.getCenterY();
	}
	
	public double getBoundWidth() {
		return boundWidth;
	}
	
	public double getBoundHeight() {
		return boundHeight;
	}
	
	public void setFill(Color color) {
		circle.setFill(color);
	}
	
	public Paint getFill() {
		return circle.getFill();
	}
	
	public void setRadius(double radius) {
		circle.setRadius(radius);
	}
	
	public double getRadius() {
		return circle.getRadius();
	}
	
	public Circle getUnderlyingFXObject() {
		return circle;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ball ball = (Ball) o;
		return Double.compare(ball.boundWidth, boundWidth) == 0 &&
				Double.compare(ball.boundHeight, boundHeight) == 0 &&
				circle.getFill().equals(ball.getFill()) &&
				Double.compare(getRadius(), ball.getRadius()) == 0 &&
				Double.compare(getCenterX(), ball.getCenterX()) == 0 &&
				Double.compare(getCenterY(), ball.getCenterY()) == 0;
	}

}
