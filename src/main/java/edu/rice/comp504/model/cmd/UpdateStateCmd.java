package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * The UpdateStateCmd will possibly update either the paint object location or attribute (color).
 */
public class UpdateStateCmd implements IPaintObjCmd {
    private final PropertyChangeListener[] iBalls;
    private final BallWorldStore store;

    /**
     * The constructor.
     * @param iBalls The canvas inner walls
     *
     */
    public UpdateStateCmd(PropertyChangeListener[] iBalls, BallWorldStore store) {
        this.iBalls = iBalls;
        this.store = store;
    }

    /**
     * Update the state of the paint object
     * @param context  The paint object.
     */
    public void execute(APaintObject context) {

        if (context.getNotUpdateCount() > 0) {
            context.setNotUpdateCount(context.getNotUpdateCount() - 1);
            return;
        }

        context.detectCollisionBoundary();
        Boolean isAffected = false;

        if (context.getType().equals("ball")) {
            ArrayList<Ball> smallPriorityBalls = new ArrayList<Ball>();
            ArrayList<Ball> samePriorityBalls = new ArrayList<Ball>();

            int priority = ((Ball)context).getInteractionStrategy().getPriority();

            for ( PropertyChangeListener listener : iBalls) {
                Ball ball = (Ball) listener;

                if ( context != ball && ((Ball) context).detectCollisionBall(ball) && ball.getNotUpdateCount() == 0) {
                    int ballPrioirty = ball.getInteractionStrategy().getPriority();
                    if (priority  < ballPrioirty) {
                        isAffected = true;
                        break;
                    } else if (priority == ballPrioirty) {
                        samePriorityBalls.add(ball);
                    } else {
                        smallPriorityBalls.add(ball);
                    }
                }
            }
            if (!isAffected) {
                if (!smallPriorityBalls.isEmpty()) {
                    ((Ball) context).getInteractionStrategy().updateState((Ball) context, smallPriorityBalls, store);
                }
                if (!samePriorityBalls.isEmpty()) {
                    BallWorldStore.getOnlyInteractionStratFac().make("changeDirection").updateState((Ball) context, samePriorityBalls, store);
                }
            }

        }
        if ( !isAffected ) {
            context.getStrategy().updateState(context);
        }

    }

}
