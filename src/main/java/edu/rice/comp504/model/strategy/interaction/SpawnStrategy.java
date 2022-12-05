package edu.rice.comp504.model.strategy.interaction;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.util.RandUtil;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class SpawnStrategy implements IInteractionStrategy{

    private static IInteractionStrategy ONLY;


    /**
     * Constructor.
     */
    public SpawnStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "spawn";
    }

    public int getPriority() {
        return 5;
    }

    /**
     * Make a horizontal strategy.
     * @return A horizontal strategy
     */
    public static IInteractionStrategy make() {
        if (ONLY == null) {
            ONLY = new SpawnStrategy();
        }
        return ONLY;
    }

    /**
     * Update the state of the ball.
     * @param context The ball.
     * @param  changedContext  balls need changed
     */
    public void updateState(Ball context, ArrayList<Ball> changedContext, BallWorldStore store) {

        int maxRadius = 200;
        double newBallX = 0;
        double newBallY = 0;
        ArrayList<Ball> allBalls = new ArrayList<>(changedContext);
        allBalls.add(context);

        int newRadius = 0;
        for (Ball ball: allBalls) {
            int halfRadaius = (int) Math.ceil( (double)ball.getRadius() / 2);
            ball.setRadius(halfRadaius);
            newBallX += ball.getLocation().x;
            newBallY += ball.getLocation().y;
            newRadius += halfRadaius;
        }

        if ( newRadius > maxRadius) {
            newRadius = maxRadius;
        }

        Ball newBall = (Ball) store.loadPaintObj("horizontal changeDirection", "ball", "");
        newBall.setRadius(newRadius);
        newBall.setLocation(new Point2D.Double(newBallX / (changedContext.size() + 1), newBallY / (changedContext.size() + 1)));


        BallWorldStore.getOnlyInteractionStratFac().make("changeDirection").updateState(context, changedContext, store);
    }
}
