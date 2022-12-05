package edu.rice.comp504.model.strategy.interaction;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.Ball;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SpawnStrategyTest {

    @Test
    public void testupdateState() {
        BallWorldStore store = new BallWorldStore();
        BallWorldStore.setCanvasDims(new Point2D.Double(800, 800));
        Point2D.Double dims = BallWorldStore.getCanvasDims();

        Ball ball = (Ball) store.loadPaintObj("horizontal spawn", "ball", "");
        Ball collisionBall = (Ball) store.loadPaintObj("horizontal changeDirection", "ball", "");


        ball.setLocation(new Point2D.Double(500, 500));
        ball.setRadius(100);

        collisionBall.setLocation(new Point2D.Double(400, 500));
        collisionBall.setRadius(10);

        ArrayList<Ball> balls = new ArrayList<Ball>();
        balls.add(collisionBall);

        assertEquals("number of balls",  2, store.getBalls().length);

        ball.getInteractionStrategy().updateState(ball, balls, store);
        assertEquals("Spawn a new Ball",  3, store.getBalls().length);

        Ball newBall = null;
        for (PropertyChangeListener Aball: store.getBalls()){
            if((Ball) Aball != collisionBall && (Ball) Aball != ball) {
                newBall = (Ball) Aball;
                break;
            }
        }
        assertEquals("Raduis of new Ball equals half of collision balls' radius",  55, newBall.getRadius());
    }
}