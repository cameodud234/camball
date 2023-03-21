package org.openjfx.camball;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jblas.DoubleMatrix;
import org.openjfx.physics.Physics;
import org.openjfx.physics.Position;
import org.openjfx.physics.Velocity;

/**
 * JavaFX App
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class App extends Application {
	
	@Override
	public void start(Stage stage) {
		
		final Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		final double screenMaxBound = 0.90;
		
		final double widthX = screenBounds.getWidth() - ((1 - screenMaxBound) * screenBounds.getWidth());
		final double widthY = screenBounds.getHeight() - ((1 - screenMaxBound) * screenBounds.getHeight());
		
		final Logger log = LogManager.getLogger(App.class);
		
		final double framerate = 240;
		final double pixelToMeter = 60;
		final Physics physics = new Physics(framerate, pixelToMeter, widthX, widthY);
	  
		Group root = new Group();
   	  
		Scene scene = new Scene(root, widthX, widthY, Color.BLACK);
		
		
   
		AnimationTimer timer = new AnimationTimer() {
		    
		    private final long frameInterval = (long) (( (1/framerate) * Math.pow(10, 9)));
		    
		    private long lastTime = 0;
		    
		    private long timer = 0;
		    
		    Velocity velocity = new Velocity(10, -10);
			Position position = new Position(80, 80);
			DoubleMatrix pixelMoveRate = physics.getPixelMoveRate(velocity);
			double deltaX = pixelMoveRate.get(0);
			double deltaY = pixelMoveRate.get(1);
			double radius = 20;
			Color color = Color.ALICEBLUE;
		    
		    @Override
		    public void handle(long now) {
		        if(lastTime == 0) {
		            lastTime = now;
		            return;
		        }
		        
		        if(now - lastTime > frameInterval) {
		        	
		        	
		        	Circle circle = new Circle(position.getPositionX(), position.getPositionY(), radius, color);
					
					if(circle.getCenterX() - circle.getRadius() <= 0) {
						deltaX = Math.abs(pixelMoveRate.get(0));
			        }
			        else if(circle.getCenterX() + circle.getRadius() >= widthX) {
			        	deltaX = -Math.abs(pixelMoveRate.get(0));
			        }
			        
			        if(circle.getCenterY() - circle.getRadius() <= 0) {
			        	deltaY = Math.abs(pixelMoveRate.get(1));
			        }
			        else if(circle.getCenterY() + circle.getRadius() >= widthY) {
			        	deltaY = -Math.abs(pixelMoveRate.get(1));
			        }
			        
			        circle.setCenterX(circle.getCenterX() + deltaX);
			        circle.setCenterY(circle.getCenterY() + deltaY);
		        	
		        	position.setPositionX(circle.getCenterX() + deltaX);
		        	position.setPositionY(circle.getCenterY() + deltaY);
		        	
		        	root.getChildren().clear();
		            root.getChildren().add(circle);
		            lastTime = now;
		            timer++;
		            log.info("The current frame is: {}", timer);
		            log.info("Position: [{}, {}]", circle.getCenterX(), circle.getCenterY());
		            log.info("Physics Move Rate: [{}, {}]", pixelMoveRate.get(0), pixelMoveRate.get(1));
		            
		        }
		    }
		};

	  
		timer.start();
      
		stage.setScene(scene);
		
		stage.setOnCloseRequest(e -> Platform.exit());
    
		stage.show();
  
	}

  
	public static void main(String[] args) {  
	  
		launch(args);
  
	}
	
}