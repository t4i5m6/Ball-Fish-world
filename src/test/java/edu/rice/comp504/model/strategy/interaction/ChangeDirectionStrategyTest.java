package edu.rice.comp504.model.strategy.interaction;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.Ball;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ChangeDirectionStrategyTest {

    @Test
    public void testupdateState() {
        BallWorldStore store = new BallWorldStore();
        BallWorldStore.setCanvasDims(new Point2D.Double(800, 800));
        Point2D.Double dims = BallWorldStore.getCanvasDims();

        Ball ball = Ball.makeBall(BallWorldStore.getOnlyStratFac().make("horizontal"), BallWorldStore.getOnlyInteractionStratFac().make("changeDirection"), dims);
        Ball collisionBall = Ball.makeBall(BallWorldStore.getOnlyStratFac().make("horizontal"), BallWorldStore.getOnlyInteractionStratFac().make("changeDirection"), dims);

        ball.setVelocity(new Point2D.Double(-50, 0));
        collisionBall.setVelocity(new Point2D.Double(50, 0));
        ArrayList<Ball> balls = new ArrayList<Ball>();
        balls.add(collisionBall);

        ball.getInteractionStrategy().updateState(ball, balls, store);
        assertEquals("change the ball direction when collide",  new Point2D.Double(-50, 0), collisionBall.getVelocity());
    }
}