package org.openjfx.objects;

import java.util.List;
import java.util.Objects;

import org.jblas.DoubleMatrix;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Position;
import org.openjfx.physics.Velocity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
	
	private double boundWidth;
	private double boundHeight;
	
	public Ball(double centerX, double centerY, double radius, Color color, Physics physics) {
		super(centerX, centerY, radius, color);
		boundWidth = physics.getScreenWidth();
		boundHeight = physics.getScreenHeight();
	}
	
	public Ball(Position position, double radius, Color color, Physics physics) {
		super(position.getX(), position.getY(), radius, color);
		boundWidth = physics.getScreenWidth();
		boundHeight = physics.getScreenHeight();
	}
	
	public void setPosition(Position position) {
		super.setCenterX(position.getX());
		super.setCenterY(position.getY());
	}
	
	public Position getPosition() {
		return new Position(super.getCenterX(), super.getCenterY());
	}
	
	public double getBoundWidth() {
		return boundWidth;
	}
	public double getBoundHeight() {
		return boundHeight;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ball ball = (Ball) o;
		return Double.compare(ball.boundWidth, boundWidth) == 0 &&
				Double.compare(ball.boundHeight, boundHeight) == 0 &&
				super.getFill().equals(ball.getFill()) &&
				Double.compare(super.getRadius(), ball.getRadius()) == 0 &&
				Double.compare(super.getCenterX(), ball.getCenterX()) == 0 &&
				Double.compare(super.getCenterY(), ball.getCenterY()) == 0;
	}

}
