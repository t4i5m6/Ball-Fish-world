package edu.rice.comp504.model.strategy.behavior;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;

/**
 * An interface for ball strategies that determine the ball behavior.
 */
public interface IUpdateStrategy {
    /**
     * The name of the strategy.
     * @return strategy name
     */
    String getName();

    String getColor();

    /**
     * Update the state of the ball.
     * @param context The ball.
     */
    void updateState(APaintObject context);
}
