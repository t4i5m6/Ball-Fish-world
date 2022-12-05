package edu.rice.comp504.model.strategy.interaction;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.Ball;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ChangeLocStrategyTest {

    @Test
    public void testupdateState() {
        BallWorldStore store = new BallWorldStore();
        BallWorldStore.setCanvasDims(new Point2D.Double(800, 800));
        Point2D.Double dims = BallWorldStore.getCanvasDims();

        Ball ball = Ball.makeBall(BallWorldStore.getOnlyStratFac().make("outAndBack"),
                BallWorldStore.getOnlyInteractionStratFac().make("changeLoc"), dims);

        Ball collisionBall = Ball.makeBall(BallWorldStore.getOnlyStratFac().make("horizontal"),
                BallWorldStore.getOnlyInteractionStratFac().make("changeDirection"), dims);


        ArrayList<Ball> balls = new ArrayList<Ball>();
        Point2D.Double originalLoc = collisionBall.getLocation();
        balls.add(collisionBall);

        ball.getInteractionStrategy().updateState(ball, balls, store);
        assertNotEquals("change the ball location when collide",  originalLoc, collisionBall.getLocation());
    }

}