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
	
	private Ball ball1;
	private Ball ball2;
	
	final Logger log = LogManager.getLogger(GameLogic.class);
	
	public GameLogic(double widthX, double widthY, Physics physics) {
		
		this.widthX = widthX;
		this.widthY = widthY;
		
		// put widthX and widthY in physics and use this to get ball bounds in ball class
		this.physics = new Physics(physics.getFramerate(), physics.getPixelToMeter());
		
		double centerX = 100;
		double centerY = 100;
		double radius = 50;
		
		Velocity initialBallVelocity1 = new Velocity(80, 60); // In meters per second
		ball1 = new Ball(initialBallVelocity1, physics, centerX, centerY, widthX, widthY, radius, Color.WHITE);
		
		Velocity initialBallVelocity2 = new Velocity(40, 60);
		ball2 = new Ball(initialBallVelocity2, physics, centerX, centerY, widthX, widthY, radius + 25, Color.AQUA);

	}
	
	public void update() {

//        log.info("Position: [{}, {}]", ball1.getCenterX(), ball1.getCenterY());
        
        ball1.move();
        ball2.move();
        
	}
	
	public Ball getBall1() {
		return ball1;
	}
	
	public Ball getBall2() {
		return ball2;
	}
	
}
