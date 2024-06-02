package pacman.ghosts;

import pacman.ghosts.ghostStrategies.PinkGhostStrategy;

/**
 * Class representing the Pink Ghost in the Pac-Man game.
 * This class extends the Ghost class.
 */
public class PinkGhost extends Ghost {

    /**
     * Constructor to initialize the Pink Ghost at a specific position.
     *
     * @param xPos The x position of the Pink Ghost.
     * @param yPos The y position of the Pink Ghost.
     */
    public PinkGhost(int xPos, int yPos) {
        super(xPos, yPos, "PinkGhost.png");
        setStrategy(new PinkGhostStrategy());
    }

    /**
     * Create a clone of this Pink Ghost.
     *
     * @return A clone of this Pink Ghost.
     */
    @Override
    public Ghost clone() {
        PinkGhost clonedGhost = (PinkGhost) super.clone();
        clonedGhost.setStrategy(new PinkGhostStrategy());
        clonedGhost.state = this.state.clone();
        return clonedGhost;
    }
}
