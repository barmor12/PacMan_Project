package pacman.ghosts.ghostStrategies;

import pacman.game.GameSession;
import pacman.game.GameplayPanel;
import pacman.ghosts.Ghost;
import pacman.helpers.Utils;

/**
 * Class representing the strategy for the Yellow Ghost.
 * This strategy includes behaviors for chase and scatter modes.
 */
public class YellowGhostStrategy implements GhostStrategy {
    private Ghost ghostEntity;

    /**
     * Constructor to initialize the Yellow Ghost strategy.
     *
     * @param ghostEntity The ghost associated with this strategy.
     */
    public YellowGhostStrategy(Ghost ghostEntity) {
        this.ghostEntity = ghostEntity;
    }

    /**
     * Create a clone of this Yellow Ghost strategy.
     *
     * @return A clone of this Yellow Ghost strategy.
     */
    @Override
    public GhostStrategy clone() {
        return new YellowGhostStrategy(ghostEntity);
    }

    /**
     * Get the target position for the chase mode.
     * This target position is based on the distance to Pacman.
     * If the distance is greater than or equal to 256 units, the target is Pacman's position.
     * Otherwise, the target is the scatter position.
     *
     * @return An array containing the target position for chase mode.
     */
    @Override
    public int[] getChaseTargetPosition() {
        int[] targetPosition = new int[2];
        double distance = Utils.getDistance(ghostEntity.getxPos(), ghostEntity.getyPos(), GameSession.getPacmanEntity().getxPos(), GameSession.getPacmanEntity().getyPos());

        if (distance >= 256) {
            targetPosition[0] = GameSession.getPacmanEntity().getxPos();
            targetPosition[1] = GameSession.getPacmanEntity().getyPos();
        } else {
            targetPosition = getScatterTargetPosition();
        }
        return targetPosition;
    }

    /**
     * Get the target position for the scatter mode.
     * The target position is the bottom-left corner of the gameplay area.
     *
     * @return An array containing the target position for scatter mode.
     */
    @Override
    public int[] getScatterTargetPosition() {
        return new int[] { 0, GameplayPanel.gameHeight };
    }
}
