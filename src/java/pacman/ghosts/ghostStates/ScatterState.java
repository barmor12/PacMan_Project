package pacman.ghosts.ghostStates;

import pacman.ghosts.Ghost;

/**
 * Class representing the scatter state of a ghost.
 * This class extends GhostState and implements Cloneable.
 */
public class ScatterState extends GhostState implements Cloneable {

    /**
     * Constructor to initialize the scatter state for a ghost.
     *
     * @param ghost The ghost associated with this state.
     */
    public ScatterState(Ghost ghost) {
        super(ghost);
    }

    /**
     * Create a clone of this scatter state.
     *
     * @return A clone of this scatter state.
     */
    @Override
    public ScatterState clone() {
        return (ScatterState) super.clone();
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
     * Handle the event when the timer for the current mode is over.
     * Switches the ghost to chase mode.
     */
    @Override
    public void timerModeOver() {
        ghost.switchChaseMode();
    }

    /**
     * Get the target position for the scatter state.
     *
     * @return An array containing the target position.
     */
    @Override
    public int[] getTargetPosition() {
        return ghost.getStrategy().getScatterTargetPosition();
    }
}
