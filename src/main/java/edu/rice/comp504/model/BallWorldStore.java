package edu.rice.comp504.model;

import com.google.gson.JsonArray;
import edu.rice.comp504.model.cmd.SwitchStrCmd;
import edu.rice.comp504.model.cmd.UpdateStateCmd;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.model.paintobj.Fish;
import edu.rice.comp504.model.paintobj.NullObject;
import edu.rice.comp504.model.strategy.behavior.BehaviorStrategyFac;
import edu.rice.comp504.model.strategy.behavior.NullStrategy;
import edu.rice.comp504.model.strategy.interaction.InteractionStrategyFac;
import org.eclipse.jetty.util.ArrayUtil;

import java.awt.geom.Point2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * A store containing current Ball World.
 */
public class BallWorldStore {
    private PropertyChangeSupport pcs;
    private static Point2D.Double dims;

    private static BehaviorStrategyFac ONLY;

    private static InteractionStrategyFac ONLY2;

    /**
     * Constructor.
     */
    public BallWorldStore() {
        pcs = new PropertyChangeSupport(this);
    }

    /**
     * Get the singleton strategy factory.
     * @return The strategy factory
     */
    public static BehaviorStrategyFac getOnlyStratFac() {
        if (ONLY == null) {
            ONLY = new BehaviorStrategyFac();
        }
        return ONLY;
    }

    /**
     * Get the singleton interaction strategy factory.
     * @return The strategy factory
     */
    public static InteractionStrategyFac getOnlyInteractionStratFac() {
        if (ONLY2 == null) {
            ONLY2 = new InteractionStrategyFac();
        }
        return ONLY2;
    }


    /**
     * Get the canvas dimensions.
     * @return The canvas dimensions
     */
    public static Point2D.Double getCanvasDims() {
        return dims;
    }

    /**
     * Set the canvas dimensions.
     * @param dims The canvas width (x) and height (y).
     */
    public static void setCanvasDims(Point2D.Double dims) {
        BallWorldStore.dims = dims;
    }

    /**
     * Call the update method on all the ball observers to update their position in the ball world.
     */
    public PropertyChangeListener[] updateBallWorld() {
        // TODO: fill in
        pcs.firePropertyChange("theClock", null, new UpdateStateCmd(pcs.getPropertyChangeListeners("ball"), this));

        PropertyChangeListener[] orderList = ArrayUtil.add(pcs.getPropertyChangeListeners("fish"), pcs.getPropertyChangeListeners("ball"));
        return orderList;
    }

    /**
     * Load a ball into the Ball World.
     * @param type The type of object (e.g. ball) created.
     * @return A ball
     */
    public APaintObject loadPaintObj(String strat, String type, String switchOrNot) {
        // TODO: fill in
        APaintObject po = NullObject.make();
        po.setStrategy(NullStrategy.makeStrategy());

        if (type.equals("ball")) {
            String[] strats = strat.split(" ");
            po = Ball.makeBall(getOnlyStratFac().make(strats[0]), getOnlyInteractionStratFac().make(strats[1]), dims);
            pcs.addPropertyChangeListener("ball", po);
        } else if (type.equals("fish")) {
            po = Fish.makeFish(getOnlyStratFac().make(strat), dims);
            pcs.addPropertyChangeListener("fish", po);
        }
        if (switchOrNot.equals("Switch")) {
            pcs.addPropertyChangeListener("switch", po);
        }
        addListener(po);
        return po;
    }



    /**
     * Switch the strategy of switcher balls
     * @param strat  The REST request strategy.
     */
    public PropertyChangeListener[] switchStrategy(String strat) {
        pcs.firePropertyChange("switch", null, new SwitchStrCmd(getOnlyStratFac().make(strat)));
        PropertyChangeListener[] orderList = ArrayUtil.add(pcs.getPropertyChangeListeners("fish"), pcs.getPropertyChangeListeners("ball"));
        return orderList;
    }

    /**
     * Add a ball that will listen for a property change (i.e. time elapsed)
     * @param pcl  The ball
     */
    private void addListener(PropertyChangeListener pcl) {
        // TODO: fill in
        pcs.addPropertyChangeListener("theClock", pcl);
    }

    /**
     * Remove balls that have stratigies.
     * @param strat Strategy
     */
    public PropertyChangeListener[] removeObjectsFromStore(String strat) {
        for (PropertyChangeListener listener: pcs.getPropertyChangeListeners("theClock")) {
            APaintObject object = (APaintObject) listener;
            if (object.getStrategy() == getOnlyStratFac().make(strat)) {
                pcs.removePropertyChangeListener("theClock", listener);
                pcs.removePropertyChangeListener(object.getType(), listener);
            }
        }
        PropertyChangeListener[] orderList = ArrayUtil.add(pcs.getPropertyChangeListeners("fish"), pcs.getPropertyChangeListeners("ball"));
        return orderList;
    }

    /**
     * Remove all balls.
     */
    public void removeBallsFromStore() {
        for (PropertyChangeListener listener : pcs.getPropertyChangeListeners()) {
            pcs.removePropertyChangeListener(listener);
        }
    }

    /**
     * Remove a ball from listening for property change events.
     * @param listener A ball
     */
    public void removeBallFromStore(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener("theClock", listener);
        pcs.removePropertyChangeListener("ball", listener);
    }

    public PropertyChangeListener[] getBalls() {
        return pcs.getPropertyChangeListeners("ball");
    }

    public PropertyChangeListener[] getFish() {
        return pcs.getPropertyChangeListeners("fish");
    }

}
