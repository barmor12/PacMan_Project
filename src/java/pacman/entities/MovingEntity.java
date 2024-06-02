package pacman.entities;

import javax.imageio.ImageIO;

import pacman.game.GameplayPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.File;

/**
 * Abstract class representing a moving entity in the game.
 * This class extends the Entity class and adds movement capabilities.
 */
public abstract class MovingEntity extends Entity {
    // Speed of the entity
    protected int entitySpeed;

    // X and Y speed components of the entity
    protected int xSpeed = 0;
    protected int ySpeed = 0;

    // Sprite image of the entity
    protected BufferedImage sprite;

    // Index of the current sprite frame
    protected float spriteIndex = 0;

    // Number of sprites per cycle of animation
    protected int spritesPerCycle;

    // Current direction of the entity (0 - right, 1 - left, 2 - up, 3 - down)
    protected int direction = 0;

    // Speed of the sprite animation
    protected float spriteSpeed = 0.2f;

    /**
     * Constructor to initialize a moving entity.
     *
     * @param size Size of the entity.
     * @param xPos X position of the entity.
     * @param yPos Y position of the entity.
     * @param entitySpeed Speed of the entity.
     * @param spriteName Name of the sprite file.
     * @param spritesPerCycle Number of sprites per cycle.
     * @param spriteSpeed Speed of the sprite animation.
     */
    public MovingEntity(int size, int xPos, int yPos, int entitySpeed, String spriteName, int spritesPerCycle,
                        float spriteSpeed) {
        super(size, xPos, yPos);
        this.entitySpeed = entitySpeed;
        try {
            this.sprite = ImageIO.read(new File("src/resources/sprites/" + spriteName));
            this.spritesPerCycle = spritesPerCycle;
            this.spriteSpeed = spriteSpeed;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh() {
        updatePosition();
    }

    @Override
    public MovingEntity clone() {
        MovingEntity cloned = (MovingEntity) super.clone();
        cloned.sprite = copyBufferedImage(this.sprite);
        return cloned;
    }

    /**
     * Utility method to copy a BufferedImage.
     *
     * @param image The image to copy.
     * @return A copy of the given image.
     */
    private BufferedImage copyBufferedImage(BufferedImage image) {
        ColorModel cm = image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    /**
     * Update the position of the entity based on its speed.
     */
    public void updatePosition() {
        if (!(xSpeed == 0 && ySpeed == 0)) {
            xPos += xSpeed;
            yPos += ySpeed;

            // Update direction based on speed
            if (xSpeed > 0) {
                direction = 0;
            } else if (xSpeed < 0) {
                direction = 1;
            } else if (ySpeed < 0) {
                direction = 2;
            } else if (ySpeed > 0) {
                direction = 3;
            }

            // Update sprite index for animation
            spriteIndex += spriteSpeed;
            if (spriteIndex >= spritesPerCycle) {
                spriteIndex = 0;
            }
        }

        // Wrap around screen edges
        if (xPos > GameplayPanel.gameWidth) {
            xPos = 0 - size + entitySpeed;
        }

        if (xPos < 0 - size + entitySpeed) {
            xPos = GameplayPanel.gameWidth;
        }

        if (yPos > GameplayPanel.gameHeight) {
            yPos = 0 - size + entitySpeed;
        }

        if (yPos < 0 - size + entitySpeed) {
            yPos = GameplayPanel.gameHeight;
        }
    }

    @Override
    public void render(Graphics2D g) {
        // Draw the current sprite frame based on direction and sprite index
        g.drawImage(sprite.getSubimage((int) spriteIndex * size + direction * size * spritesPerCycle, 0, size,
                size), this.xPos, this.yPos, null);
    }

    /**
     * Check if the entity is aligned to the grid.
     *
     * @return True if the entity is on the grid, false otherwise.
     */
    public boolean isOnGrid() {
        return xPos % 8 == 0 && yPos % 8 == 0;
    }

    /**
     * Check if the entity is within the gameplay window.
     *
     * @return True if the entity is within the gameplay window, false otherwise.
     */
    public boolean isInGameplayWindow() {
        return xPos > 0 && xPos < GameplayPanel.gameWidth && yPos > 0 && yPos < GameplayPanel.gameHeight;
    }

    public Rectangle getHitbox() {
        return new Rectangle(xPos, yPos, size, size);
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public void setSprite(String spriteName) {
        try {
            this.sprite = ImageIO.read(new File("src/resources/sprites/" + spriteName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float getSpriteIndex() {
        return spriteIndex;
    }

    public void setSpriteIndex(float spriteIndex) {
        this.spriteIndex = spriteIndex;
    }

    public int getSpritesPerCycle() {
        return spritesPerCycle;
    }

    public void setSpritesPerCycle(int spritesPerCycle) {
        this.spritesPerCycle = spritesPerCycle;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getEntitySpeed() {
        return entitySpeed;
    }
}
