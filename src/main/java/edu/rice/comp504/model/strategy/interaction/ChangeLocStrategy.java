package edu.rice.comp504.model.strategy.interaction;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.util.RandUtil;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ChangeLocStrategy implements IInteractionStrategy{

    private static IInteractionStrategy ONLY;

    /**
     * Constructor.
     */
    public ChangeLocStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "changeLoc";
    }

    public int getPriority() {
        return 3;
    }

    /**
     * Make a horizontal strategy.
     * @return A horizontal strategy
     */
    public static IInteractionStrategy make() {
        if (ONLY == null) {
            ONLY = new ChangeLocStrategy();
        }
        return ONLY;
    }

    /**
     * Update the state of the ball.
     * @param context The ball.
     * @param  changedContext  balls need changed
     */
    public void updateState(Ball context, ArrayList<Ball> changedContext, BallWorldStore store) {

        Point2D.Double dims =  BallWorldStore.getCanvasDims();
        for (Ball ball: changedContext) {
            int radius = ball.getRadius();
            Point2D.Double originalLoc = ball.getLocation();
            ball.setLocation(new Point2D.Double( RandUtil.getRnd(radius, (int)dims.x - radius), RandUtil.getRnd(radius, (int)dims.y - radius)));
            while (context.detectCollisionBall(ball) || ball.getLocation() == originalLoc) {
                ball.setLocation(new Point2D.Double( RandUtil.getRnd(radius, (int)dims.x - radius), RandUtil.getRnd(radius, (int)dims.y - radius)));
            }
        }
    }
}
