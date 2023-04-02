package org.openjfx.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openjfx.physics.Physics;
import org.openjfx.physics.Position;
import org.openjfx.physics.Velocity;

import javafx.scene.paint.Color;

public class BallState {
	
private static final Color[] color = {Color.ALICEBLUE, Color.BLUE, Color.RED, Color.GREEN, Color.GRAY, Color.PURPLE};
	
	private final double screenWidth;
	private final double screenHeight;
	
	private final Velocity velocity;
	private final Position position;
	
	private final List<Double> randomInitialPosition;
    private final List<Double> randomInitialVelocity;
    private final Random randomNumber;
    private double radius;
	
	Color chosenColor;
	
	public BallState(double screenWidth, double screenHeight) {
    	
    	this.screenWidth = screenWidth;
    	this.screenHeight = screenHeight;
    	randomNumber = new Random();
    	double boundRadius = Math.min(randomNumber.nextInt(20, 100), Math.min(screenWidth, screenHeight) );
		double[] boundPosition = {0 + boundRadius, Math.min(screenWidth, screenHeight) - boundRadius};
		double[] boundVelocity = {-50, 50};
		
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
		radius = boundRadius;
		
		Color randomColor = color[randomNumber.nextInt(0, color.length)];
		chosenColor = randomColor;
    	
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
		this.chosenColor = chosenColor;
	}

	public double getScreenWidth() {
		return screenWidth;
	}

	public double getScreenHeight() {
		return screenHeight;
	}

	public Velocity getVelocity() {
		return velocity;
	}

	public Position getPosition() {
		return position;
	}

	public List<Double> getRandomInitialPosition() {
		return randomInitialPosition;
	}

	public List<Double> getRandomInitialVelocity() {
		return randomInitialVelocity;
	}
    

}
