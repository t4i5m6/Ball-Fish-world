package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.strategy.behavior.NullStrategy;

import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;

/**
 * NullObject represents an initial or unexpected paint object type.
 */
public class NullObject extends APaintObject {
    private static NullObject ONLY;

    /**
     * Constructor.
     */
    public NullObject() {
        super(new Point2D.Double(400,400), new Point2D.Double(0,0), "black", "null", NullStrategy.makeStrategy());
    }

    /**
     * Make a null object.  There is only one (singleton).
     * @return A null object
     */
    public static NullObject make() {
        if (ONLY == null) {
            ONLY = new NullObject();
        }
        return ONLY;
    }

    @Override
    public boolean detectCollisionBoundary() {
        return false;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
