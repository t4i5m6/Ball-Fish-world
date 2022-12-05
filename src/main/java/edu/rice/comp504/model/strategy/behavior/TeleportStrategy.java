package edu.rice.comp504.model.strategy.behavior;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.util.RandUtil;

import java.awt.geom.Point2D;

public class TeleportStrategy implements IUpdateStrategy{

    private static IUpdateStrategy ONLY;


    /**
     * Constructor.
     */
    public TeleportStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "teleport";
    }

    public String getColor() {
        return "yellow";
    }

    /**
     * Make a horizontal strategy.
     * @return A horizontal strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new TeleportStrategy();
        }
        return ONLY;
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        Point2D.Double dims = BallWorldStore.getCanvasDims();
        context.setLocation(new Point2D.Double( RandUtil.getRnd(0, (int)dims.x), RandUtil.getRnd(0, (int)dims.y )));
        while (context.detectCollisionBoundary()) {
            context.setLocation(new Point2D.Double( RandUtil.getRnd(0, (int)dims.x), RandUtil.getRnd(0, (int)dims.y )));
        }
    }
}

