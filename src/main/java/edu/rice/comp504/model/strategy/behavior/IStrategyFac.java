package edu.rice.comp504.model.strategy.behavior;

/**
 * A factory that makes strategies.
 */
public interface IStrategyFac {

    /**
     * Makes a strategy.
     * @param type The ball strategy type.
     * @return A strategy
     */
    IUpdateStrategy make(String type);
}
