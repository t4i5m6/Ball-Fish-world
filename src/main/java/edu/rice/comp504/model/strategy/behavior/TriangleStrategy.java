package edu.rice.comp504.model.strategy.behavior;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.util.RandUtil;

import java.awt.geom.Point2D;

public class TriangleStrategy implements IUpdateStrategy{

    private static IUpdateStrategy ONLY;

    /**
     * Constructor.
     */
    public TriangleStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "triangle";
    }

    public String getColor() {
        return "pink";
    }

    /**
     * Make a horizontal strategy.
     * @return A horizontal strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new TriangleStrategy();
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

        double rad = Math.toRadians(angle / Math.abs(angle) * 120);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);


        if (distance == BehaviorStrategyFac.getMaxDistance()) {
            context.setVelocity(new Point2D.Double(v.x * cos - v.y * sin, v.y * cos + v.x * sin));
            v = context.getVelocity();
            context.setDistance(0);
        } else {
            context.setDistance(distance + 1);
        }

        context.setLocation(new Point2D.Double(loc.x + v.x, loc.y + v.y));

    }
}
