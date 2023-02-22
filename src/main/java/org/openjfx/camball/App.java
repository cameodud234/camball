package org.openjfx.camball;


/**
 * JavaFX App
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;


public class App extends Application {
	
	@Override
	public void start(Stage stage) {
		
		private final int widthX = 800;
		private final int widthY = 800;
		
		private final long framerateInterval = 16_666_67
		
	  
		Group root = new Group();
   	  
		Scene scene = new Scene(root, widthX, widthY, Color.WHITE);
		
		ToolBar toolbar = new ToolBar();
		
		root.getChildren().add(toolbar);
		
		GameLogic game = new GameLogic(widthX, widthY);
   
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				game.update();
				root.getChildren().setAll(game.getCircle());
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