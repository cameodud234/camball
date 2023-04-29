package org.openjfx.physics;

import javafx.scene.paint.Color;

public class BallIntializers {
	
	double framerate = 120;
	double screenWidth = 500;
	double screenHeight = 700;
	double pixelToMeter = 20;
	
	Physics physics = new Physics(framerate, pixelToMeter, screenWidth, screenHeight);
	
	Velocity velocity1 = new Velocity(-3, 24);
	Position position1 = new Position(98, 54);
	double radius1 = 10;
	Color color1 = Color.ALICEBLUE;
	
	Velocity velocity2 = new Velocity(-23, 44);
	Position position2 = new Position(200, 340);
	double radius2 = 33;
	Color color2 = Color.AQUAMARINE;
	
	Velocity velocity3 = new Velocity(36, 12);
	Position position3 = new Position(250, 540);
	double radius3 = 12;
	Color color3 = Color.BISQUE;
	
}
