package pacman.ghosts.ghostStates;

import pacman.ghosts.Ghost;
import pacman.helpers.Utils;
import pacman.helpers.WallCollisionDetector;

/**
 * Class representing the house state of a ghost.
 * This class extends GhostState and implements Cloneable.
 */
public class HouseState extends GhostState implements Cloneable {

    /**
     * Constructor to initialize the house state for a ghost.
     *
     * @param ghost The ghost associated with this state.
     */
    public HouseState(Ghost ghost) {
        super(ghost);
    }

    /**
     * Handle the event when the ghost is outside the house.
     * Switches the ghost to either chase mode or scatter mode.
     */
    @Override
    public void outsideHouse() {
        this.ghost.switchChaseModeOrScatterMode();
    }

    /**
     * Create a clone of this house state.
     *
     * @return A clone of this house state.
     */
    @Override
    public HouseState clone() {
        return (HouseState) super.clone();
    }

    /**
     * Get the target position for the house state.
     *
     * @return An array containing the target position.
     */
    @Override
    public int[] getTargetPosition() {
        return new int[] { 208, 168 };
    }

    /**
     * Compute the next direction for the ghost based on its current position and target position.
     */
    @Override
    public void computeNextDirection() {
        int new_xSpd = 0;
        int new_ySpd = 0;

        if (!ghost.isOnGrid() || !ghost.isInGameplayWindow()) {
            return;
        }

        double minDist = Double.MAX_VALUE;

        // Check possible directions and compute distances
        if (ghost.getxSpeed() <= 0 && !WallCollisionDetector.isCollision(ghost, -ghost.getEntitySpeed(), 0, true)) {
            double distance = Utils.getDistance(ghost.getxPos() - ghost.getEntitySpeed(), ghost.getyPos(),
                    getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                new_xSpd = -ghost.getEntitySpeed();
                new_ySpd = 0;
                minDist = distance;
            }
        }
        if (ghost.getxSpeed() >= 0 && !WallCollisionDetector.isCollision(ghost, ghost.getEntitySpeed(), 0, true)) {
            double distance = Utils.getDistance(ghost.getxPos() + ghost.getEntitySpeed(), ghost.getyPos(),
                    getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                new_xSpd = ghost.getEntitySpeed();
                new_ySpd = 0;
                minDist = distance;
            }
        }
        if (ghost.getySpeed() <= 0 && !WallCollisionDetector.isCollision(ghost, 0, -ghost.getEntitySpeed(), true)) {
            double distance = Utils.getDistance(ghost.getxPos(), ghost.getyPos() - ghost.getEntitySpeed(),
                    getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                new_xSpd = 0;
                new_ySpd = -ghost.getEntitySpeed();
                minDist = distance;
            }
        }
        if (ghost.getySpeed() >= 0 && !WallCollisionDetector.isCollision(ghost, 0, ghost.getEntitySpeed(), true)) {
            double distance = Utils.getDistance(ghost.getxPos(), ghost.getyPos() + ghost.getEntitySpeed(),
                    getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                new_xSpd = 0;
                new_ySpd = ghost.getEntitySpeed();
                minDist = distance;
            }
        }

        if (new_xSpd == 0 && new_ySpd == 0) {
            return;
        }

        // Set the ghost's speed based on the computed direction
        if (Math.abs(new_xSpd) != Math.abs(new_ySpd)) {
            ghost.setxSpeed(new_xSpd);
            ghost.setySpeed(new_ySpd);
        } else {
            if (ghost.getxSpeed() != 0) {
                ghost.setxSpeed(0);
                ghost.setxSpeed(new_ySpd);
            } else {
                ghost.setxSpeed(new_xSpd);
                ghost.setySpeed(0);
            }
        }
    }
}
