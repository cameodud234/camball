package org.openjfx.camball;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jblas.DoubleMatrix;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Position;
import org.openjfx.physics.Velocity;
import org.openjfx.simobjects.Ball;

import javafx.scene.paint.Color;

public class GameLogic {
	
	private final Object lock = new Object();
	private GameLogicState state;
	private final Ball ball;
	
	final Logger log = LogManager.getLogger(GameLogic.class);
	
	public GameLogic(double screenWidthX, double screenWidthY, Physics physics) {
		
		state = new GameLogicState();
		Velocity velocity = new Velocity(90, 120);
		Position position = new Position(300, 300);
		double radius = 20;
		Color color = Color.ALICEBLUE;
		ball = new Ball(velocity, physics, position, screenWidthX, screenWidthY, radius, color);
		
	}
	
	public void updateState() {
		
		synchronized(lock) {
			log.info("Position: [{}, {}]", ball.getCenterX(), ball.getCenterY());
			
			ball.move();
			
		}
		
//        log.info("Position: [{}, {}]", ball1.getCenterX(), ball1.getCenterY());
        
//        ball1.setCenterX(ball1.getCenterX() + 3);
//        ball1.move();
		
//		ball1.setCenterX(4);
        
	}
	
	
	public void setState(GameLogicState state) {
		synchronized(lock) {
			
			// Change velocity here.
			
		}
	}
	
	public Ball getBall() {
		
		return ball;
	}
	
}
