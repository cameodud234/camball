package org.openjfx.camball;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jblas.DoubleMatrix;
import org.openjfx.objects.Ball;
import org.openjfx.objects.BallState;
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
    
    List<Ball> balls;
    List<BallState> ballStates;
    
    private final int ballCount;

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
		
		balls = new ArrayList<Ball>();
		ballStates = new ArrayList<BallState>();
		
		ballCount = 9;
		
		for(int i = 0; i < ballCount; i++) {
			BallState ballState = new BallState(width, height);
			ballStates.add(ballState);
		}
		
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
        	
        	balls.clear();
        	
        	for(int i = 0; i < ballCount; i++) {
        		
        		BallState ballState = ballStates.get(i);
        		Ball ball = new Ball(ballState.getVelocity(), ballState.getPosition(),
        				ballState.getRadius(), ballState.getColor(), physics);
        		balls.add(ball);
        		
        	}	
        	
//        	ballState.update();
        	
        	root.getChildren().clear();
            root.getChildren().addAll(balls);
            lastTime = now;
            timer++;
            log.info("The current frame is: {}", timer);
//            log.info("Position: [{}, {}]", ball.getCenterX(), ball.getCenterY());
//            log.info("Physics Move Rate: [{}, {}]", pixelMoveRate.get(0), pixelMoveRate.get(1));
            
        }
    }
    
    
    
}