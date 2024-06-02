package pacman.ghosts;

import pacman.ghosts.ghostStrategies.YellowGhostStrategy;

/**
 * Class representing the Yellow Ghost in the Pac-Man game.
 * This class extends the Ghost class.
 */
public class YellowGhost extends Ghost {

    /**
     * Constructor to initialize the Yellow Ghost at a specific position.
     *
     * @param xPos The x position of the Yellow Ghost.
     * @param yPos The y position of the Yellow Ghost.
     */
    public YellowGhost(int xPos, int yPos) {
        super(xPos, yPos, "YellowGhost.png");
        setStrategy(new YellowGhostStrategy(this));
    }

    /**
     * Create a clone of this Yellow Ghost.
     *
     * @return A clone of this Yellow Ghost.
     */
    @Override
    public Ghost clone() {
        YellowGhost clonedGhost = (YellowGhost) super.clone();
        clonedGhost.setStrategy(this.strategy.clone());
        return clonedGhost;
    }
}
