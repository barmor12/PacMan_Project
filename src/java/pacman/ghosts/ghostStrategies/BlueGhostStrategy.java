package pacman.ghosts.ghostStrategies;

import pacman.game.GameSession;
import pacman.game.GameplayPanel;
import pacman.ghosts.Ghost;
import pacman.helpers.Utils;

/**
 * Class representing the strategy for the Blue Ghost.
 * This strategy includes behaviors for chase and scatter modes.
 */
public class BlueGhostStrategy implements GhostStrategy {
    private Ghost otherGhost;

    /**
     * Constructor to initialize the Blue Ghost strategy.
     *
     * @param ghost The ghost associated with this strategy.
     */
    public BlueGhostStrategy(Ghost ghost) {
        this.otherGhost = ghost;
    }

    /**
     * Create a clone of this Blue Ghost strategy.
     *
     * @return A clone of this Blue Ghost strategy.
     */
    @Override
    public GhostStrategy clone() {
        return new BlueGhostStrategy(otherGhost);
    }

    /**
     * Get the target position for the chase mode.
     * This target position is based on Pacman's facing position and the other ghost's position.
     *
     * @return An array containing the target position.
     */
    @Override
    public int[] getChaseTargetPosition() {
        int[] position = new int[2];
        int[] pacmanFacingPosition = Utils.getPointDistanceDirection(GameSession.getPacmanEntity().getxPos(), GameSession.getPacmanEntity().getyPos(), 32d, Utils.directionConverter(GameSession.getPacmanEntity().getDirection()));
        double distanceOtherGhost = Utils.getDistance(pacmanFacingPosition[0], pacmanFacingPosition[1], otherGhost.getxPos(), otherGhost.getyPos());
        double directionOtherGhost = Utils.getDirection(otherGhost.getxPos(), otherGhost.getyPos(), pacmanFacingPosition[0], pacmanFacingPosition[1]);
        int[] redGhostVectorPosition = Utils.getPointDistanceDirection(pacmanFacingPosition[0], pacmanFacingPosition[1], distanceOtherGhost, directionOtherGhost);
        position[0] = redGhostVectorPosition[0];
        position[1] = redGhostVectorPosition[1];
        return position;
    }

    /**
     * Get the target position for the scatter mode.
     * The target position is the bottom-right corner of the gameplay area.
     *
     * @return An array containing the target position.
     */
    @Override
    public int[] getScatterTargetPosition() {
        return new int[] { GameplayPanel.gameWidth, GameplayPanel.gameHeight };
    }
}
