package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.BallWorldStore;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class BallTest {

    @Test
    public void testdetectCollisionBoundary() {
        BallWorldStore store = new BallWorldStore();
        BallWorldStore.setCanvasDims(new Point2D.Double(800, 800));
        Point2D.Double dims = BallWorldStore.getCanvasDims();
        Ball ball = Ball.makeBall(BallWorldStore.getOnlyStratFac().make("circle"), BallWorldStore.getOnlyInteractionStratFac().make("changeBehavior"), dims);
        ball.setLocation(new Point2D.Double(800, 800));
        ball.setVelocity(new Point2D.Double(50, 500));
        ball.detectCollisionBoundary();

        assertEquals("isCollisionHappens", true, ball.detectCollisionBoundary());


    }
}