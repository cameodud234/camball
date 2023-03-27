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
		    
			private final Random randomNumber = new Random();
			double boundRadius = Math.min(randomNumber.nextInt(20, 100), Math.min(screenBounds.getWidth(), screenBounds.getHeight()) );
			double[] boundPosition = {0 + boundRadius, Math.min(screenBounds.getWidth(), screenBounds.getHeight()) - boundRadius};
			double[] boundVelocity = {-50, 50};
			
			double[] randomInitialVelocity = {randomNumber.nextDouble(boundVelocity[0], boundVelocity[1]), randomNumber.nextDouble(boundVelocity[0], boundVelocity[1])};
			double[] randomInitialPosition = {randomNumber.nextDouble(boundPosition[0], boundPosition[1]), randomNumber.nextDouble(boundPosition[0], boundPosition[1])};
			
			private final Velocity velocity = new Velocity(randomInitialVelocity[0], randomInitialVelocity[1]);
			private final Position position = new Position(randomInitialPosition[0], randomInitialPosition[1]);
			private double radius = boundRadius;
			
			private DoubleMatrix pixelMoveRate = physics.getPixelMoveRate(velocity);
			private double[] delta = {pixelMoveRate.get(0), pixelMoveRate.get(1)};
			
			Color[] color = {Color.ALICEBLUE, Color.BLUE, Color.RED, Color.GREEN, Color.GRAY, Color.PURPLE};
			
			Color randomColor = color[randomNumber.nextInt(0, color.length)];
			Color chosenColor = randomColor;
			private Circle circle = new Circle(randomNumber.nextDouble(boundPosition[0], boundPosition[1]), randomNumber.nextDouble(boundPosition[0], boundPosition[1]), 
					randomNumber.nextDouble(boundRadius), randomColor);
			
			
			private double lastTime = 0;
			double timer = 0;
			
		    
		    @Override
		    public void handle(long now) {
		        if(lastTime == 0) {
		            lastTime = now;
		            return;
		        }
		        
		        if(now - lastTime > frameInterval) {
		        	
		        	
		        	Circle circle = new Circle(position.getPositionX(), position.getPositionY(), radius, chosenColor);
					
					if(circle.getCenterX() - circle.getRadius() <= 0) {
						delta[0] = Math.abs(pixelMoveRate.get(0));
			        }
			        else if(circle.getCenterX() + circle.getRadius() >= widthX) {
			        	delta[0] = -Math.abs(pixelMoveRate.get(0));
			        }
			        
			        if(circle.getCenterY() - circle.getRadius() <= 0) {
			        	delta[1] = Math.abs(pixelMoveRate.get(1));
			        }
			        else if(circle.getCenterY() + circle.getRadius() >= widthY) {
			        	delta[1] = -Math.abs(pixelMoveRate.get(1));
			        }
			        
			        circle.setCenterX(circle.getCenterX() + delta[0]);
			        circle.setCenterY(circle.getCenterY() + delta[1]);
		        	
		        	position.setPositionX(circle.getCenterX() + delta[0]);
		        	position.setPositionY(circle.getCenterY() + delta[1]);
		        	
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