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
import org.openjfx.physics.Position;
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

	Set<BallState> ballStates;
	 
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
		
		ballStates = new HashSet<>();
	
		BallState ballState1 = new BallState(new Velocity(20, 0), new Position(400, physics.getScreenHeight()/2), 
				200, Color.ANTIQUEWHITE, 5, physics);
		
		BallState ballState2 = new BallState(new Velocity(-20, 0), new Position(physics.getScreenWidth() - 200, 
				physics.getScreenHeight()/2), 200, Color.BLUEVIOLET, 50, physics);
		
		ballStates.add(ballState1);
		ballStates.add(ballState2);
		
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
        	
        	Map<BallState, BallState> collisions = Collisions.calculateCollisions(ballStates.stream().toList());
        	
        	if(!collisions.isEmpty()) {
        		Set<BallState> visitedBallStates = new HashSet<>();
        		log.info("collisions: [{}, {}]", collisions.keySet());
        		for(BallState ballState: collisions.keySet()) {
        			BallState ballState1 = ballState;
        			BallState ballState2 = collisions.get(ballState);
        			if(!(visitedBallStates.contains(ballState1) && visitedBallStates.contains(ballState2))) {
        				log.info("ballstates Velocity: " + ballState1.getVelocity().getVelocity() + ", " + ballState2.getVelocity().getVelocity());
            			List<Velocity> newVelocities;
            			try {
    						newVelocities = Collisions.calculateCollisionVelocities(ballState1, ballState2);
    						log.info("newVelocities: " + newVelocities.get(0).getVelocity() + " : " + newVelocities.get(1).getVelocity());
    						ballState1.setVelocity(newVelocities.get(0));
    						ballState2.setVelocity(newVelocities.get(1));
    						visitedBallStates.add(ballState1);
                			visitedBallStates.add(ballState2);
    					} catch (Exception e) {
    						log.error(e.getCause() + " : " + e.getMessage());
    					}
            			
        			}
        		}
        	}
        	
        	Set<Ball> balls = new HashSet<>();
        	for(BallState ballState: ballStates) {
        		ballState.update();
        		Ball ball = new Ball(ballState.getPosition(), ballState.getRadius(), ballState.getColor(), physics);
            	balls.add(ball);
        	}
        	
        	root.getChildren().clear();
        	root.getChildren().addAll(balls.stream().toList());
            lastTime = now;
            timer++;
            log.info("The current frame is: {}", timer);
//            log.info("Position: [{}, {}]", ball.getCenterX(), ball.getCenterY());
//            log.info("Physics Move Rate: [{}, {}]", pixelMoveRate.get(0), pixelMoveRate.get(1));
            
        }
    }  
    
}