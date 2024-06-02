package pacman.ghosts;

import pacman.ghosts.ghostStrategies.RedGhostStrategy;

/**
 * Class representing the Red Ghost in the Pac-Man game.
 * This class extends the Ghost class.
 */
public class RedGhost extends Ghost {

    /**
     * Constructor to initialize the Red Ghost at a specific position.
     *
     * @param xPos The x position of the Red Ghost.
     * @param yPos The y position of the Red Ghost.
     */
    public RedGhost(int xPos, int yPos) {
        super(xPos, yPos, "RedGhost.png");
        setStrategy(new RedGhostStrategy());
    }

    /**
     * Create a clone of this Red Ghost.
     *
     * @return A clone of this Red Ghost.
     */
    @Override
    public Ghost clone() {
        RedGhost clonedGhost = (RedGhost) super.clone();
        clonedGhost.setStrategy(new RedGhostStrategy());
        clonedGhost.state = HouseState;
        return clonedGhost;
    }
}
