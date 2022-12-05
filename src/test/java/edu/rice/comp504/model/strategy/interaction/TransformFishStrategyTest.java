package edu.rice.comp504.model.strategy.interaction;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.cmd.UpdateStateCmd;
import edu.rice.comp504.model.paintobj.Ball;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TransformFishStrategyTest {

    @Test
    public void testupdateState() {
        BallWorldStore store = new BallWorldStore();
        BallWorldStore.setCanvasDims(new Point2D.Double(800, 800));
        Point2D.Double dims = BallWorldStore.getCanvasDims();

        Ball ball = (Ball) store.loadPaintObj("horizontal transformFish", "ball", "");
        Ball collisionBall = (Ball) store.loadPaintObj("horizontal changeDirection", "ball", "");


        ArrayList<Ball> balls = new ArrayList<Ball>();
        balls.add(collisionBall);

        ball.getInteractionStrategy().updateState(ball, balls, store);
        assertEquals("the number of Fish in store", 1, store.getFish().length);
    }
}