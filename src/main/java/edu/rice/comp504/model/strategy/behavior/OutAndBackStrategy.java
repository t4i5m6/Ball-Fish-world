package edu.rice.comp504.model.strategy.behavior;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.geom.Point2D;

public class OutAndBackStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;

    /**
     * Constructor.
     */
    public OutAndBackStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "outAndBack";
    }

    public String getColor() {
        return "gray";
    }

    /**
     * Make a horizontal strategy.
     * @return A horizontal strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new OutAndBackStrategy();
        }
        return ONLY;
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        Point2D.Double v = context.getVelocity();
        Point2D.Double loc = context.getLocation();
        Math.min(BallWorldStore.getCanvasDims().x - loc.x, loc.x);

        context.setVelocity(new Point2D.Double(context.getVelocity().x  , 0));

        double dimX = BallWorldStore.getCanvasDims().x;
        //context.detectCollisionBoundary();
        context.setLocation(new Point2D.Double(loc.x + v.x * Math.min( dimX - loc.x, loc.x) / dimX * 2, loc.y));
    }
}