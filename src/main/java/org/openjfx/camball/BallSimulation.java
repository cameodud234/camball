package org.openjfx.camball;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjfx.objects.Ball;
import org.openjfx.objects.BallState;
import org.openjfx.physics.Collisions;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Velocity;
import org.openjfx.physics.VelocityException;

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
    
    Map<Ball, BallState> balls_ballStates;
    
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
		
//		ballStates = new HashSet<BallState>();
		balls_ballStates = new HashMap<>();
		
		ballCount = 2;
		
		for(int i = 0; i < ballCount; i++) {
			BallState ballState = new BallState(width, height, physics);
			Ball ball = new Ball(ballState.getVelocity(), ballState.getPosition(), 
					ballState.getRadius(), ballState.getColor(), ballState.getMass(), physics);
			balls_ballStates.put(ball, ballState);
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
        	
        	Set<Ball> ballKeys = balls_ballStates.keySet();
        	
        	for(Ball ball: ballKeys) {
        		
        		BallState ballState = balls_ballStates.get(ball);
        		ballState.update();
        		
        		ball.setVelocity(ballState.getVelocity());
        		ball.setPosition(ballState.getPosition());
        		ball.setRadius(ballState.getRadius());
        		ball.setFill(ballState.getColor());
        		ball.setMass(ballState.getMass());
        		
        	}
        	
        	
        	Map<Ball, Ball> collidingBalls_Balls = Collisions.calculateCollisions(balls_ballStates.keySet().stream().toList());
        	
        	if(!collidingBalls_Balls.isEmpty()) {
        		log.info("collidingBalls_Balls: [{}, {}]", collidingBalls_Balls.keySet());
        		Set<Ball> visitedBalls = new HashSet<>();
        		for(Ball ball: collidingBalls_Balls.keySet().stream().toList()) {
        			Ball ball1 = ball;
        			Ball ball2 = collidingBalls_Balls.get(ball);
        			if((visitedBalls.contains(ball1) && visitedBalls.contains(ball2))) {
        				continue;
        			}
        			else {
        				
        				try {
        					List<Velocity> newVelocities = Collisions.calculateCollisionVelocities(ball1, ball2);
        					BallState currentBallState1 = balls_ballStates.get(ball1);
							BallState currentBallState2 = balls_ballStates.get(ball2);
							currentBallState1.setVelocity(newVelocities.get(0));
							currentBallState2.setVelocity(newVelocities.get(1));
							currentBallState1.update(newVelocities.get(0));
							currentBallState2.update(newVelocities.get(1));
							ball1.setVelocity(currentBallState1.getVelocity());
							ball1.setPosition(currentBallState1.getPosition());
							ball2.setVelocity(currentBallState2.getVelocity());
							ball2.setPosition(currentBallState2.getPosition());
						} catch (VelocityException e) {
							log.error(e.getCause());
						}
        				
        				visitedBalls.add(ball1);
        				visitedBalls.add(ball2);
        				
        			}
        			
        		}
        		
        	}
        	
        	root.getChildren().clear();
            root.getChildren().addAll(balls_ballStates.keySet().stream().toList());
            lastTime = now;
            timer++;
            log.info("The current frame is: {}", timer);
//            log.info("Position: [{}, {}]", ball.getCenterX(), ball.getCenterY());
//            log.info("Physics Move Rate: [{}, {}]", pixelMoveRate.get(0), pixelMoveRate.get(1));
            
        }
    }  
    
}