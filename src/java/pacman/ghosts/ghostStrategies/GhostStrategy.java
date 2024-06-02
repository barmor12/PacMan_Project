package pacman.ghosts.ghostStrategies;

/**
 * Interface representing the strategy for a ghost.
 * Defines methods for getting target positions in chase and scatter modes.
 */
public interface GhostStrategy {

    /**
     * Get the target position for the chase mode.
     *
     * @return An array containing the target position for chase mode.
     */
    int[] getChaseTargetPosition();

    /**
     * Get the target position for the scatter mode.
     *
     * @return An array containing the target position for scatter mode.
     */
    int[] getScatterTargetPosition();

    /**
     * Create a clone of this ghost strategy.
     *
     * @return A clone of this ghost strategy.
     */
    GhostStrategy clone();
}
