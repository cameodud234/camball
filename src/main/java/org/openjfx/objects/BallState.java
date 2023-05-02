package org.openjfx.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jblas.DoubleMatrix;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Position;
import org.openjfx.physics.Velocity;

import javafx.scene.paint.Color;

public class BallState {
	
private static final Color[] color = {Color.ALICEBLUE, Color.BLUE, Color.RED, Color.GREEN, Color.GRAY, Color.PURPLE};
	
	private final double screenWidth;
	private final double screenHeight;
	private final Physics physics;

	private final Position position;
	private final double mass;
	
	private final List<Double> randomInitialPosition;
    private final List<Double> randomInitialVelocity;
    private final Random randomNumber;
    private Velocity velocity;
    private DoubleMatrix delta;
    private double radius;
	
	Color chosenColor;
	
	public BallState(double screenWidth, double screenHeight, Physics physics) {
    	
    	this.screenWidth = screenWidth;
    	this.screenHeight = screenHeight;
    	this.physics = physics;
    	randomNumber = new Random();
    	double boundRadius = Math.min(randomNumber.nextInt(20, 100), Math.min(screenWidth, screenHeight) );
		double[] boundPosition = {0 + boundRadius, Math.min(screenWidth, screenHeight) - boundRadius};
		double[] boundVelocity = {-100, 100};
		double[] boundMass = {1, 50};
		
		randomInitialVelocity = new ArrayList<Double>( 
				List.of(
						randomNumber.nextDouble(boundVelocity[0], boundVelocity[1]), 
						randomNumber.nextDouble(boundVelocity[0], boundVelocity[1])
				)
		);
		
		randomInitialPosition = new ArrayList<Double>( 
				List.of(
						randomNumber.nextDouble(boundPosition[0], boundPosition[1]), 
						randomNumber.nextDouble(boundPosition[0], boundPosition[1])
				)
		);
		
		velocity = new Velocity(randomInitialVelocity.get(0), randomInitialVelocity.get(1));
		position = new Position(randomInitialPosition.get(0), randomInitialPosition.get(1));
		mass = randomNumber.nextDouble(boundMass[0], boundMass[1]);
		radius = boundRadius;
		
		delta = physics.getPixelMoveRate(velocity);
		
		Color randomColor = color[randomNumber.nextInt(0, color.length)];
		chosenColor = randomColor;
    	
    }     
	
	public void update() {
		
//		delta = physics.getPixelMoveRate(velocity);
		
		
		if(position.getX() - radius <= 0) {
			delta.put(0, Math.abs(delta.get(0)));
        }
        else if(position.getX() + radius >= screenWidth) {
        	delta.put(0, -Math.abs(delta.get(0)));
        }
        
        if(position.getY() - radius <= 0) {
        	delta.put(1, Math.abs(delta.get(1)));
        }
        else if(position.getY() + radius >= screenHeight) {
        	delta.put(1, -Math.abs(delta.get(1)));
        }
        
        position.setX(position.getX() + delta.get(0));
        position.setY(position.getY() + delta.get(1));
		
	}
	
	public void update(Velocity velocity) {
		delta = physics.getPixelMoveRate(velocity);
		
		if(position.getX() - radius <= 0) {
			delta.put(0, Math.abs(delta.get(0)));
        }
        else if(position.getX() + radius >= screenWidth) {
        	delta.put(0, -Math.abs(delta.get(0)));
        }
        
        if(position.getY() - radius <= 0) {
        	delta.put(1, Math.abs(delta.get(1)));
        }
        else if(position.getY() + radius >= screenHeight) {
        	delta.put(1, -Math.abs(delta.get(1)));
        }
        
        position.setX(position.getX() + delta.get(0));
        position.setY(position.getY() + delta.get(1));
		
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Color getColor() {
		return chosenColor;
	}

	public void setColor(Color color) {
		this.chosenColor = color;
	}

	public double getScreenWidth() {
		return screenWidth;
	}

	public double getScreenHeight() {
		return screenHeight;
	}
	
	public void setVelocity(Velocity velocity) {
		this.velocity = new Velocity(velocity.getX(), velocity.getY());
	}

	public Velocity getVelocity() {
		return velocity;
	}

	public Position getPosition() {
		return position;
	}
	
	public double getMass() {
		return mass;
	}

	public List<Double> getRandomInitialPosition() {
		return randomInitialPosition;
	}

	public List<Double> getRandomInitialVelocity() {
		return randomInitialVelocity;
	}
    

}
