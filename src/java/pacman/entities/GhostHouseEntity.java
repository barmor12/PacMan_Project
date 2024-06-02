package pacman.entities;

/**
 * Class representing the ghost house entity.
 * Inherits from WallEntity.
 */
public class GhostHouseEntity extends WallEntity {

    /**
     * Constructor to initialize the ghost house entity.
     *
     * @param xPos X position of the ghost house.
     * @param yPos Y position of the ghost house.
     */
    public GhostHouseEntity(int xPos, int yPos) {
        super(xPos, yPos);
    }
}
