package pacman.entities;

/**
 * Class representing a wall entity in the game.
 * This class extends StaticEntity.
 */
public class WallEntity extends StaticEntity {

    /**
     * Constructor to initialize a wall entity.
     *
     * @param xPos X position of the wall.
     * @param yPos Y position of the wall.
     */
    public WallEntity(int xPos, int yPos) {
        super(8, xPos, yPos);
    }
}
