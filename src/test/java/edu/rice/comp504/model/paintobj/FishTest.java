package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.BallWorldStore;
import java.awt.geom.Point2D;
import org.junit.Test;

import static org.junit.Assert.*;

public class FishTest {

    @Test
    public void testdetectCollisionBoundary() {
        BallWorldStore store = new BallWorldStore();
        BallWorldStore.setCanvasDims(new Point2D.Double(800, 800));
        Point2D.Double dims = BallWorldStore.getCanvasDims();
        Fish fish = Fish.makeFish(BallWorldStore.getOnlyStratFac().make("horizontal"), dims);
        fish.setLocation(new Point2D.Double(780, 600));
        fish.setVelocity(new Point2D.Double(50, 0));
        fish.setSideLength(20);

        fish.detectCollisionBoundary();
        assertEquals("change fish direction when collide with the wall", new Point2D.Double(-50, 0) ,fish.vel);
    }
}