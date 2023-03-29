package org.openjfx.objects;

import org.jblas.DoubleMatrix;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Position;
import org.openjfx.physics.Velocity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
	
	private final Physics physics;
	private final Velocity velocity;
	private Color color;
	
	private DoubleMatrix delta;
	
	public Ball(Velocity velocity, Position position, double radius, Color color, Physics physics) {
		super(position.getX(), position.getY(), radius, color);
		this.velocity = new Velocity(velocity.getX(), velocity.getY());
		this.physics = new Physics(physics.getFramerate(), physics.getPixelToMeter(), 
				physics.getScreenWidth(), physics.getScreenHeight());
		delta = this.physics.getPixelMoveRate(velocity);
	}

}
