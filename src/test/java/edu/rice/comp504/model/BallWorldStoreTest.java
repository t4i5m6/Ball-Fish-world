package edu.rice.comp504.model;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.model.strategy.behavior.AccelerationStrategy;
import edu.rice.comp504.model.strategy.behavior.CircleStrategy;
import edu.rice.comp504.model.strategy.behavior.OutAndBackStrategy;
import edu.rice.comp504.model.strategy.behavior.RectangleStrategy;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class BallWorldStoreTest {


    @Test
    public void testUpdateBallWorld() {
        BallWorldStore store = new BallWorldStore();
        BallWorldStore.setCanvasDims(new Point2D.Double(800, 800));

        Ball horizontalBall = (Ball) store.loadPaintObj("horizontal changeDirection", "ball", "");
        horizontalBall.setRadius(50);
        horizontalBall.setLocation(new Point2D.Double(400, 400));
        horizontalBall.setVelocity(new Point2D.Double(77, 0));

        store.updateBallWorld();
        assertEquals("update horizontal ball location test", new Point2D.Double(477, 400) , horizontalBall.getLocation());


        store.removeBallsFromStore();
        Ball circleBall = (Ball) store.loadPaintObj("circle changeDirection", "ball", "");
        circleBall.setRadius(50);
        circleBall.setLocation(new Point2D.Double(400, 400));
        circleBall.setVelocity(new Point2D.Double(50, 50));
        double cos = Math.cos(Math.toRadians(10));
        double sin = Math.sin(Math.toRadians(10));
        double new_x = 400 + 50 * (Math.cos(Math.toRadians(10)) - Math.sin(Math.toRadians(10)));
        double new_y = 400 + 50 * (Math.cos(Math.toRadians(10)) + Math.sin(Math.toRadians(10)));

        store.updateBallWorld();
        assertEquals("update circle ball location test", new Point2D.Double(new_x, new_y) , circleBall.getLocation());

        store.removeBallsFromStore();
        Ball squareBall = (Ball) store.loadPaintObj("square changeDirection", "ball", "");
        squareBall.setRadius(50);
        squareBall.setLocation(new Point2D.Double(400, 400));
        squareBall.setVelocity(new Point2D.Double(50, 0));

        store.updateBallWorld();
        assertEquals("update square ball location x test", new Point2D.Double(450, 400) , squareBall.getLocation());

        for (int i = 0 ; i < 4 ; i++) {
            store.updateBallWorld();
        }
        assertEquals("update square ball location y test", new Point2D.Double(550, 500) , squareBall.getLocation());

        store.removeBallsFromStore();
        Ball recBall = (Ball) store.loadPaintObj("rectangle changeDirection", "ball", "");
        recBall.setRadius(50);
        recBall.setLocation(new Point2D.Double(400, 400));
        recBall.setVelocity(new Point2D.Double(50, 0));

        store.updateBallWorld();
        store.updateBallWorld();
        store.updateBallWorld();
        store.updateBallWorld();
        assertEquals("update rec ball location", new Point2D.Double(550, 425) , recBall.getLocation());

        store.removeBallsFromStore();
        Ball triBall = (Ball) store.loadPaintObj("triangle changeDirection", "ball", "");
        triBall.setRadius(50);
        triBall.setLocation(new Point2D.Double(400, 400));
        triBall.setVelocity(new Point2D.Double(50, 0));

        store.updateBallWorld();
        store.updateBallWorld();
        store.updateBallWorld();
        store.updateBallWorld();
        assertEquals("update triangle ball location", new Point2D.Double(525, 443.30127018922195) , triBall.getLocation());



        Ball transformBall = (Ball) store.loadPaintObj("transform changeDirection", "ball", "");
        Point2D.Double originalPoint = transformBall.getLocation();

        store.updateBallWorld();
        assertEquals("update transform ball location test", originalPoint , transformBall.getLocation());
    }


    @Test
    public void testLoadBall() {
        BallWorldStore store = new BallWorldStore();

        BallWorldStore.setCanvasDims(new Point2D.Double(800, 800));
        Ball accelBall = (Ball) store.loadPaintObj("acceleration changeDirection", "ball", "");
        assertEquals("load horizontal ball type test", AccelerationStrategy.make(), accelBall.getStrategy());


        Ball circleBall = (Ball) store.loadPaintObj("outAndBack changeDirection", "ball", "");
        assertEquals("load circle ball type test", OutAndBackStrategy.make(), circleBall.getStrategy());


        Ball squareBall = (Ball) store.loadPaintObj("rectangle changeDirection", "ball", "");
        assertEquals("load square ball type test", RectangleStrategy.make(), squareBall.getStrategy());

    }

    @Test
    public void testRemoveBallsFromStore() {
        BallWorldStore store = new BallWorldStore();

        BallWorldStore.setCanvasDims(new Point2D.Double(800, 800));
        store.loadPaintObj("acceleration changeDirection", "ball", "");

        store.removeBallsFromStore();
        assertEquals("clear all balls test", 0, store.updateBallWorld().length);
    }

    @Test
    public void testAddBallToStore() {
        BallWorldStore store = new BallWorldStore();

        BallWorldStore.setCanvasDims(new Point2D.Double(800, 800));
        int number_of_balls = store.updateBallWorld().length;
        assertEquals("add balls test original", 0, number_of_balls);

        APaintObject object  = store.loadPaintObj("rectangle changeDirection", "ball", "Switch");
        object.setLocation(new Point2D.Double(500, 500));
        object = store.loadPaintObj("triangle changeDirection", "ball", "Switch");
        object.setLocation(new Point2D.Double(500, 500));
        object = store.loadPaintObj("acceleration changeColor", "ball", "Switch");
        object.setLocation(new Point2D.Double(500, 500));
        store.loadPaintObj("stopAndGo changeDirection", "ball", "Switch");
        store.loadPaintObj("teleport changeDirection", "ball", "Switch");
        store.loadPaintObj("transform", "fish", "Switch");
        store.loadPaintObj("outAndBack changeDirection", "ball", "Switch");
        number_of_balls = store.updateBallWorld().length;
        assertEquals("add balls test increase 1", 7, number_of_balls);
    }

    @Test
    public void testRemoveObjects() {
        BallWorldStore store = new BallWorldStore();

        BallWorldStore.setCanvasDims(new Point2D.Double(800, 800));
        store.loadPaintObj("acceleration changeDirection", "ball", "Switch");
        store.removeObjectsFromStore("acceleration");
        assertEquals("number of balls in store", 0, store.getBalls().length);

    }

    @Test
    public void testSwitchStrategy() {
        BallWorldStore store = new BallWorldStore();

        BallWorldStore.setCanvasDims(new Point2D.Double(800, 800));
        store.loadPaintObj("acceleration changeDirection", "ball", "Switch");
        store.switchStrategy("circle");

        assertEquals("strategy changed", CircleStrategy.make(), ((Ball)store.getBalls()[0]).getStrategy());

    }


}