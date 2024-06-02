package pacman.ghosts.ghostStrategies;

import pacman.game.GameSession;
import pacman.helpers.Utils;

/**
 * Class representing the strategy for the Pink Ghost.
 * This strategy includes behaviors for chase and scatter modes.
 */
public class PinkGhostStrategy implements GhostStrategy {

    /**
     * Get the target position for the chase mode.
     * This target position is based on Pacman's current direction and a fixed distance ahead.
     *
     * @return An array containing the target position for chase mode.
     */
    @Override
    public int[] getChaseTargetPosition() {
        int[] targetPosition = new int[2];
        int[] pacmanDirectionPosition = Utils.getPointDistanceDirection(GameSession.getPacmanEntity().getxPos(), GameSession.getPacmanEntity().getyPos(), 64, Utils.directionConverter(GameSession.getPacmanEntity().getDirection()));
        targetPosition[0] = pacmanDirectionPosition[0];
        targetPosition[1] = pacmanDirectionPosition[1];
        return targetPosition;
    }

    /**
     * Create a clone of this Pink Ghost strategy.
     *
     * @return A clone of this Pink Ghost strategy.
     */
    @Override
    public GhostStrategy clone() {
        return new PinkGhostStrategy();
    }

    /**
     * Get the target position for the scatter mode.
     * The target position is the top-left corner of the gameplay area.
     *
     * @return An array containing the target position for scatter mode.
     */
    @Override
    public int[] getScatterTargetPosition() {
        return new int[] { 0, 0 };
    }
}
