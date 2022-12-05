package edu.rice.comp504.adapter;

import com.google.gson.JsonArray;
import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.cmd.SwitchStrCmd;
import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.geom.Point2D;
import java.beans.PropertyChangeListener;

import java.awt.*;

/**
 * This adapter interfaces with the view (paint objects) and the controller.
 */
public class DispatchAdapter {

    private BallWorldStore store;
    /**
     * Constructor call.
     */
    public DispatchAdapter() {
        store = new BallWorldStore();
    }

    /**
     * Set the canvas dimensions.
     * @param dims The canvas width (x) and height (y).
     */
    public void setCanvasDims(Point2D.Double dims) {
        BallWorldStore.setCanvasDims(dims);
    }

    /**
     * Update the ball world.
     * @return Balls in BallWorld
     */
    public PropertyChangeListener[] updateBallWorld() {
        // TODO: fill in
        return store.updateBallWorld();
    }

    /**
     * Load a ball into the paint world.
     * @param strat  The REST request strategy.
     * @param type  The object type
     * @return APaintObject
     */
    public APaintObject loadPaintObj(String strat, String type, String switchOrNot) {
        // TODO: fill in

        return store.loadPaintObj(strat, type, switchOrNot);
    }


    /**
     * Switch the strategy for switcher balls
     * @param strat  The REST request strategy.
     * @return Balls in BallWorld
     */
    public PropertyChangeListener[] switchStrategy(String strat) {
        // TODO: fill in
        return store.switchStrategy(strat);
    }

    public PropertyChangeListener[] removeObjects(String strat) {
        // TODO: fill in
        return store.removeObjectsFromStore(strat);
    }


    /**
     * Remove all balls from listening for property change events for a particular property.
     * @return Balls in BallWorld
     */

    public PropertyChangeListener[] removeAll() {
        // TODO: fill in
        store.removeBallsFromStore();
        return  null;
    }

}
