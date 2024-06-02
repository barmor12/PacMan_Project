package pacman.entities;

import java.awt.*;

/**
 * Class representing a Pac-Gum entity in the game.
 * This class extends the StaticEntity class.
 */
public class PacGumEntity extends StaticEntity {

    /**
     * Constructor to initialize a Pac-Gum entity.
     *
     * @param xPos X position of the Pac-Gum.
     * @param yPos Y position of the Pac-Gum.
     */
    public PacGumEntity(int xPos, int yPos) {
        super(4, xPos + 8, yPos + 8);
    }

    /**
     * Render the Pac-Gum on the screen.
     *
     * @param g Graphics object used for rendering.
     */
    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(255, 183, 174));
        g.fillRect(xPos, yPos, size, size);
    }
}
