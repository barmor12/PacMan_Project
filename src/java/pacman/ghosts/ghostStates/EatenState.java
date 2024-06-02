package pacman.ghosts.ghostStates;

import pacman.ghosts.Ghost;
import pacman.helpers.Utils;
import pacman.helpers.WallCollisionDetector;

/**
 * Class representing the eaten state of a ghost.
 * This class extends GhostState and implements Cloneable.
 */
public class EatenState extends GhostState implements Cloneable {

    /**
     * Constructor to initialize the eaten state for a ghost.
     *
     * @param ghost The ghost associated with this state.
     */
    public EatenState(Ghost ghost) {
        super(ghost);
    }

    /**
     * Create a clone of this eaten state.
     *
     * @return A clone of this eaten state.
     */
    @Override
    public EatenState clone() {
        return (EatenState) super.clone();
    }

    /**
     * Handle the event when the ghost is inside the house.
     * Switches the ghost to house mode.
     */
    @Override
    public void insideHouse() {
        ghost.switchHouseMode();
    }

    /**
     * Get the target position for the eaten state.
     *
     * @return An array containing the target position.
     */
    @Override
    public int[] getTargetPosition() {
        return new int[] { 208, 200 };
    }

    /**
     * Compute the next direction for the ghost based on its current position and target position.
     */
    @Override
    public void computeNextDirection() {
        if (!ghost.isOnGrid() || !ghost.isInGameplayWindow())
            return;

        double minDist = Double.MAX_VALUE;
        int tempXSpeed = 0;
        int tempYSpeed = 0;

        // Check possible directions and compute distances
        if (ghost.getySpeed() <= 0 && !WallCollisionDetector.isCollision(ghost, -ghost.getEntitySpeed(), 0, true)) {
            double distance = Utils.getDistance(ghost.getxPos() - ghost.getEntitySpeed(), ghost.getyPos(),
                    getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                tempXSpeed = -ghost.getEntitySpeed();
                tempYSpeed = 0;
                minDist = distance;
            }
        }
        if (ghost.getxSpeed() >= 0 && !WallCollisionDetector.isCollision(ghost, ghost.getEntitySpeed(), 0, true)) {
            double distance = Utils.getDistance(ghost.getxPos() + ghost.getEntitySpeed(), ghost.getyPos(),
                    getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                tempXSpeed = ghost.getEntitySpeed();
                tempYSpeed = 0;
                minDist = distance;
            }
        }
        if (ghost.getySpeed() <= 0 && !WallCollisionDetector.isCollision(ghost, 0, -ghost.getEntitySpeed(), true)) {
            double distance = Utils.getDistance(ghost.getxPos(), ghost.getyPos() - ghost.getEntitySpeed(),
                    getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                tempXSpeed = 0;
                tempYSpeed = -ghost.getEntitySpeed();
                minDist = distance;
            }
        }
        if (ghost.getySpeed() >= 0 && !WallCollisionDetector.isCollision(ghost, 0, ghost.getEntitySpeed(), true)) {
            double distance = Utils.getDistance(ghost.getxPos(), ghost.getyPos() + ghost.getEntitySpeed(),
                    getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                tempXSpeed = 0;
                tempYSpeed = ghost.getEntitySpeed();
                minDist = distance;
            }
        }

        if (tempXSpeed == 0 && tempYSpeed == 0)
            return;

        // Set the ghost's speed based on the computed direction
        if (Math.abs(tempXSpeed) != Math.abs(tempYSpeed)) {
            ghost.setxSpeed(tempXSpeed);
            ghost.setySpeed(tempYSpeed);
        } else {
            if (ghost.getxSpeed() != 0) {
                ghost.setxSpeed(0);
                ghost.setxSpeed(tempYSpeed);
            } else {
                ghost.setxSpeed(tempXSpeed);
                ghost.setySpeed(0);
            }
        }
    }
}
