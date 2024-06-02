package pacman.ghosts.ghostStates;

import pacman.ghosts.Ghost;
import pacman.helpers.Utils;

/**
 * Class representing the frightened state of a ghost.
 * This class extends GhostState and implements Cloneable.
 */
public class FrightenedState extends GhostState implements Cloneable {

    /**
     * Constructor to initialize the frightened state for a ghost.
     *
     * @param ghost The ghost associated with this state.
     */
    public FrightenedState(Ghost ghost) {
        super(ghost);
    }

    /**
     * Create a clone of this frightened state.
     *
     * @return A clone of this frightened state.
     */
    @Override
    public FrightenedState clone() {
        return (FrightenedState) super.clone();
    }

    /**
     * Handle the event when the ghost is eaten.
     * Switches the ghost to eaten mode.
     */
    @Override
    public void eaten() {
        ghost.switchEatenMode();
    }

    /**
     * Handle the event when the timer for frightened mode is over.
     * Switches the ghost to either chase mode or scatter mode.
     */
    @Override
    public void timerFrightenedModeOver() {
        ghost.switchChaseModeOrScatterMode();
    }

    /**
     * Get the target position for the frightened state.
     * The target position is randomly determined within a certain range.
     *
     * @return An array containing the target position.
     */
    @Override
    public int[] getTargetPosition() {
        int[] position = new int[2];

        boolean randomAxis = Utils.randomBool();
        position[0] = ghost.getxPos() + (randomAxis ? Utils.randomInt(-1, 1) * 32 : 0);
        position[1] = ghost.getyPos() + (!randomAxis ? Utils.randomInt(-1, 1) * 32 : 0);
        return position;
    }
}
