package pacman.ghosts.ghostFactory;

import pacman.ghosts.*;

/**
 * Abstract factory class for creating ghost entities.
 * Subclasses should implement the createGhost method.
 */
public abstract class AbstractGhostFactory {

    /**
     * Create a ghost entity at the specified position.
     *
     * @param xPos X position of the ghost.
     * @param yPos Y position of the ghost.
     * @return The created ghost entity.
     */
    public abstract Ghost createGhost(int xPos, int yPos);
}
