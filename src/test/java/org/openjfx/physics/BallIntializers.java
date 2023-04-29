package org.openjfx.physics;

import javafx.scene.paint.Color;

public class BallIntializers {
	
	double framerate = 120;
	double screenWidth = 500;
	double screenHeight = 700;
	double pixelToMeter = 20;
	
	Physics physics = new Physics(framerate, pixelToMeter, screenWidth, screenHeight);
	
	Velocity velocity1 = new Velocity(-3, 24);
	Position position1 = new Position(50, 75);
	double mass1 = 4;
	double radius1 = 10;
	Color color1 = Color.ALICEBLUE;
	
	Velocity velocity2 = new Velocity(-23, 44);
	Position position2 = new Position(20, 34);
	double mass2 = 9;
	double radius2 = 8;
	Color color2 = Color.AQUAMARINE;
	
	Velocity velocity3 = new Velocity(36, 12);
	Position position3 = new Position(25, 54);
	double mass3 = 19;
	double radius3 = 12;
	Color color3 = Color.BISQUE;
	
	Velocity velocity4 = new Velocity(36, 12);
	Position position4 = new Position(80, 90);
	double mass4 = 45;
	double radius4 = 30;
	Color color4 = Color.BEIGE;
	
}