package pacman.game;

import pacman.entities.PacGumEntity;
import pacman.entities.SuperPacGumEntity;
import pacman.ghosts.Ghost;

/**
 * Interface representing an observer in the observer pattern.
 * Observers are notified of changes to certain entities in the game.
 */
public interface Observer {

    /**
     * Called when a Pac-Gum is eaten.
     *
     * @param pg The Pac-Gum entity that was eaten.
     */
    void updatePacGumEaten(PacGumEntity pg);

    /**
     * Called when a Super Pac-Gum is eaten.
     *
     * @param spg The Super Pac-Gum entity that was eaten.
     */
    void updateSuperPacGumEaten(SuperPacGumEntity spg);

    /**
     * Called when there is a collision with a ghost.
     *
     * @param gh The Ghost entity that was involved in the collision.
     */
    void updateGhostCollision(Ghost gh);
}
