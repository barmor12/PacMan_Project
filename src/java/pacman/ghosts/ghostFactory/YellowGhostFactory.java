package pacman.ghosts.ghostFactory;

import pacman.ghosts.Ghost;
import pacman.ghosts.YellowGhost;

/**
 * Factory class for creating YellowGhost entities.
 * This class extends AbstractGhostFactory.
 */
public class YellowGhostFactory extends AbstractGhostFactory {

    /**
     * Create a YellowGhost entity at the specified position.
     *
     * @param xPos X position of the ghost.
     * @param yPos Y position of the ghost.
     * @return The created YellowGhost entity.
     */
    @Override
    public Ghost createGhost(int xPos, int yPos) {
        return new YellowGhost(xPos, yPos);
    }
}
