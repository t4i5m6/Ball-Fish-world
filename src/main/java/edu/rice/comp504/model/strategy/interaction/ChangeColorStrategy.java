package edu.rice.comp504.model.strategy.interaction;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.Ball;

import java.util.ArrayList;

public class ChangeColorStrategy implements IInteractionStrategy{

    private static IInteractionStrategy ONLY;

    /**
     * Constructor.
     */
    public ChangeColorStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "changeColor";
    }

    public int getPriority() {
        return 2;
    }

    /**
     * Make a horizontal strategy.
     * @return A horizontal strategy
     */
    public static IInteractionStrategy make() {
        if (ONLY == null) {
            ONLY = new ChangeColorStrategy();
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
            ball.setColor(context.getColor());
        }
        BallWorldStore.getOnlyInteractionStratFac().make("changeDirection").updateState(context, changedContext, store);
    }
}