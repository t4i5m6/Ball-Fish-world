package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.strategy.behavior.BehaviorStrategyFac;
import edu.rice.comp504.model.strategy.behavior.IUpdateStrategy;
import edu.rice.comp504.util.RandUtil;

import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;

public class Fish extends APaintObject{

    private int sideLength;

    private double theta;
    private Boolean isFlip;
    /**
     * Constructor.
     * @param loc  The location of the fish on the canvas
     * @param sideLength The fish sideLength
     * @param vel  The fish velocity
     */
    private Fish(Point2D.Double loc, int sideLength, Point2D.Double vel, IUpdateStrategy strategy) {
        super(loc, vel, "black", "fish", strategy);
        this.sideLength = sideLength;
        setTheta(vel);
    }

    /**
     * Make a new ball.
     * @return A ball.
     */
    public static Fish makeFish(IUpdateStrategy strategy, Point2D.Double dims) {

        int maxLength = 100;
        return new Fish(new Point2D.Double(RandUtil.getRnd(maxLength, (int)dims.x - maxLength), RandUtil.getRnd(maxLength, (int)dims.y - maxLength)),
                RandUtil.getRnd(50, maxLength),
                new Point2D.Double((int)Math.floor(Math.random() * 50 + 1), (int)Math.floor(Math.random() * 50) + 1),
                strategy);
    }

    private void setTheta(Point2D.Double vel) {
        double theta = Math.atan2(vel.y, vel.x) * 180 / Math.PI;
        isFlip = false;
        if (theta > 90 || theta < -90) {
            isFlip = true;
            if (theta > 90) {
                theta = 180 - theta;
            } else {
                theta = -1 * (180 + theta);
            }
        }
        this.theta = theta;
    }

    public void setVelocity(Point2D.Double vel) {
        this.vel = vel;
        setTheta(vel);
    }

    public void setSideLength(int l) {
        this.sideLength = l;
    }

    public int getSideLength() {
        return this.sideLength;
    }

    public double getTheta() {
        return this.theta;
    }

    /**
     * Detects collision between a ball and a wall in the ball world.  Change direction if ball collides with a wall.
     * @return True if there was a collision and false otherwise.
     */
    public boolean detectCollisionBoundary() {
        // TODO: fill in
        double halfDiagonal = sideLength * Math.sqrt(2) / 2;
        Double[] points = {45.0, 135.0, -45.0, -135.0};
        Point2D.Double newVel = new Point2D.Double(vel.x, vel.y);

        Boolean collision = false;
        for (double point : points) {
            double x = loc.x + sideLength / 2 + halfDiagonal * Math.cos(Math.toRadians(point + theta));
            double y = loc.y + sideLength / 2 + halfDiagonal * Math.sin(Math.toRadians(45 + theta));

            if ( vel.x == newVel.x && ( (vel.x < 0  && x <= 0)  || (vel.x > 0 && x >= BallWorldStore.getCanvasDims().x) )) {
                newVel.x *= -1;
                newVel.y *= -1;
                collision = true;
                break;
            } else if ( vel.y == newVel.y && ((vel.y < 0 && y <= 0) || (vel.y > 0 && y >= BallWorldStore.getCanvasDims().y))) {
                newVel.y *= -1;
                newVel.x *= -1;
                collision = true;
                break;
            }
        }
        if (collision) {
            changeAngle();
            loc.x += newVel.x;
            loc.y += newVel.y;
            distance = BehaviorStrategyFac.getMaxDistance() - distance;
        }
        setVelocity(newVel);

        return collision;
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
