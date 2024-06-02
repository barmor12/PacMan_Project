package pacman.entities;

import java.awt.*;

/**
 * Class representing a Super Pac-Gum entity in the game.
 * This class extends StaticEntity.
 */
public class SuperPacGumEntity extends StaticEntity {
    // Frame count used for animation
    private int frameCount = 0;

    /**
     * Constructor to initialize a Super Pac-Gum entity.
     *
     * @param xPos X position of the Super Pac-Gum.
     * @param yPos Y position of the Super Pac-Gum.
     */
    public SuperPacGumEntity(int xPos, int yPos) {
        super(16, xPos, yPos);
    }

    /**
     * Render the Super Pac-Gum on the screen.
     *
     * @param g Graphics object used for rendering.
     */
    @Override
    public void render(Graphics2D g) {
        // Flashing effect: alternate between visible and invisible every 30 frames
        if (frameCount % 60 < 30) {
            g.setColor(new Color(255, 183, 174));
            g.fillOval(this.xPos, this.yPos, this.size, this.size);
        }
    }

    /**
     * Update the state of the Super Pac-Gum.
     */
    @Override
    public void refresh() {
        frameCount++;
    }
}
