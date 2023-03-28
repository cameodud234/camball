package org.openjfx.camball;

import java.util.Random;

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
		
		final double width = screenBounds.getWidth() - ((1 - screenMaxBound) * screenBounds.getWidth());
		final double height = screenBounds.getHeight() - ((1 - screenMaxBound) * screenBounds.getHeight());
		
		final Logger log = LogManager.getLogger(App.class);
		
		final double framerate = 240;
		final double pixelToMeter = 60;
		final Physics physics = new Physics(framerate, pixelToMeter, width, height);
	  
		Group root = new Group();
   	  
		Scene scene = new Scene(root, width, height, Color.BLACK);
		
   
		BallSimulation ballSimulation = new BallSimulation(root, width, height, framerate, pixelToMeter);

	  
		ballSimulation.start();
      
		stage.setScene(scene);
		
		stage.setOnCloseRequest(e -> Platform.exit());
    
		stage.show();
  
	}

  
	public static void main(String[] args) {  
	  
		launch(args);
  
	}
	
}