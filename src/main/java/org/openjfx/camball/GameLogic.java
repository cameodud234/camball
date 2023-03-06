package org.openjfx.camball;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jblas.DoubleMatrix;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Velocity;
import org.openjfx.simobjects.Ball;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameLogic {
	
	private final Physics physics;
	
	private final double widthX;
	private final double widthY;
	
	private double positionY;
	private boolean movingUp;
	private boolean movingDown;
	
	private double positionX;
	private boolean movingLeft;
	private boolean movingRight;
	
	private Ball ball;
	
	final Logger log = LogManager.getLogger(GameLogic.class);
	
	public GameLogic(double widthX, double widthY, Physics physics) {
		
		this.widthX = widthX;
		this.widthY = widthY;
		this.physics = physics;
		
		positionY = 100;
		movingUp = false;
		movingDown = true;
		
		positionX = 100;
		movingLeft = false;
		movingRight = true;
		
		Velocity intialVelocityBall = new Velocity(30, 20); // In meters per second
		double radiusBall = 5;
		this.ball = new Ball(intialVelocityBall, positionX, positionY, radiusBall, Color.WHITE);

	}
	
	public void update() {
		
		DoubleMatrix pixelMoveRate = physics.getPixelMoveRate(ball.getVelocity());
        log.info("pixelMoveRate: {}", pixelMoveRate.toString());
		
		if(ball.getCenterY() + ball.getRadius() < widthY && movingDown) {
            movingUp = false;
            movingDown = true;
            positionY += pixelMoveRate.get(1);
            
        } else {
            movingUp = true;
            movingDown = false;
            ball.setVelocity(ball.getVelocity().getSpeedX(), - ball.getVelocity().getSpeedY());
            positionY -= pixelMoveRate.get(1);
            if(ball.getCenterY() - ball.getRadius() <= 0) {
                movingUp = false;
                movingDown = true;
            }
        }
        
        if(ball.getCenterX() + ball.getRadius() < widthX && movingRight) {
            movingLeft = false;
            movingRight = true;
            positionX += pixelMoveRate.get(0);
        } else {
            movingLeft = true;
            movingRight = false;
            ball.setVelocity( - ball.getVelocity().getSpeedX(), ball.getVelocity().getSpeedY());
            positionX -= pixelMoveRate.get(0);
            if(ball.getCenterX() - ball.getRadius() <= 0) {
                movingLeft = false;
                movingRight = true;
            }
        }
        
        
        ball.move(positionX, positionY);
        
        log.info("Position: [{}, {}]", ball.getCenterX(), ball.getCenterY());
        
	}
	
	public Ball getBall() {
		return ball;
	}
	
}
