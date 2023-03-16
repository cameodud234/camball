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
			
			private double centerX = 450;
			private double centerY = 500;
			
			private Position circle1Position = new Position(centerX, centerY);
			private Position circle2Position = new Position(centerX - 100, centerY - 100);
			
			@Override
			public void handle(long now) {
				if(lastTime == 0) {
					lastTime = now;
					return;
				}
				
				if(now - lastTime > frameInterval) {
					game.update();
					Circle circle1 = new Circle(circle1Position.getPositionX(), circle1Position.getPositionY(), 20, Color.ALICEBLUE);
					Circle circle2 = new Circle(circle2Position.getPositionX(), circle2Position.getPositionY(), 30, Color.ALICEBLUE);
					List<Circle> circles = new ArrayList<>(List.of(circle1, circle2));
					root.getChildren().clear();
					root.getChildren().addAll(circles);
					lastTime = now;
					timer++;
					log.info("The current frame is: {}", timer);
					
					circle1Position.setPositionX(circle1Position.getPositionX() + 3);
					circle1Position.setPositionY(circle1Position.getPositionY() - 2);
					
					circle2Position.setPositionX(circle2Position.getPositionX() + 3);
					circle2Position.setPositionY(circle2Position.getPositionY() + 2);
					
					circle1.setCenterX(circle1Position.getPositionX());
					circle1.setCenterY(circle1Position.getPositionY());
					
					circle2.setCenterX(circle2Position.getPositionX());
					circle2.setCenterY(circle2Position.getPositionY());
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