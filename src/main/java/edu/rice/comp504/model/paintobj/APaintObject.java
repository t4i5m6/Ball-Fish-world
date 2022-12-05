package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.strategy.behavior.IUpdateStrategy;

import java.awt.geom.Point2D;
import java.beans.PropertyChangeListener;

public abstract class APaintObject implements PropertyChangeListener {
    Point2D.Double loc;
    Point2D.Double vel;
    IUpdateStrategy strategy;
    String color;

    int angle = 10;

    int distance;
    String type;

    int acceleration = 3;

    private int notUpdateCount = 0;

    /**
     * Constructor.
     * @param loc  The location of the paintable object on the canvas
     * @param vel  The object velocity
     * @param strategy  The object update strategy
     */
    public APaintObject(Point2D.Double loc, Point2D.Double vel, String color, String type, IUpdateStrategy strategy) {
        this.loc = loc;
        this.vel = vel;
        this.color = color;
        this.type = type;
        this.strategy = strategy;
        this.distance = 0;
    }


    /**
     * Detects collision between a paint and a boundary wall.  Change direction if ball collides with boundary.
     */
    public abstract boolean detectCollisionBoundary();

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * Get the paintable object type.
     * @return The paintable object type
     */
    public String getType() {
        return type;
    }

    /**
     * Get the paint location in the paint world.
     * @return The paint location.
     */
    public Point2D.Double getLocation() {
        return loc;
    }


    /**
     * Set the paint location in the canvas.  The origin (0,0) is the top left corner of the canvas.
     * @param loc  The paint coordinate.
     */
    public void setLocation(Point2D.Double loc) {
        this.loc = loc;
    }

    /**
     * Check if the paint object is colorable.  For example, images are not colorable and would return false.
     */
    public boolean isColorable() {
        return false;
    }

    /**
     * Get the paintable object color.
     * @return The paintable object color
     */
    public String getColor() {
        return color;
    }

    /**
     * Set the paintable object color.
     * @param color The new color
     */
    public void setColor(String color) {
        if (isColorable()) {
            this.color = color;
        }
    }



    /**
     * Get the velocity of the paint.
     * @return The paint velocity
     */
    public  Point2D.Double getVelocity() {
        return vel;
    }

    /**
     * Set the paintable object velocity.
     * @param vel The new color
     */
    public void setVelocity(Point2D.Double vel) {
        this.vel = vel;
    }

    public void changeAngle() {
        this.angle *= -1;
    }

    public int getAngle() {
        return this.angle;
    }



    public int getDistance() {
        return this.distance;
    }


    public void setDistance(int d) {
        this.distance = d;
    }

    /**
     * Get the paint object strategy.
     * @return The paint object strategy
     */
    public IUpdateStrategy getStrategy() {
        return strategy;
    }

    /**
     * Set the new strategy.
     * @param strategy The new strategy.
     */
    public void setStrategy(IUpdateStrategy strategy) {

        this.strategy = strategy;
        this.color = strategy.getColor();
    }

    public void setNotUpdateCount(int c) {
        this.notUpdateCount = c;
    }

    public int getNotUpdateCount() {
        return notUpdateCount;
    }

}

