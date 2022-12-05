package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.model.strategy.behavior.*;

import java.beans.PropertyChangeListener;

public class SwitchStrCmd implements IPaintObjCmd {

    private final IUpdateStrategy strategy;


    /**
     * Constructor.
     */
    public SwitchStrCmd(IUpdateStrategy strategy) {
        this.strategy = strategy;

    }


    @Override
    /**
     * Execute the command by calling the receiver's update ball method.
     */
    public void execute(APaintObject context) {
        // TODO: switch strategies
        if (context != null) {
            BehaviorStrategyFac.switchStrategy(context, strategy);
        }
    }

}
