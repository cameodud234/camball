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
	
	private final double screenWidth;
	private final double screenHeight;
	
	private final Velocity velocity;
	private final Position position;
	
	private final List<Double> randomInitialPosition;
    private final List<Double> randomInitialVelocity;
    private final Random randomNumber;
    private double radius;
    
    private DoubleMatrix pixelMoveRate;
	private List<Double> delta;
	
	Color chosenColor;
    
    public BallState(double screenWidth, double screenHeight, Physics physics) {
    	
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
		
		pixelMoveRate = physics.getPixelMoveRate(velocity);
		delta = new ArrayList<Double>(List.of(pixelMoveRate.get(0), pixelMoveRate.get(1)));
		
		Color randomColor = color[randomNumber.nextInt(0, color.length)];
		chosenColor = randomColor;
    	
    }

}
