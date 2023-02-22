package org.openjfx.camball;

import org.openjfx.simobjects.Ball;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameLogic {
	
	private final int widthX;
	private final int widthY;
	
	private double pixelY;
	private boolean movingUp;
	private boolean movingDown;
	
	private double pixelX;
	private boolean movingLeft;
	private boolean movingRight;
	
	private double deltaX;
	private double deltaY;
	
	private Circle circle;
	
	public GameLogic(int widthX, int widthY) {
		this.widthX = widthX;
		this.widthY = widthY;
		
		pixelY = 100;
		movingUp = false;
		movingDown = true;
		
		pixelX = 100;
		movingLeft = false;
		movingRight = true;
		
		deltaX = 3;
		deltaY = 5;
		
		circle = new Circle(pixelX, pixelY, 20);
		
		circle.setFill(Color.BLUE);

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
