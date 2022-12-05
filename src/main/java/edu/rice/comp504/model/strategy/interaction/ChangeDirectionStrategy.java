package edu.rice.comp504.model.strategy.interaction;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.model.strategy.interaction.IInteractionStrategy;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ChangeDirectionStrategy implements IInteractionStrategy{

    private static IInteractionStrategy ONLY;

    /**
     * Constructor.
     */
    public ChangeDirectionStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "changeDirection";
    }

    public int getPriority() {
        return 1;
    }

    /**
     * Make a horizontal strategy.
     * @return A horizontal strategy
     */
    public static IInteractionStrategy make() {
        if (ONLY == null) {
            ONLY = new ChangeDirectionStrategy();
        }
        return ONLY;
    }

    /**
     * Update the state of the ball.
     * @param context The ball.
     * @param  changedContext  balls need changed
     */
    public void updateState(Ball context, ArrayList<Ball> changedContext, BallWorldStore store) {

        Point2D.Double contextV = context.getVelocity();

        for (Ball ball: changedContext) {
            Point2D.Double v = ball.getVelocity();
            ball.setVelocity( new Point2D.Double( (contextV.x - 0.001) / Math.abs(contextV.x - 0.001) * Math.abs(v.x), (contextV.y - 0.001) / Math.abs(contextV.y - 0.001) * Math.abs(v.y)));
            ball.getStrategy().updateState(ball);
            ball.changeAngle();
            while (context.detectCollisionBall(ball)) {
                ball.getStrategy().updateState(ball);
            }
        }

        context.setVelocity(new Point2D.Double( contextV.x * -1, contextV.y * -1));
    }
}
