package org.openjfx.camball;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openjfx.physics.Physics;
import org.openjfx.physics.Position;
import org.openjfx.physics.Velocity;
import org.openjfx.simobjects.Ball;

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
		final double pixelToMeter = 50;
		final Physics physics = new Physics(framerate, pixelToMeter, widthX, widthY);
	  
		Group root = new Group();
   	  
		Scene scene = new Scene(root, widthX, widthY, Color.BLACK);
		
		GameLogic game = new GameLogic(widthX, widthY, physics);
   
		AnimationTimer timer = new AnimationTimer() {
		    
		    private final long frameInterval = (long) (( (1/framerate) * Math.pow(10, 9)));
		    
		    private long lastTime = 0;
		    
		    private long timer = 0;
		   
		    private Velocity velocity = new Velocity(2, 3);
		    private Position position = new Position(100, 100);
		    private double radius = 20;
		    
		    private double deltaX = 4;
		    private double deltaY = 3;
		    
		    @Override
		    public void handle(long now) {
		        if(lastTime == 0) {
		            lastTime = now;
		            return;
		        }
		        
		        if(now - lastTime > frameInterval) {
//		            game.update();
		            Ball ball = new Ball(velocity, physics, position.getPositionX(), position.getPositionY(), widthX, widthY, radius, Color.ANTIQUEWHITE);
		            log.info("Position: [{}, {}]", ball.getCenterX(), ball.getCenterY());
		            root.getChildren().clear();
		            root.getChildren().add(ball);
		            
		            lastTime = now;
		            timer++;
		            log.info("The current frame is: {}", timer);
		            
		            
		            position.setPositionX(ball.getCenterX() + deltaX);
		            position.setPositionY(ball.getCenterY() + deltaY);
		            
		            
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