package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.strategy.behavior.BehaviorStrategyFac;
import edu.rice.comp504.model.strategy.behavior.IUpdateStrategy;
import edu.rice.comp504.model.strategy.interaction.IInteractionStrategy;
import edu.rice.comp504.util.RandUtil;

import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;

/**
 * The balls that will be drawn in the ball world.
 */
public class Ball extends APaintObject {

    private int radius;


    IInteractionStrategy interactionStrategy;
    /**
     * Constructor.
     * @param loc  The location of the ball on the canvas
     * @param radius The ball radius
     * @param vel  The ball velocity
     * @param color The ball color
     */
    private Ball(Point2D.Double loc, int radius, Point2D.Double vel, String color, IUpdateStrategy strategy, IInteractionStrategy interaction) {
        super(loc, vel, color, "ball", strategy);
        this.radius = radius;
        this.interactionStrategy = interaction;
    }

    /**
     * Make a new ball.
     * @return A ball.
     */
    public static Ball makeBall(IUpdateStrategy strategy, IInteractionStrategy interaction, Point2D.Double dims) {

        int maxRadius = 30;
        return new Ball(new Point2D.Double(RandUtil.getRnd(maxRadius, (int)dims.x - maxRadius), RandUtil.getRnd(maxRadius, (int)dims.y - maxRadius)),
                RandUtil.getRnd(10, maxRadius),
                new Point2D.Double((int)Math.floor(Math.random() * 50 + 1), (int)Math.floor(Math.random() * 50) + 1),
                strategy.getColor(), strategy, interaction);
    }

    /**
     * Get the radius of the ball.
     * @return The ball radius.
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Set the radius of the ball.
     * @param r The ball radius.
     */
    public void setRadius(int r) {
        this.radius = r;
    }




    /**
     * Detects collision between a ball and a wall in the ball world.
     * @return True if there was a collision and false otherwise.
     */
    public boolean detectCollisionBall(Ball ball) {
        // TODO: fill in
        Point2D.Double ballLoc = ball.getLocation();

        if (Math.sqrt(Math.pow(loc.x - ballLoc.x, 2) + Math.pow(loc.y - ballLoc.y, 2)) <= radius + ball.getRadius()) {
            return true;
        }
        return false;
    }

    /**
     * Detects collision between a ball and a wall in the ball world.  Change direction if ball collides with a wall.
     * @return True if there was a collision and false otherwise.
     */
    public boolean detectCollisionBoundary() {
        // TODO: fill in
        Boolean collision = false;

        if (loc.x  + vel.x < radius || loc.x + vel.x > BallWorldStore.getCanvasDims().x - radius ) {
            vel.x = -1 * vel.x;
            vel.y = -1 * vel.y;
            collision = true;
        } else if (loc.y + vel.y < radius || loc.y + vel.y > BallWorldStore.getCanvasDims().y - radius) {
            vel.y = -1 * vel.y;
            vel.x = -1 * vel.x;
            collision = true;
        }
        if (collision) {
            changeAngle();
            loc.x += vel.x;
            loc.y += vel.y;
            distance = BehaviorStrategyFac.getMaxDistance() - distance;
        }

        return collision;
    }

    public IInteractionStrategy getInteractionStrategy() {
        return interactionStrategy;
    }

    /**
     * Set the new interaction strategy.
     * @param strategy The new strategy.
     */
    public void setInteractionStrategy(IInteractionStrategy strategy) {
        interactionStrategy = strategy;
    }


    public boolean isColorable() {
        return true;
    }

    @Override
    /**
     *  Ball responds to the property change support indicating that property has changed.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO: fill in
        ((IPaintObjCmd)evt.getNewValue()).execute(this);
    }

}
