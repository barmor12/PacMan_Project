package pacman.ghosts.ghostFactory;

import pacman.ghosts.BlueGhost;
import pacman.ghosts.Ghost;

/**
 * Factory class for creating BlueGhost entities.
 * This class extends AbstractGhostFactory.
 */
public class BlueGhostFactory extends AbstractGhostFactory {

    /**
     * Create a BlueGhost entity at the specified position.
     *
     * @param xPos X position of the ghost.
     * @param yPos Y position of the ghost.
     * @return The created BlueGhost entity.
     */
    @Override
    public Ghost createGhost(int xPos, int yPos) {
        return new BlueGhost(xPos, yPos);
    }
}
