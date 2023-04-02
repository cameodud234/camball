package org.openjfx.objects;

import java.util.List;

import org.jblas.DoubleMatrix;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Position;
import org.openjfx.physics.Velocity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
	
	private final Velocity velocity;
	private final Position position;
	
	private double boundWidth;
	private double boundHeight;
	private DoubleMatrix delta;
	
	public Ball(Velocity velocity, Position position, double radius, Color color, Physics physics) {
		super(position.getX(), position.getY(), radius, color);
		this.velocity = new Velocity(velocity.getX(), velocity.getY());
		this.position = new Position(position.getX(), position.getY());
		boundWidth = physics.getScreenWidth();
		boundHeight = physics.getScreenHeight();
		DoubleMatrix physicsValues = physics.getPixelMoveRate(velocity);
		delta = new DoubleMatrix(List.of(physicsValues.get(0), physicsValues.get(1)));
	}
	
	
	public void move(DoubleMatrix pixelMoveRate) {
		if(super.getCenterX() - super.getRadius() <= 0) {
			delta.put(0, Math.abs(pixelMoveRate.get(0)));
        }
        else if(super.getCenterX() + super.getRadius() >= boundWidth) {
        	delta.put(0, -Math.abs(pixelMoveRate.get(0)));
        }
        
        if(super.getCenterY() - super.getRadius() <= 0) {
        	delta.put(1, Math.abs(pixelMoveRate.get(1)));
        }
        else if(super.getCenterY() + super.getRadius() >= boundHeight) {
        	delta.put(1, -Math.abs(pixelMoveRate.get(1)));
        }
        
        super.setCenterX(super.getCenterX() + delta.get(0));
        super.setCenterY(super.getCenterY() + delta.get(1));
    	
    	position.setX(super.getCenterX());
    	position.setY(super.getCenterY());
	}
	
	public void setVelocity(Velocity velocity) {
		this.velocity.setX(velocity.getX());
		this.velocity.setY(velocity.getY());
	}
	
	public void setPosition(Position position) {
		super.setCenterX(position.getX());
		super.setCenterY(position.getY());
		this.position.setX(position.getX());
		this.position.setY(position.getY());
	}
	
	public Velocity getVelocity() {
		return new Velocity(velocity.getX(), velocity.getY());
	}
	
	public Position getPosition() {
		return new Position(position.getX(), position.getY());
	}
	
	public double getBoundWidth() {
		return boundWidth;
	}
	public double getBoundHeight() {
		return boundHeight;
	}
	
	public DoubleMatrix getDelta() {
		return new DoubleMatrix(List.of(delta.get(0), delta.get(1)));
	}

}
