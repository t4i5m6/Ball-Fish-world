package edu.rice.comp504.model.paintobj;

/**
 * A factory that makes balls.
 */
public interface IBallFac {
    /**
     * Makes a ball.
     * @param type The ball type.
     * @return A Ball
     */
    Ball make(String type);
}
