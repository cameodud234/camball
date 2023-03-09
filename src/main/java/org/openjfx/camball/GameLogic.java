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
        
        ball.move(deltaX, deltaY);
        
		
//		if(ball.getPositionY() + ball.getRadius() < widthY && movingDown) {
//            movingUp = false;
//            movingDown = true;
//            centerY += pixelMoveRate.get(1);
//            
//        } else {
//            movingUp = true;
//            movingDown = false;
//            ball.setVelocity(ball.getVelocity().getSpeedX(), - ball.getVelocity().getSpeedY());
//            centerY -= pixelMoveRate.get(1);
//            if(ball.getPositionY() - ball.getRadius() <= 0) {
//                movingUp = false;
//                movingDown = true;
//            }
//        }
//        
//        if(ball.getPositionX() + ball.getRadius() < widthX && movingRight) {
//            movingLeft = false;
//            movingRight = true;
//            centerX += pixelMoveRate.get(0);
//        } else {
//            movingLeft = true;
//            movingRight = false;
//            ball.setVelocity( - ball.getVelocity().getSpeedX(), ball.getVelocity().getSpeedY());
//            centerX -= pixelMoveRate.get(0);
//            if(ball.getPositionX() - ball.getRadius() <= 0) {
//                movingLeft = false;
//                movingRight = true;
//            }
//        }
        
        
        
//        ball.move(pixelMoveRate.get(0), pixelMoveRate.get(1));
       
        log.info("Position: [{}, {}]", ball.getCenterX(), ball.getCenterY());
        
	}
	
	public Ball getBall() {
		return ball;
	}
	
}
