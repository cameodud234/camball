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
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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

	private Set<BallState> ballStates;
	private List<Circle> ballTrail;
	
	 
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
		
		int ballCount = 20;
		
		for(int i = 0; i < ballCount; i++) {
			ballStates.add(new BallState(physics));
		}
		
		
		double radius1 = 100;
		double mass1 = 1500;
		Position position1 = new Position(physics.getScreenWidth() - radius1, physics.getScreenHeight()/2);
		Velocity velocity1 = new Velocity(100, 100);
		BallState ballState1 = new BallState(velocity1, position1, radius1, Color.ALICEBLUE, mass1, physics);
		ballStates.add(ballState1);
//		
//		double radius2 = 100;
//		double mass2 = 2;
//		Position position2 = new Position(physics.getScreenWidth() - Math.min(200, physics.getScreenWidth() - radius1), physics.getScreenHeight()/2);
//		Velocity velocity2 = new Velocity(-50, 20);
////		
//		double radius3 = 100;
//		double mass3 = 100;
//		Position position3 = new Position(Math.min(200, physics.getScreenWidth() + radius1), physics.getScreenHeight()/4);
//		Velocity velocity3 = new Velocity(-10, -10);
////		

//		BallState ballState2 = new BallState(velocity2, position2, radius2, Color.BEIGE, mass2, physics);
//		BallState ballState3 = new BallState(velocity3, position3, radius3, Color.CYAN, mass3, physics);
////		

//		ballStates.add(ballState2);
//		ballStates.add(ballState3);
		
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
        	
        	Set<Circle> circles = new HashSet<>();
        	for(BallState ballState: ballStates) {
        		ballState.update();
        		Circle circle = new Circle(ballState.getPosition().getX(), ballState.getPosition().getY(), ballState.getRadius(), ballState.getColor());
            	circles.add(circle);
        	}
        	
        	root.getChildren().clear();
        	root.getChildren().addAll(circles);
            lastTime = now;
            timer++;
            log.info("The current frame is: {}", timer);
//            log.info("Position: [{}, {}]", ball.getCenterX(), ball.getCenterY());
//            log.info("Physics Move Rate: [{}, {}]", pixelMoveRate.get(0), pixelMoveRate.get(1));
            
        }
    }  
    
}