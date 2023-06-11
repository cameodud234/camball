package org.openjfx.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.openjfx.objects.BallState;

public class TestCollisions extends BallIntializers {
	
	BallState ballState1 = new BallState(velocity1, position1, radius1, color1, mass1, physics);
	
	BallState ballState2 = new BallState(velocity2, position2, radius2, color2, mass2, physics);
	
	BallState ballState3 = new BallState(velocity3, position3, radius3, color3, mass3, physics);
	
	BallState ballState4 = new BallState(velocity4, position4, radius4, color4, mass4, physics);
	
	List<BallState> ballStates = new ArrayList<BallState>(List.of(ballState1, ballState2, ballState3, ballState4));
	
	
	@Test
	public void testCollisionsCalculateCollisionsNotEquals() {
		Map<BallState, BallState> actualCollidingBalls = new HashMap<>();
		actualCollidingBalls.put(ballState1, ballState4);
		actualCollidingBalls.put(ballState4, ballState1);
		Map<BallState, BallState> collidingBalls = Collisions.calculateCollisions(ballStates);
		assertNotEquals(collidingBalls, actualCollidingBalls);
	}
	
	@Test
	public void testCollisionsCalculateCollisionsEquals() {
		Map<BallState, BallState> actualCollidingBalls = new HashMap<>();
		actualCollidingBalls.put(ballState2, ballState3);
		actualCollidingBalls.put(ballState3, ballState2);
		Map<BallState, BallState> collidingBalls = Collisions.calculateCollisions(ballStates);
		assertNotEquals(collidingBalls, actualCollidingBalls);
	}
	
}