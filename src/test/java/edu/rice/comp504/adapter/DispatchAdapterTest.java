package edu.rice.comp504.adapter;

import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.model.strategy.behavior.CircleStrategy;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class DispatchAdapterTest {

    @Test
    public void testupdateBallWorld() {
        DispatchAdapter dis = new DispatchAdapter();
        dis.setCanvasDims(new Point2D.Double(800, 800));
        dis.loadPaintObj("acceleration changeDirection", "ball", "Switch");
        dis.updateBallWorld();
        assertEquals("test update", 1, dis.updateBallWorld().length);
    }


    @Test
    public void switchStrategy() {
        DispatchAdapter dis = new DispatchAdapter();
        dis.setCanvasDims(new Point2D.Double(800, 800));
        dis.loadPaintObj("acceleration changeDirection", "ball", "Switch");

        assertEquals("test switch", CircleStrategy.make(), ((Ball) dis.switchStrategy("circle")[0]).getStrategy());
    }

    @Test
    public void removeObjects() {
        DispatchAdapter dis = new DispatchAdapter();
        dis.setCanvasDims(new Point2D.Double(800, 800));
        dis.loadPaintObj("acceleration changeDirection", "ball", "Switch");

        assertEquals("test remove", 0, dis.removeObjects("acceleration").length);
    }

    @Test
    public void removeAll() {
        DispatchAdapter dis = new DispatchAdapter();
        dis.setCanvasDims(new Point2D.Double(800, 800));
        dis.loadPaintObj("acceleration changeDirection", "ball", "Switch");
        dis.removeAll();

        assertEquals("test remove", 0, dis.updateBallWorld().length);
    }
}