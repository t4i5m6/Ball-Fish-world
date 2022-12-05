package edu.rice.comp504.model.strategy.interaction;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.Ball;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ExplosionStrategyTest {

    @Test
    public void testupdateState() {
        BallWorldStore store = new BallWorldStore();
        BallWorldStore.setCanvasDims(new Point2D.Double(800, 800));
        Point2D.Double dims = BallWorldStore.getCanvasDims();

        Ball ball = (Ball) store.loadPaintObj("acceleration explosion", "ball", "");
        Ball collisionBall = (Ball) store.loadPaintObj("horizontal changeDirection", "ball", "");


        ball.setLocation(new Point2D.Double(500, 500));
        ball.setRadius(100);

        collisionBall.setLocation(new Point2D.Double(400, 500));
        collisionBall.setRadius(10);

        ArrayList<Ball> balls = new ArrayList<Ball>();
        balls.add(collisionBall);

        Ball outsideBall = (Ball) store.loadPaintObj("horizontal changeDirection", "ball", "");
        outsideBall.setLocation(new Point2D.Double(600, 500));
        outsideBall.setRadius(10);
        assertEquals("number of balls",  3, store.getBalls().length);

        ball.getInteractionStrategy().updateState(ball, balls, store);
        assertEquals("Destory all balls in the explosion range",  0, store.getBalls().length);
    }
}