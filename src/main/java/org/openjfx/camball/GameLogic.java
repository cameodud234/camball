package org.openjfx.camball;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Velocity;
import org.openjfx.simobjects.Ball;

import javafx.scene.paint.Color;

public class GameLogic {
	
	private final Physics physics;
	
	private final double widthX;
	private final double widthY;
	
	private Ball ball;
	
	final Logger log = LogManager.getLogger(GameLogic.class);
	
	public GameLogic(double widthX, double widthY, Physics physics) {
		
		this.widthX = widthX;
		this.widthY = widthY;
		this.physics = new Physics(physics.getFramerate(), physics.getPixelToMeter());
		
		double centerX = 100;
		double centerY = 100;
		double radius = 50;
		
		Velocity initialBallVelocity = new Velocity(80, 60); // In meters per second
		ball = new Ball(initialBallVelocity, physics, centerX, centerY, widthX, widthY, radius, Color.WHITE);

	}
	
	public void update() {

        log.info("Position: [{}, {}]", ball.getCenterX(), ball.getCenterY());
        
        ball.move();
        
	}
	
	public Ball getBall() {
		return ball;
	}
	
}
