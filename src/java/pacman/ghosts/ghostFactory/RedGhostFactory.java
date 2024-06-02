package pacman.ghosts.ghostFactory;

import pacman.ghosts.Ghost;
import pacman.ghosts.RedGhost;

/**
 * Factory class for creating RedGhost entities.
 * This class extends AbstractGhostFactory.
 */
public class RedGhostFactory extends AbstractGhostFactory {

    /**
     * Create a RedGhost entity at the specified position.
     *
     * @param xPos X position of the ghost.
     * @param yPos Y position of the ghost.
     * @return The created RedGhost entity.
     */
    @Override
    public Ghost createGhost(int xPos, int yPos) {
        return new RedGhost(xPos, yPos);
    }
}
