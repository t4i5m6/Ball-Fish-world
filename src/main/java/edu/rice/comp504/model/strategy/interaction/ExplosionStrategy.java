package edu.rice.comp504.model.strategy.interaction;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.Ball;

import java.awt.geom.Point2D;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ExplosionStrategy implements IInteractionStrategy{

    private static IInteractionStrategy ONLY;

    /**
     * Constructor.
     */
    public ExplosionStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "explosion";
    }

    public int getPriority() {
        return 9;
    }

    /**
     * Make a horizontal strategy.
     * @return A horizontal strategy
     */
    public static IInteractionStrategy make() {
        if (ONLY == null) {
            ONLY = new ExplosionStrategy();
        }
        return ONLY;
    }

    /**
     * Update the state of the ball.
     * @param context The ball.
     * @param  changedContext  balls need changed
     */
    public void updateState(Ball context, ArrayList<Ball> changedContext, BallWorldStore store) {

        changedContext.add(context);
        for (Ball ball: changedContext) {
            for (PropertyChangeListener listener: store.getBalls()) {
                Ball otherBall = (Ball) listener;
                Point2D.Double ballLoc = ball.getLocation();
                Point2D.Double otherLoc = otherBall.getLocation();

                if (!changedContext.contains(otherBall) &&
                        Math.sqrt(Math.pow(otherLoc.x - ballLoc.x, 2) + Math.pow(otherLoc.y - ballLoc.y, 2)) <= ball.getRadius() * 10) {
                    store.removeBallFromStore(otherBall);
                }
            }
            store.removeBallFromStore(ball);
        }
    }
}
