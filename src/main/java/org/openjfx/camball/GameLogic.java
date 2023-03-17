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
	
//	private final Physics physics;
//	
//	private final double screenWidthX;
//	private final double screenWidthY;
//	
//	private final Velocity velocity;
//	private final Position position;
//	private double radius;
//	
//	final Logger log = LogManager.getLogger(GameLogic.class);
//	
//	public GameLogic(double screenWidthX, double screenWidthY, Physics physics) {
//		
//		this.screenWidthX = screenWidthX;
//		this.screenWidthY = screenWidthY;
//		
//		// put screenWidthX and screenWidthY in physics and use this to get ball bounds in ball class
//		this.physics = new Physics(physics.getFramerate(), physics.getPixelToMeter(), 
//				screenWidthX, screenWidthY);
//		
//		
//		velocity = new Velocity(-80, -60); // In meters per second
//		position = new Position(100, 100);
//		radius = 50;
//		
//	}
	
	public void move(Ball ball) {

		
//		if(super.getCenterX() - super.getRadius() <= 0) {
//        	deltaX = Math.abs(pixelMoveRate.get(0));
//        }
//        else if(super.getCenterX() + super.getRadius() >= boundX) {
//        	deltaX = -Math.abs(pixelMoveRate.get(0));
//        }
//        
//        if(super.getCenterY() - super.getRadius() <= 0) {
//        	deltaY = Math.abs(pixelMoveRate.get(1));
//        }
//        else if(super.getCenterY() + super.getRadius() >= boundY) {
//        	deltaY = -Math.abs(pixelMoveRate.get(1));
//        }
        
//        double centerX = super.getCenterX() + deltaX;
//        double centerY = super.getCenterY() + deltaY;
		
        
//        super.setCenterX(super.getCenterX() + deltaX);
//        super.setCenterY(super.getCenterY() + deltaY);
		
	}
	
	public void update() {

//        log.info("Position: [{}, {}]", ball1.getCenterX(), ball1.getCenterY());
        
//        ball1.setCenterX(ball1.getCenterX() + 3);
//        ball1.move();
		
//		ball1.setCenterX(4);
        
	}
	
//	public Ball getBall() {
//		Ball ball = new Ball(velocity, physics, position.getPositionX(), position.getPositionY(), 
//				screenWidthX, screenWidthY, radius, Color.WHITE);
//		
//		return ball;
//	}
	
}
