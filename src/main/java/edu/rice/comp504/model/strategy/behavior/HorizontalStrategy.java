package edu.rice.comp504.model.strategy.behavior;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.geom.Point2D;

public class HorizontalStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;

    /**
     * Constructor.
     */
    public HorizontalStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "horizontal";
    }

    public String getColor() {
        return "blue";
    }

    /**
     * Make a horizontal strategy.
     * @return A horizontal strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new HorizontalStrategy();
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

        context.setVelocity(new Point2D.Double(context.getVelocity().x, 0));

        //context.detectCollisionBoundary();
        context.setLocation(new Point2D.Double(loc.x + v.x, loc.y));
    }
}

