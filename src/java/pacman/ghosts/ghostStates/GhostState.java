package pacman.ghosts.ghostStates;

import pacman.ghosts.Ghost;
import pacman.helpers.Utils;
import pacman.helpers.WallCollisionDetector;

/**
 * Abstract class representing the state of a ghost.
 * This class provides common behaviors and state transitions for ghosts.
 */
public abstract class GhostState {
    protected Ghost ghost;

    /**
     * Constructor to initialize the ghost state.
     *
     * @param ghost The ghost associated with this state.
     */
    public GhostState(Ghost ghost) {
        this.ghost = ghost;
    }

    /**
     * Create a clone of this ghost state.
     *
     * @return A clone of this ghost state.
     */
    public GhostState clone() {
        try {
            return (GhostState) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Handle the event when a super Pac-Gum is eaten.
     */
    public void superPacGumEaten() {}

    /**
     * Handle the event when the timer for the current mode is over.
     */
    public void timerModeOver() {}

    /**
     * Handle the event when the timer for frightened mode is over.
     */
    public void timerFrightenedModeOver() {}

    /**
     * Handle the event when the ghost is eaten.
     */
    public void eaten() {}

    /**
     * Handle the event when the ghost is outside the house.
     */
    public void outsideHouse() {}

    /**
     * Handle the event when the ghost is inside the house.
     */
    public void insideHouse() {}

    /**
     * Get the target position for the ghost in the current state.
     *
     * @return An array containing the target position.
     */
    public int[] getTargetPosition() {
        return new int[2];
    }

    /**
     * Compute the next direction for the ghost based on its current position and target position.
     */
    public void computeNextDirection() {
        int new_xSpd = 0;
        int new_ySpd = 0;

        if (!ghost.isOnGrid()) return;
        if (!ghost.isInGameplayWindow()) return;

        double minDist = Double.MAX_VALUE;

        // Check possible directions and compute distances
        if (ghost.getxSpeed() <= 0 && !WallCollisionDetector.isCollision(ghost, -ghost.getEntitySpeed(), 0)) {
            double distance = Utils.getDistance(ghost.getxPos() - ghost.getEntitySpeed(), ghost.getyPos(), getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                new_xSpd = -ghost.getEntitySpeed();
                new_ySpd = 0;
                minDist = distance;
            }
        }

        if (ghost.getxSpeed() >= 0 && !WallCollisionDetector.isCollision(ghost, ghost.getEntitySpeed(), 0)) {
            double distance = Utils.getDistance(ghost.getxPos() + ghost.getEntitySpeed(), ghost.getyPos(), getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                new_xSpd = ghost.getEntitySpeed();
                new_ySpd = 0;
                minDist = distance;
            }
        }

        if (ghost.getySpeed() <= 0 && !WallCollisionDetector.isCollision(ghost, 0, -ghost.getEntitySpeed())) {
            double distance = Utils.getDistance(ghost.getxPos(), ghost.getyPos() - ghost.getEntitySpeed(), getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                new_xSpd = 0;
                new_ySpd = -ghost.getEntitySpeed();
                minDist = distance;
            }
        }

        if (ghost.getySpeed() >= 0 && !WallCollisionDetector.isCollision(ghost, 0, ghost.getEntitySpeed())) {
            double distance = Utils.getDistance(ghost.getxPos(), ghost.getyPos() + ghost.getEntitySpeed(), getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                new_xSpd = 0;
                new_ySpd = ghost.getEntitySpeed();
                minDist = distance;
            }
        }

        if (new_xSpd == 0 && new_ySpd == 0) return;

        // Set the ghost's speed based on the computed direction
        if (Math.abs(new_xSpd) != Math.abs(new_ySpd)) {
            ghost.setxSpeed(new_xSpd);
            ghost.setySpeed(new_ySpd);
        } else {
            if (new_xSpd != 0) {
                ghost.setxSpeed(0);
                ghost.setxSpeed(new_ySpd);
            } else {
                ghost.setxSpeed(new_xSpd);
                ghost.setySpeed(0);
            }
        }
    }
}
