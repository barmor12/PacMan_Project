package pacman.game;

import pacman.entities.PacGumEntity;
import pacman.entities.SuperPacGumEntity;
import pacman.ghosts.Ghost;

/**
 * Interface representing a subject in the observer pattern.
 * Subjects notify their observers of changes to their state.
 */
public interface Subject {

    /**
     * Remove an observer from the list of observers.
     *
     * @param observer The observer to remove.
     */
    void removeObserver(Observer observer);

    /**
     * Register an observer to the list of observers.
     *
     * @param observer The observer to register.
     */
    void registerObserver(Observer observer);

    /**
     * Notify observers that a Super Pac-Gum has been eaten.
     *
     * @param spg The Super Pac-Gum entity that was eaten.
     */
    void notifyObserversSuperPacGumEaten(SuperPacGumEntity spg);

    /**
     * Notify observers that a Pac-Gum has been eaten.
     *
     * @param pg The Pac-Gum entity that was eaten.
     */
    void notifyObserversPacGumEaten(PacGumEntity pg);

    /**
     * Notify observers of a collision with a ghost.
     *
     * @param gh The Ghost entity involved in the collision.
     */
    void notifyObserversGhostCollision(Ghost gh);
}
