package org.openjfx.camball;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjfx.objects.Ball;
import org.openjfx.objects.BallState;
import org.openjfx.physics.Collisions;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Velocity;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class BallSimulation extends AnimationTimer {
	
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
		
		ballCount = 2;
		
		for(int i = 0; i < ballCount; i++) {
			BallState ballState = new BallState(width, height, physics);
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
        				ballState.getRadius(), ballState.getColor(), ballState.getMass(), physics);
        		balls.add(ball);
        		
        	}
        	
        	for(BallState ballState: ballStates) {
        		ballState.update();
        	}
        	
        	Map<Ball, Ball> collidingBalls_Balls = Collisions.calculateCollisions(balls);
        	
        	if(!collidingBalls_Balls.isEmpty()) {
        		for(Ball ball: balls) {
        			if(collidingBalls_Balls.containsKey(ball)) {
        				Ball ball1 = ball;
        				Ball ball2 = collidingBalls_Balls.get(ball);
        				List<Velocity> velocities;
        				try {
        					velocities = Collisions.calculateCollisionVelocities(ball1, ball2);
        					ball1.setVelocity(velocities.get(0));
        					ball2.setVelocity(velocities.get(1));
        					int indexFind1 = 0;
        					int indexFind2 = 0;
        					for(int i = 0; i < ballCount; i++) {
        						if(ball1.equals(balls.get(i))) {
        							indexFind1 = i;
        						}
        					}
        					for(int i = 0; i < ballCount; i++) { 
        						if(ball2.equals(balls.get(i))) {
        							indexFind2 = i;
        						}
        					}
        					
        					BallState newBallState1 = ballStates.get(indexFind1);
        					BallState newBallState2 = ballStates.get(indexFind2);
        					newBallState1.setVelocity(velocities.get(0));
        					newBallState2.setVelocity(velocities.get(1));
        					
        					ballStates.set(indexFind1, newBallState1);
        					ballStates.set(indexFind2, newBallState2);
        					
        					balls.remove(ball1);
        					balls.remove(ball2);
        					
        					
						} catch (Exception e) {
							log.error(e.getCause() + ", " + e.getMessage());
						}
        			}
        		}
        	}
        	
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