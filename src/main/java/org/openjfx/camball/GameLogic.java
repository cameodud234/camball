package org.openjfx.camball;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jblas.DoubleMatrix;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Velocity;
import org.openjfx.simobjects.Ball;

import javafx.scene.paint.Color;

public class GameLogic {
	
	private final Physics physics;
	
	private final double widthX;
	private final double widthY;
	
	private double centerX;
	private double centerY;
	
	private double radius;
	
	private double deltaX;
	private double deltaY;
	
	private Ball ball;
	
	final Logger log = LogManager.getLogger(GameLogic.class);
	
	public GameLogic(double widthX, double widthY, Physics physics) {
		
		this.widthX = widthX;
		this.widthY = widthY;
		this.physics = physics;
		
		centerY = 100;
		centerX = 100;
		
		radius = 50;
		
		Velocity initialBallVelocity = new Velocity(80, 60); // In meters per second
		ball = new Ball(initialBallVelocity, centerX, centerY, radius, Color.WHITE);
		DoubleMatrix initialPixelMoveRate = physics.getPixelMoveRate(ball.getVelocity());
		deltaX = initialPixelMoveRate.get(0);
		deltaY = initialPixelMoveRate.get(1);

	}
	
	public void update() {
		
		DoubleMatrix pixelMoveRate = physics.getPixelMoveRate(ball.getVelocity());
        log.info("pixelMoveRate: {}", pixelMoveRate.toString());
       
        if(ball.getCenterX() - ball.getRadius() <= 0) {
        	deltaX = pixelMoveRate.get(0);
        }
        else if(ball.getCenterX() + ball.getRadius() >= widthX) {
        	deltaX = -pixelMoveRate.get(0);
        }
        
        if(ball.getCenterY() - ball.getRadius() <= 0) {
        	deltaY = pixelMoveRate.get(1);
        }
        else if(ball.getCenterY() + ball.getRadius() >= widthY) {
        	deltaY = -pixelMoveRate.get(1);
        }
        

        log.info("Position: [{}, {}]", ball.getCenterX(), ball.getCenterY());
        
        ball.move(deltaX, deltaY);
        
	}
	
	public Ball getBall() {
		return ball;
	}
	
}
