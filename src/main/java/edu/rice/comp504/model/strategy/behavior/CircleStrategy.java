package edu.rice.comp504.model.strategy.behavior;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.geom.Point2D;

public class CircleStrategy implements IUpdateStrategy{

    private static IUpdateStrategy ONLY;

    /**
     * Constructor.
     */
    public CircleStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "circle";
    }

    public String getColor() {
        return "red";
    }

    /**
     * Make a horizontal strategy.
     * @return A horizontal strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new CircleStrategy();
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


        double rad = Math.toRadians(context.getAngle());
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);


        context.setVelocity(new Point2D.Double(v.x * cos - v.y * sin, v.y * cos + v.x * sin));
        v = context.getVelocity();
        context.setLocation(new Point2D.Double(loc.x + v.x, loc.y + v.y));


    }
}
