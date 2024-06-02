package pacman.entities;

import java.util.ArrayList;
import java.util.List;

import pacman.game.GameSession;
import pacman.game.Observer;
import pacman.game.Subject;
import pacman.ghosts.Ghost;
import pacman.ghosts.ghostStates.EatenState;
import pacman.ghosts.ghostStates.FrightenedState;
import pacman.helpers.EntityCollisionDetector;
import pacman.helpers.KeyHandler;
import pacman.helpers.WallCollisionDetector;

/**
 * Class representing the Pacman entity in the game.
 * This class extends MovingEntity and implements Subject.
 */
public class PacmanEntity extends MovingEntity implements Subject {
    private static PacmanEntity instance = null;
    private EntityCollisionDetector collisionDetector;
    private List<Observer> observerCollection;
    private int lives = 3;
    private boolean pacmanDeath = false;

    /**
     * Private constructor to initialize the Pacman entity.
     *
     * @param xPos X position of Pacman.
     * @param yPos Y position of Pacman.
     */
    private PacmanEntity(int xPos, int yPos) {
        super(32, xPos, yPos, 2, "pacman.png", 4, 0.3f);
        observerCollection = new ArrayList<>();
    }

    /**
     * Singleton method to get the instance of PacmanEntity.
     *
     * @param xPos X position of Pacman.
     * @param yPos Y position of Pacman.
     * @return The singleton instance of PacmanEntity.
     */
    public static PacmanEntity getInstance(int xPos, int yPos) {
        if (instance == null) {
            instance = new PacmanEntity(xPos, yPos);
        }
        return instance;
    }

    /**
     * Handle Pacman's movements based on key inputs.
     *
     * @param keyHandler Key handler for capturing user inputs.
     */
    public void handleInput(KeyHandler keyHandler) {
        int newXSpeed = 0;
        int newYSpeed = 0;
        // Pacman must be on a "case" of the game area and within the gameplay window
        if (!isOnGrid() || !isInGameplayWindow())
            return;
        if (pacmanDeath) {
            pacmanDeath = false;
            System.out.println("Pacman Respawned");
            keyHandler.leftKey.isPressed = false;
            keyHandler.rightKey.isPressed = false;
            keyHandler.upKey.isPressed = false;
            keyHandler.downKey.isPressed = false;
            GameSession.setFirstUserInput(false);
            // return;
        }
        // Change Pacman's direction based on the pressed keys
        if (keyHandler.leftKey.isPressed && xSpeed >= 0
                && !WallCollisionDetector.isCollision(this, -entitySpeed, 0)) {
            newXSpeed = -entitySpeed;
        }
        if (keyHandler.rightKey.isPressed && xSpeed <= 0
                && !WallCollisionDetector.isCollision(this, entitySpeed, 0)) {
            newXSpeed = entitySpeed;
        }
        if (keyHandler.upKey.isPressed && ySpeed >= 0
                && !WallCollisionDetector.isCollision(this, 0, -entitySpeed)) {
            newYSpeed = -entitySpeed;
        }
        if (keyHandler.downKey.isPressed && ySpeed <= 0
                && !WallCollisionDetector.isCollision(this, 0, entitySpeed)) {
            newYSpeed = entitySpeed;
        }

        // If no movement, return
        if (newXSpeed == 0 && newYSpeed == 0)
            return;

        // If it's the first input, set the flag
        if (!GameSession.getIsFirstUserInput())
            GameSession.setFirstUserInput(true);

        // Update entitySpeed values
        if (Math.abs(newXSpeed) != Math.abs(newYSpeed)) {
            xSpeed = newXSpeed;
            ySpeed = newYSpeed;
        } else {
            if (xSpeed != 0) {
                xSpeed = 0;
                ySpeed = newYSpeed;
            } else {
                xSpeed = newXSpeed;
                ySpeed = 0;
            }
        }
    }

    /**
     * Refresh the state of Pacman, checking for collisions and updating position.
     */
    @Override
    public void refresh() {
        // Check for collisions with PacGum, SuperPacGum, and Ghosts
        // If there is a collision, notify the observers
        PacGumEntity pacGum = (PacGumEntity) collisionDetector.checkCollision(this, PacGumEntity.class);
        if (pacGum != null) {
            notifyObserversPacGumEaten(pacGum);
        }

        SuperPacGumEntity superPacGum = (SuperPacGumEntity) collisionDetector.checkCollision(this,
                SuperPacGumEntity.class);
        if (superPacGum != null) {
            notifyObserversSuperPacGumEaten(superPacGum);
        }

        Ghost ghost = (Ghost) collisionDetector.checkCollision(this, Ghost.class);
        if (ghost != null) {

            // if ghost isnt in frightened state, pacman dies
            if (!(ghost.getState() instanceof FrightenedState) && !(ghost.getState() instanceof EatenState))
                handleDeath(); // Handle Pacman's death

            notifyObserversGhostCollision(ghost); // Notify observers

        }
        // If there is no collision with a wall, update Pacman's position
        if (!WallCollisionDetector.isCollision(this, xSpeed, ySpeed)) {
            updatePosition();
        }
    }

    /**
     * Set the collision detector for Pacman.
     *
     * @param collisionDetector The collision detector to set.
     */
    public void setCollisionDetector(EntityCollisionDetector collisionDetector) {
        this.collisionDetector = collisionDetector;
    }

    @Override
    public void registerObserver(Observer observer) {
        observerCollection.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerCollection.remove(observer);
    }

    @Override
    public void notifyObserversPacGumEaten(PacGumEntity pg) {
        observerCollection.forEach(obs -> obs.updatePacGumEaten(pg));
    }

    @Override
    public void notifyObserversSuperPacGumEaten(SuperPacGumEntity spg) {
        observerCollection.forEach(obs -> obs.updateSuperPacGumEaten(spg));
    }

    @Override
    public void notifyObserversGhostCollision(Ghost gh) {
        observerCollection.forEach(obs -> obs.updateGhostCollision(gh));
    }

    public int getLives() {
        return lives;
    }

    public void resetLives() {
        lives = 3;
    }

    /**
     * Handle the death of Pacman, reducing lives and resetting position.
     */
    public void handleDeath() {
        if (pacmanDeath || lives <= 0)
            return;
        lives--;
        System.out.println("Lives: " + lives);
        setxPos(208);
        setyPos(360);
        pacmanDeath = true;
        xSpeed = 0;
        ySpeed = 0;
    }

    /**
     * Reset Pacman to the initial state.
     */
    public void reset() {
        setxPos(208);
        setyPos(360);
        xSpeed = 0;
        ySpeed = 0;
        pacmanDeath = false;
        lives = 3;
    }
}
