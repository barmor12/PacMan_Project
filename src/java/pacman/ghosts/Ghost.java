package pacman.ghosts;

import javax.imageio.ImageIO;

import pacman.entities.MovingEntity;
import pacman.game.GameSession;
import pacman.ghosts.ghostStates.*;
import pacman.ghosts.ghostStrategies.GhostStrategy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

/**
 * Abstract class representing a ghost in the Pac-Man game.
 * This class extends MovingEntity and provides common behaviors and states for ghosts.
 */
public abstract class Ghost extends MovingEntity {
    protected GhostState state;

    protected final GhostState ChaseState;
    protected final GhostState ScatterState;
    protected final GhostState FrightenedState;
    protected final GhostState EatenState;
    protected final GhostState HouseState;

    protected int modeTimer = 0;
    protected int frightenedTimer = 0;
    protected boolean isChasing = false;

    protected static BufferedImage frightenedSprite1;
    protected static BufferedImage frightenedSprite2;
    protected static BufferedImage eatenSprite;

    protected GhostStrategy strategy;

    /**
     * Constructor to initialize a ghost at a specific position with a given sprite.
     *
     * @param xPos The x position of the ghost.
     * @param yPos The y position of the ghost.
     * @param spriteName The name of the sprite file.
     */
    public Ghost(int xPos, int yPos, String spriteName) {
        super(32, xPos, yPos, 2, spriteName, 2, 0.1f);

        ChaseState = new ChaseState(this);
        ScatterState = new ScatterState(this);
        FrightenedState = new FrightenedState(this);
        EatenState = new EatenState(this);
        HouseState = new HouseState(this);

        state = HouseState;

        try {
            frightenedSprite1 = ImageIO.read(new File("src/resources/sprites/ghost_frightened.png"));
            frightenedSprite2 = ImageIO.read(new File("src/resources/sprites/ghost_frightened_2.png"));
            eatenSprite = ImageIO.read(new File("src/resources/sprites/ghost_eaten.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a clone of this ghost.
     *
     * @return A clone of this ghost.
     */
    @Override
    public Ghost clone() {
        Ghost clonedGhost = (Ghost) super.clone();
        clonedGhost.strategy = this.strategy.clone(); // Assuming GhostStrategy has a properly implemented clone method
        clonedGhost.state = this.state.clone();
        return clonedGhost;
    }

    /**
     * Switch the ghost to chase mode.
     */
    public void switchChaseMode() {
        state = ChaseState;
    }

    /**
     * Switch the ghost to scatter mode.
     */
    public void switchScatterMode() {
        state = ScatterState;
    }

    /**
     * Switch the ghost to frightened mode.
     */
    public void switchFrightenedMode() {
        frightenedTimer = 0;
        state = FrightenedState;
    }

    /**
     * Switch the ghost to eaten mode.
     */
    public void switchEatenMode() {
        state = EatenState;
    }

    /**
     * Switch the ghost to house mode.
     */
    public void switchHouseMode() {
        state = HouseState;
    }

    /**
     * Switch the ghost to either chase mode or scatter mode based on the current state.
     */
    public void switchChaseModeOrScatterMode() {
        if (isChasing) {
            switchChaseMode();
        } else {
            switchScatterMode();
        }
    }

    /**
     * Get the strategy for the ghost.
     *
     * @return The current strategy for the ghost.
     */
    public GhostStrategy getStrategy() {
        return this.strategy;
    }

    /**
     * Set the strategy for the ghost.
     *
     * @param strategy The strategy to set.
     */
    public void setStrategy(GhostStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Get the current state of the ghost.
     *
     * @return The current state of the ghost.
     */
    public GhostState getState() {
        return state;
    }

    /**
     * Refresh the state of the ghost.
     */
    @Override
    public void refresh() {
        if (!GameSession.getIsFirstUserInput())
            return;

        if (state == FrightenedState) {
            frightenedTimer++;

            if (frightenedTimer >= (60 * 7)) {
                state.timerFrightenedModeOver();
            }
        }

        if (state == ChaseState || state == ScatterState) {
            modeTimer++;

            if ((isChasing && modeTimer >= (60 * 20)) || (!isChasing && modeTimer >= (60 * 5))) {
                state.timerModeOver();
                isChasing = !isChasing;
            }
        }

        if (xPos == 208 && yPos == 168) {
            state.outsideHouse();
        }

        if (xPos == 208 && yPos == 200) {
            state.insideHouse();
        }

        state.computeNextDirection();
        updatePosition();
    }

    /**
     * Render the ghost on the screen.
     *
     * @param g The graphics context to render on.
     */
    @Override
    public void render(Graphics2D g) {
        if (state == FrightenedState) {
            if (frightenedTimer <= (60 * 5) || frightenedTimer % 20 > 10) {
                g.drawImage(frightenedSprite1.getSubimage((int) spriteIndex * size, 0, size, size), this.xPos, this.yPos, null);
            } else {
                g.drawImage(frightenedSprite2.getSubimage((int) spriteIndex * size, 0, size, size), this.xPos, this.yPos, null);
            }
        } else if (state == EatenState) {
            g.drawImage(eatenSprite.getSubimage(direction * size, 0, size, size), this.xPos, this.yPos, null);
        } else {
            g.drawImage(sprite.getSubimage((int) spriteIndex * size + direction * size * spritesPerCycle, 0, size, size), this.xPos, this.yPos, null);
        }
    }
}
