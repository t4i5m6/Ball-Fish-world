package edu.rice.comp504.model.strategy.behavior;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.geom.Point2D;

public class RectangleStrategy implements IUpdateStrategy{

    private static IUpdateStrategy ONLY;

    /**
     * Constructor.
     */
    public RectangleStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "rectangle";
    }

    public String getColor() {
        return "magenta";
    }

    /**
     * Make a horizontal strategy.
     * @return A horizontal strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new RectangleStrategy();
        }
        return ONLY;
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {

        Point2D.Double loc = context.getLocation();
        Point2D.Double v = context.getVelocity();
        int distance = context.getDistance();
        int angle = context.getAngle();
        double rad = Math.toRadians(angle / Math.abs(angle) * 90);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);
        if ( Math.abs(v.x) > 0.00001 && v.y != 0) {
            context.setVelocity(new Point2D.Double(context.getVelocity().x, 0));
        }
        v = context.getVelocity();

        if (distance == BehaviorStrategyFac.getMaxDistance()) {

            if (v.y == 0) {
                context.setVelocity(new Point2D.Double(v.x * cos - v.y * sin, (v.y * cos + v.x * sin) / 2));
            } else {
                context.setVelocity(new Point2D.Double((v.x * cos - v.y * sin) * 2, v.y * cos + v.x * sin));
            }
            v = context.getVelocity();
            context.setDistance(0);
        } else {
            context.setDistance(distance + 1);
        }

        context.setLocation(new Point2D.Double(loc.x + v.x, loc.y + v.y));


    }
}