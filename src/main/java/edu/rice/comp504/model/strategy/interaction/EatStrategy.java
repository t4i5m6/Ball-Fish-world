package edu.rice.comp504.model.strategy.interaction;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.Ball;

import java.util.ArrayList;

public class EatStrategy implements IInteractionStrategy{

    private static IInteractionStrategy ONLY;

    /**
     * Constructor.
     */
    public EatStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "eat";
    }

    public int getPriority() {
        return 4;
    }

    /**
     * Make a horizontal strategy.
     * @return A horizontal strategy
     */
    public static IInteractionStrategy make() {
        if (ONLY == null) {
            ONLY = new EatStrategy();
        }
        return ONLY;
    }

    /**
     * Update the state of the ball.
     * @param context The ball.
     * @param  changedContext  balls need changed
     */
    public void updateState(Ball context, ArrayList<Ball> changedContext, BallWorldStore store) {

        int radius = context.getRadius();
        int maxRadius = 200;
        if (radius >= maxRadius) {
            radius = maxRadius;
        } else {
            for (Ball ball: changedContext) {
                int halfRadaius = (int) Math.ceil( (double)ball.getRadius() / 2);
                ball.setRadius(halfRadaius);
                radius += halfRadaius;
            }
        }


        BallWorldStore.getOnlyInteractionStratFac().make("changeDirection").updateState(context, changedContext, store);
        context.setRadius(radius);
    }
}