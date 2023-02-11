package org.openjfx.physics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Velocity {
	
	private List<Double> velocity;
	
	public Velocity(double speedX, double speedY) {
		velocity = new ArrayList<Double>(Arrays.asList(speedX, speedY));
	}
	
	public double getSpeedX() {
		return velocity.get(0);
	}
	
	public double getSpeedY() {
		return velocity.get(1);
	}
	
	public List<Double> getVelocity() {
		return velocity;
	}
	
	public void setSpeedX(double speedX) {
		velocity.set(0, speedX);
	}
	
	public void setSpeedY(double speedY) {
		velocity.set(1, speedY);
	}
	
	public boolean equals(Velocity velocity) {
		if(this.velocity.get(0) == velocity.getSpeedX() &&
				this.velocity.get(1) == velocity.getSpeedY()) {
			return true;
		}
		return false;
	}

}
