package pacman.helpers;

import java.awt.*;

import pacman.entities.Entity;
import pacman.entities.GhostHouseEntity;
import pacman.entities.WallEntity;
import pacman.game.GameSession;

/**
 * Class to detect collisions with walls in the game.
 */
public class WallCollisionDetector {

    /**
     * Check for a collision between an entity and walls.
     *
     * @param obj The entity to check for collisions.
     * @param dx The change in x position.
     * @param dy The change in y position.
     * @return True if a collision is detected, false otherwise.
     */
    public static boolean isCollision(Entity obj, int dx, int dy) {
        Rectangle r = new Rectangle(obj.getxPos() + dx, obj.getyPos() + dy, obj.getSize(), obj.getSize());
        for (WallEntity w : GameSession.getWallEntities()) {
            if (w.getHitbox().intersects(r)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check for a collision between an entity and walls, with an option to ignore ghost houses.
     *
     * @param obj The entity to check for collisions.
     * @param dx The change in x position.
     * @param dy The change in y position.
     * @param ignoreGhostHouses Whether to ignore ghost houses in the collision check.
     * @return True if a collision is detected, false otherwise.
     */
    public static boolean isCollision(Entity obj, int dx, int dy, boolean ignoreGhostHouses) {
        Rectangle r = new Rectangle(obj.getxPos() + dx, obj.getyPos() + dy, obj.getSize(), obj.getSize());
        for (WallEntity w : GameSession.getWallEntities()) {
            if (!(ignoreGhostHouses && w instanceof GhostHouseEntity) && w.getHitbox().intersects(r)) {
                return true;
            }
        }
        return false;
    }
}
