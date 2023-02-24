package org.openjfx.camball;


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
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;

public class App extends Application {
	
	@Override
	public void start(Stage stage) {
		
		final Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		final double screenMaxBound = 0.80;
		
		final double widthX = screenBounds.getWidth() - ((1 - screenMaxBound) * screenBounds.getWidth());
		final double widthY = screenBounds.getHeight() - ((1 - screenMaxBound) * screenBounds.getHeight());
		
		final double framerate = 1;
	  
		Group root = new Group();
   	  
		Scene scene = new Scene(root, widthX, widthY, Color.BLACK);
		
		GameLogic game = new GameLogic(widthX, widthY);
   
		AnimationTimer timer = new AnimationTimer() {
			
			private final long frameInterval = (long) (( (1/framerate) * Math.pow(10, 9)));
			
			private long lastTime = 0; 
			
			@Override
			public void handle(long now) {
				if(lastTime == 0) {
					lastTime = now;
					return;
				}
				
				if(now - lastTime > frameInterval) {
					game.update();
					root.getChildren().setAll(game.getCircle());
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