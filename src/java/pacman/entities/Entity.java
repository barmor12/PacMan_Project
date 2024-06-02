package pacman.entities;

import java.awt.*;

/**
 * Abstract class representing a generic game entity.
 */
public abstract class Entity implements Cloneable {
    // Size of the entity
    protected int size;

    // X position of the entity
    protected int xPos;

    // Y position of the entity
    protected int yPos;

    // Flag to check if the entity is destroyed
    protected boolean destroyed = false;

    /**
     * Constructor to initialize an entity.
     *
     * @param size Size of the entity.
     * @param xPos X position of the entity.
     * @param yPos Y position of the entity.
     */
    public Entity(int size, int xPos, int yPos) {
        this.size = size;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Method to clone the entity.
     *
     * @return A clone of the entity.
     */
    public Entity clone() {
        Entity clone = null;
        try {
            clone = (Entity) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    /**
     * Method to refresh the entity state.
     */
    public void refresh() {}

    /**
     * Method to render the entity.
     *
     * @param g Graphics object used for rendering.
     */
    public void render(Graphics2D g) {}

    /**
     * Method to destroy the entity.
     */
    public void destroy() {
        this.xPos = -32;
        this.yPos = -32;
        destroyed = true;
    }

    /**
     * Check if the entity is destroyed.
     *
     * @return True if the entity is destroyed, false otherwise.
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    /**
     * Get the size of the entity.
     *
     * @return Size of the entity.
     */
    public int getSize() {
        return size;
    }

    /**
     * Get the X position of the entity.
     *
     * @return X position of the entity.
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * Get the Y position of the entity.
     *
     * @return Y position of the entity.
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * Set the X position of the entity.
     *
     * @param xPos New X position of the entity.
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * Set the Y position of the entity.
     *
     * @param yPos New Y position of the entity.
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * Get the hitbox of the entity.
     *
     * @return Rectangle representing the hitbox of the entity.
     */
    public abstract Rectangle getHitbox();
}
