package edu.rice.comp504.model.strategy.interaction;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.model.paintobj.Fish;

import java.util.ArrayList;

public class TransformFishStrategy implements IInteractionStrategy{

    private static IInteractionStrategy ONLY;

    /**
     * Constructor.
     */
    public TransformFishStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "transformFish";
    }

    public int getPriority() {
        return 7;
    }

    /**
     * Make a horizontal strategy.
     * @return A horizontal strategy
     */
    public static IInteractionStrategy make() {
        if (ONLY == null) {
            ONLY = new TransformFishStrategy();
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
            Fish fish = (Fish) store.loadPaintObj(ball.getStrategy().getName(), "fish", "");
            fish.setLocation(ball.getLocation());
            fish.setSideLength(ball.getRadius());
            fish.setStrategy(ball.getStrategy());
            fish.setVelocity(ball.getVelocity());

            store.removeBallFromStore(ball);
        }
    }
}
