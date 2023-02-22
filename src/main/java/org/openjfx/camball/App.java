package org.openjfx.camball;


/**
 * JavaFX App
 */
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class App extends Application {
	
	@Override
	public void start(Stage stage) {
		
		final int widthX = 800;
		final int widthY = 800;
	  
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