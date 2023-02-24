package org.openjfx.camball;

import org.openjfx.simobjects.Ball;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameLogic {
	
	private final double widthX;
	private final double widthY;
	
	private double pixelY;
	private boolean movingUp;
	private boolean movingDown;
	
	private double pixelX;
	private boolean movingLeft;
	private boolean movingRight;
	
	private double deltaX;
	private double deltaY;
	
	private Circle circle;
	
	public GameLogic(double widthX, double widthY) {
		this.widthX = widthX;
		this.widthY = widthY;
		
		pixelY = 100;
		movingUp = false;
		movingDown = true;
		
		pixelX = 100;
		movingLeft = false;
		movingRight = true;
		
		deltaX = 1;
		deltaY = 1;
		
		circle = new Circle(pixelX, pixelY, 20);
		
		circle.setFill(Color.WHITE);

	}
	
	public void update() {
		
		if(circle.getCenterY() + circle.getRadius() < widthY - 1 && movingDown) {
            movingUp = false;
            movingDown = true;
            pixelY += deltaY;
        } else {
            movingUp = true;
            movingDown = false;
            pixelY -= deltaY;
            if(circle.getCenterY() - circle.getRadius() <= 0) {
                movingUp = false;
                movingDown = true;
            }
        }
        
        if(circle.getCenterX() + circle.getRadius() < widthX - 1 && movingRight) {
            movingLeft = false;
            movingRight = true;
            pixelX += deltaX;
        } else {
            movingLeft = true;
            movingRight = false;
            pixelX -= deltaX;
            if(circle.getCenterX() - circle.getRadius() <= 0) {
                movingLeft = false;
                movingRight = true;
            }
        }
		
		circle.setCenterX(pixelX);
		circle.setCenterY(pixelY);
		
	}
	
	public Circle getCircle() {
		return circle;
	}
	
}
