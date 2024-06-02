package pacman.helpers;

import pacman.entities.*;
import pacman.game.GameSession;

/**
 * Class to detect collisions between entities in the game.
 */
public class EntityCollisionDetector {
    private GameSession game;

    /**
     * Constructor to initialize the collision detector with a game session.
     *
     * @param game The game session.
     */
    public EntityCollisionDetector(GameSession game) {
        this.game = game;
    }

    /**
     * Check for a collision between an entity and any other entities of a specified type.
     *
     * @param obj The entity to check for collisions.
     * @param collisionCheck The class of entities to check for collisions with.
     * @return The entity that was collided with, or null if no collision occurred.
     */
    public Entity checkCollision(Entity obj, Class<? extends Entity> collisionCheck) {
        for (Entity e : game.getGameEntities()) {
            if (!e.isDestroyed() && collisionCheck.isInstance(e) && e.getHitbox().contains(obj.getxPos() + obj.getSize() / 2, obj.getyPos() + obj.getSize() / 2)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Check for a rectangular collision between an entity and any other entities of a specified type.
     *
     * @param obj The entity to check for collisions.
     * @param collisionCheck The class of entities to check for collisions with.
     * @return The entity that was collided with, or null if no collision occurred.
     */
    public Entity checkCollisionRect(Entity obj, Class<? extends Entity> collisionCheck) {
        for (Entity e : game.getGameEntities()) {
            if (!e.isDestroyed() && collisionCheck.isInstance(e) && e.getHitbox().intersects(obj.getHitbox())) {
                return e;
            }
        }
        return null;
    }
}
