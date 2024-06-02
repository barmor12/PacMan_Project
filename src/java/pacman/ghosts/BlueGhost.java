package pacman.ghosts;

import pacman.game.GameSession;
import pacman.ghosts.ghostStrategies.BlueGhostStrategy;

/**
 * Class representing the Blue Ghost in the Pac-Man game.
 * This class extends the Ghost class.
 */
public class BlueGhost extends Ghost {

    /**
     * Constructor to initialize the Blue Ghost at a specific position.
     *
     * @param xPos The x position of the Blue Ghost.
     * @param yPos The y position of the Blue Ghost.
     */
    public BlueGhost(int xPos, int yPos) {
        super(xPos, yPos, "BlueGhost.png");
        setStrategy(new BlueGhostStrategy(GameSession.getRedGhostEntity()));
    }

    /**
     * Create a clone of this Blue Ghost.
     *
     * @return A clone of this Blue Ghost.
     */
    @Override
    public Ghost clone() {
        BlueGhost clonedGhost = (BlueGhost) super.clone();
        clonedGhost.setStrategy(new BlueGhostStrategy(GameSession.getRedGhostEntity()));
        clonedGhost.state = this.state;
        return clonedGhost;
    }
}
