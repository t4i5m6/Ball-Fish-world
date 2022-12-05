package edu.rice.comp504.model.strategy.interaction;

/**
 * A factory that makes strategies.
 */
public interface IInteractionStrategyFac {

    /**
     * Makes a strategy.
     * @param type The ball strategy type.
     * @return A strategy
     */
    IInteractionStrategy make(String type);
}
