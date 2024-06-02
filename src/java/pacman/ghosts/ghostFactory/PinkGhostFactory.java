package pacman.ghosts.ghostFactory;

import pacman.ghosts.Ghost;
import pacman.ghosts.PinkGhost;

/**
 * Factory class for creating PinkGhost entities.
 * This class extends AbstractGhostFactory.
 */
public class PinkGhostFactory extends AbstractGhostFactory {

    /**
     * Create a PinkGhost entity at the specified position.
     *
     * @param xPos X position of the ghost.
     * @param yPos Y position of the ghost.
     * @return The created PinkGhost entity.
     */
    @Override
    public Ghost createGhost(int xPos, int yPos) {
        return new PinkGhost(xPos, yPos);
    }
}
