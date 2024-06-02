package pacman.ghosts.ghostStates;

import pacman.ghosts.Ghost;

/**
 * Class representing the chase state of a ghost.
 * This class extends GhostState and implements Cloneable.
 */
public class ChaseState extends GhostState implements Cloneable {

    /**
     * Constructor to initialize the chase state for a ghost.
     *
     * @param ghost The ghost associated with this state.
     */
    public ChaseState(Ghost ghost) {
        super(ghost);
    }

    /**
     * Handle the event when a super Pac-Gum is eaten.
     * Switches the ghost to frightened mode.
     */
    @Override
    public void superPacGumEaten() {
        ghost.switchFrightenedMode();
    }

    /**
     * Create a clone of this chase state.
     *
     * @return A clone of this chase state.
     */
    @Override
    public ChaseState clone() {
        return (ChaseState) super.clone();
    }

    /**
     * Handle the event when the timer mode is over.
     * Switches the ghost to scatter mode.
     */
    @Override
    public void timerModeOver() {
        ghost.switchScatterMode();
    }

    /**
     * Get the target position for the chase state.
     *
     * @return An array containing the target position.
     */
    @Override
    public int[] getTargetPosition() {
        return ghost.getStrategy().getChaseTargetPosition();
    }
}
