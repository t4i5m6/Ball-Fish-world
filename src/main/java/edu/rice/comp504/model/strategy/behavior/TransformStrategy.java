package edu.rice.comp504.model.strategy.behavior;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.model.paintobj.Fish;

import java.awt.geom.Point2D;

public class TransformStrategy implements IUpdateStrategy{

    private static IUpdateStrategy ONLY;

    /**
     * Constructor.
     */
    public TransformStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "transform";
    }

    public String getColor() {
        return "green";
    }

    /**
     * Make a horizontal strategy.
     * @return A horizontal strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new TransformStrategy();
        }
        return ONLY;
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {

        if (context.getType().equals("ball")) {
            Ball ball = (Ball) context;
            Point2D.Double loc = context.getLocation();
            double v = context.getVelocity().x;

            int newRadius = ball.getRadius() + (int)(v);


            context.setVelocity(new Point2D.Double(context.getVelocity().x, 0));

            if ( newRadius <= 0 || loc.x  - newRadius < 0 || loc.x + newRadius > BallWorldStore.getCanvasDims().x ||
                    loc.y - newRadius < 0 || loc.y + newRadius > BallWorldStore.getCanvasDims().y) {
                context.setVelocity(new Point2D.Double(-1 * v, 0));
            } else {
                ball.setRadius(newRadius);
            }
        } else if (context.getType().equals("fish")) {
            Fish fish = (Fish) context;
            double v = context.getVelocity().x;
            int newSideLength = fish.getSideLength() + (int) v;
            double halfDiagonal = newSideLength * Math.sqrt(2) / 2;
            Double[] points = {45.0, 135.0, -45.0, -135.0};

            Point2D.Double loc = fish.getLocation();
            Double theta = fish.getTheta();


            for (double point : points) {
                double x = loc.x + newSideLength / 2 + halfDiagonal * Math.cos(point + theta);
                double y = loc.y + newSideLength / 2 + halfDiagonal * Math.sin(45 + theta);
                if ( (v  < 0 && (x < 0 || y < 0)) || (v > 0 && (x > BallWorldStore.getCanvasDims().x || y > BallWorldStore.getCanvasDims().y))) {
                    context.setVelocity(new Point2D.Double(-1 * v, v));
                    return;
                }
            }

            fish.setSideLength(newSideLength);
        }


    }
}
