package org.openjfx.camball;

import org.openjfx.physics.Position;
import org.openjfx.physics.Velocity;

public class GameLogicState {
	
	private Velocity velocity;
	private Position position;
	private double deltaX;
	private double deltaY;
	
	public Position getPosition() {
		return position;
	}
	
	public Velocity getVelocity() {
		return velocity;
	}
	
	public double getDeltaX() {
		return deltaX;
	}
	
	public double getDeltaY() {
		return deltaY;
	}
	
	public void setVelocity(Velocity velocity) {
		this.velocity = new Velocity(velocity.getVelocityX(), velocity.getVelocityY());
	}
	
	public void setPosition(Position position) {
		this.position = new Position(position.getPositionX(), position.getPositionY());
	}
	
	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}
	
	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}
	

}
