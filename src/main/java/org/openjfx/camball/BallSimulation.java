package org.openjfx.camball;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jblas.DoubleMatrix;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Position;
import org.openjfx.physics.Velocity;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BallSimulation extends AnimationTimer {
	
	private static final Color[] color = {Color.ALICEBLUE, Color.BLUE, Color.RED, Color.GREEN, Color.GRAY, Color.PURPLE};
	private final Logger log = LogManager.getLogger(BallSimulation.class);
	private final Group root;
	private final Physics physics;
	private final double pixelToMeter;
	private final long frameInterval;
	private final double framerate;
    private final Random randomNumber;
    private final double width;
    private final double height;
    
    private final List<Double> randomInitialPosition;
    private final List<Double> randomInitialVelocity;
	
	private final Velocity velocity;
	private final Position position;
	private double radius;
	
	private DoubleMatrix pixelMoveRate;
	private List<Double> delta;

	Color chosenColor;
	
	private double lastTime;
	double timer;
	
	public BallSimulation(Group root, double width, double height, double framerate, double pixelToMeter) {
		
		this.root = root;
		this.width = width;
		this.height = height;
		
		this.physics = new Physics(framerate, pixelToMeter, width, height);
		randomNumber = new Random();
		this.framerate = framerate;
		this.frameInterval = (long) (( (1/framerate) * Math.pow(10, 9)));
		this.pixelToMeter = pixelToMeter;
		
		double boundRadius = Math.min(randomNumber.nextInt(20, 100), Math.min(width, height) );
		double[] boundPosition = {0 + boundRadius, Math.min(width, height) - boundRadius};
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
		
		lastTime = 0;
		timer = 0;
		
		
	}
	
    
    @Override
    public void handle(long now) {
        if(lastTime == 0) {
            lastTime = now;
            return;
        }
        
        if(now - lastTime > frameInterval) {
        	
        	
        	Circle circle = new Circle(position.getX(), position.getY(), radius, chosenColor);
			
			if(circle.getCenterX() - circle.getRadius() <= 0) {
				delta.set(0, Math.abs(pixelMoveRate.get(0)));
	        }
	        else if(circle.getCenterX() + circle.getRadius() >= width) {
	        	delta.set(0, -Math.abs(pixelMoveRate.get(0)));
	        }
	        
	        if(circle.getCenterY() - circle.getRadius() <= 0) {
	        	delta.set(1, Math.abs(pixelMoveRate.get(1)));
	        }
	        else if(circle.getCenterY() + circle.getRadius() >= height) {
	        	delta.set(1, -Math.abs(pixelMoveRate.get(1)));
	        }
	        
	        circle.setCenterX(circle.getCenterX() + delta.get(0));
	        circle.setCenterY(circle.getCenterY() + delta.get(1));
        	
        	position.setX(circle.getCenterX() + delta.get(0));
        	position.setY(circle.getCenterY() + delta.get(1));
        	
        	root.getChildren().clear();
            root.getChildren().add(circle);
            lastTime = now;
            timer++;
            log.info("The current frame is: {}", timer);
            log.info("Position: [{}, {}]", circle.getCenterX(), circle.getCenterY());
            log.info("Physics Move Rate: [{}, {}]", pixelMoveRate.get(0), pixelMoveRate.get(1));
            
        }
    }
    
    
    
}