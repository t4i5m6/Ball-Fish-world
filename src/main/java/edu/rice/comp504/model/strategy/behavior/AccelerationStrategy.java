package edu.rice.comp504.model.strategy.behavior;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.geom.Point2D;

public class AccelerationStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;

    /**
     * Constructor.
     */
    public AccelerationStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "acceleration";
    }

    public String getColor() {
        return "cyan";
    }

    /**
     * Make a horizontal strategy.
     * @return A horizontal strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new AccelerationStrategy();
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
        double maxV = 200;
        int a = context.getAcceleration();

        int direction = (v.x >= 0) ? 1 : -1;
        double newV = Math.abs(context.getVelocity().x) + context.getAcceleration();
        if (newV > maxV || newV <= 0) {
            context.setAcceleration(-1 * a);
        }

        context.setVelocity(new Point2D.Double(direction * (Math.abs(context.getVelocity().x) + context.getAcceleration()), 0));
        context.setLocation(new Point2D.Double(loc.x + context.getVelocity().x, loc.y));
    }
}
