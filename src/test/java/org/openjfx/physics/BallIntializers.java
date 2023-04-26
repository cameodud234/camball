package org.openjfx.physics;

import javafx.scene.paint.Color;

public class BallIntializers {
	
	Velocity velocity1 = new Velocity(-23, 44);
	Position position1 = new Position(250, 540);
	double radius1 = 0.1;
	Color color1 = Color.ALICEBLUE;
	double framerate1 = 120;
	double screenWidth1 = 500;
	double screenHeight1 = 700;
	double pixelToMeter1 = 20;
	Physics physics1 = new Physics(framerate1, pixelToMeter1, screenWidth1, screenHeight1);
	
	Velocity velocity2 = new Velocity(-23, 44);
	Position position2 = new Position(250, 540);
	double radius2 = 0.1;
	Color color2 = Color.ALICEBLUE;
	double framerate2 = 120;
	double screenWidth2 = 500;
	double screenHeight2 = 700;
	double pixelToMeter2 = 20;
	Physics physics2 = new Physics(framerate2, pixelToMeter2, screenWidth2, screenHeight2);
	
	Velocity velocity3 = new Velocity(-23, 44);
	Position position3 = new Position(250, 540);
	double radius3 = 0.1;
	Color color3 = Color.ALICEBLUE;
	double framerate3 = 120;
	double screenWidth3 = 500;
	double screenHeight3 = 700;
	double pixelToMeter3 = 20;
	Physics physics3 = new Physics(framerate3, pixelToMeter3, screenWidth3, screenHeight3);
	
}
