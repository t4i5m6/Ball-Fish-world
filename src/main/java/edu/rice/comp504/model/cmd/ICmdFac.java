package edu.rice.comp504.model.cmd;

/**
 * A factory that makes commands.
 */
public interface ICmdFac {

    /**
     * Makes a ball command.
     * @param type The ball command type.
     * @return A ball command
     */
    IPaintObjCmd make(String type);
}
