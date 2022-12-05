package edu.rice.comp504.model.strategy.interaction;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;

import java.util.ArrayList;

/**
 * An interface for ball strategies that determine the ball interaction.
 */
public interface IInteractionStrategy {

    /**
     * The name of the strategy.
     * @return strategy name
     */
    String getName();

    int getPriority();

    /**
     * Update the state of the ball.
     * @param context The ball.
     * @param  changedContext  balls need changed
     */
    void updateState(Ball context, ArrayList<Ball> changedContext, BallWorldStore store);
}
