package edu.rice.comp504.model.strategy.interaction;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.Ball;

import java.util.ArrayList;

public class StopStrategy implements IInteractionStrategy{

    private static IInteractionStrategy ONLY;

    /**
     * Constructor.
     */
    public StopStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "stop";
    }

    public int getPriority() {
        return 10;
    }

    /**
     * Make a horizontal strategy.
     * @return A horizontal strategy
     */
    public static IInteractionStrategy make() {
        if (ONLY == null) {
            ONLY = new StopStrategy();
        }
        return ONLY;
    }

    /**
     * Update the state of the ball.
     * @param context The ball.
     * @param  changedContext  balls need changed
     */
    public void updateState(Ball context, ArrayList<Ball> changedContext, BallWorldStore store) {

        for (Ball ball: changedContext) {
            ball.setNotUpdateCount(20);
        }
    }
}
