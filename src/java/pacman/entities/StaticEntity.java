package pacman.entities;

import java.awt.*;

/**
 * Abstract class representing a static entity in the game.
 * This class extends the Entity class and adds a hitbox.
 */
public abstract class StaticEntity extends Entity {

    // Rectangle representing the hitbox of the entity
    protected Rectangle hitbox;

    /**
     * Constructor to initialize a static entity.
     *
     * @param size Size of the entity.
     * @param xPos X position of the entity.
     * @param yPos Y position of the entity.
     */
    public StaticEntity(int size, int xPos, int yPos) {
        super(size, xPos, yPos);
        this.hitbox = new Rectangle(xPos, yPos, size, size);
    }

    /**
     * Get the hitbox of the entity.
     *
     * @return Rectangle representing the hitbox of the entity.
     */
    public Rectangle getHitbox() {
        return hitbox;
    }
}
