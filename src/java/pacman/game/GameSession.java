package pacman.game;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import pacman.GameLauncher;
import pacman.entities.*;
import pacman.ghosts.Ghost;
import pacman.ghosts.RedGhost;
import pacman.ghosts.ghostFactory.*;
import pacman.ghosts.ghostStates.EatenState;
import pacman.ghosts.ghostStates.FrightenedState;
import pacman.helpers.EntityCollisionDetector;
import pacman.helpers.KeyHandler;
import pacman.helpers.MapReader;

/**
 * Class representing a game session of Pac-Man.
 * This class manages the game entities and game state.
 */
public class GameSession implements Observer {
    private List<Entity> gameEntities;
    private List<Ghost> ghostEntities;
    private static List<WallEntity> wallEntities;

    private static PacmanEntity pacmanEntity;
    private static RedGhost redGhostEntity;

    private static boolean isFirstUserInput;
    private static boolean isGameOver = false;

    /**
     * Constructor to initialize the game session.
     */
    public GameSession() {
        setupGame();
    }

    /**
     * Setup the game by loading the map and initializing entities.
     */
    private void setupGame() {
        gameEntities = new ArrayList<>();
        ghostEntities = new ArrayList<>();
        wallEntities = new ArrayList<>();
        isFirstUserInput = false;
        List<List<String>> mapData = new MapReader().parseMap(new File("src/resources/level/level.csv").toURI());

        int mapWidth = mapData.get(0).size();
        int mapHeight = mapData.size();
        int cellDimension = 8;

        EntityCollisionDetector collisionDetector = new EntityCollisionDetector(this);

        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                String cellData = mapData.get(y).get(x);
                addEntity(cellData, x * cellDimension, y * cellDimension, collisionDetector);
            }
        }

        gameEntities.add(pacmanEntity);
        gameEntities.addAll(ghostEntities);

        for (Entity entity : gameEntities) {
            if (entity instanceof WallEntity) {
                wallEntities.add((WallEntity) entity);
            }
        }
    }

    /**
     * Add an entity to the game based on the cell data.
     *
     * @param cellData The cell data indicating the type of entity.
     * @param x X position of the entity.
     * @param y Y position of the entity.
     * @param collisionDetector The collision detector for the entity.
     */
    private void addEntity(String cellData, int x, int y, EntityCollisionDetector collisionDetector) {
        switch (cellData) {
            case "x":
                gameEntities.add(new WallEntity(x, y));
                break;
            case "P":
                pacmanEntity = PacmanEntity.getInstance(x, y);
                pacmanEntity.setCollisionDetector(collisionDetector);
                pacmanEntity.registerObserver(GameLauncher.getUIPanel());
                pacmanEntity.registerObserver(this);
                break;
            case "b":
            case "p":
            case "i":
            case "c":
                Ghost ghost = generateGhost(cellData, x, y);
                ghostEntities.add(ghost);
                if (cellData.equals("b")) {
                    redGhostEntity = (RedGhost) ghost;
                }
                break;
            case ".":
                gameEntities.add(new PacGumEntity(x, y));
                break;
            case "o":
                gameEntities.add(new SuperPacGumEntity(x, y));
                break;
            case "-":
                gameEntities.add(new GhostHouseEntity(x, y));
                break;
        }
    }

    /**
     * Generate a ghost based on the cell data.
     *
     * @param cellData The cell data indicating the type of ghost.
     * @param x X position of the ghost.
     * @param y Y position of the ghost.
     * @return The generated ghost.
     */
    private Ghost generateGhost(String cellData, int x, int y) {
        AbstractGhostFactory ghostFactory = null;
        switch (cellData) {
            case "b":
                ghostFactory = new RedGhostFactory();
                break;
            case "p":
                ghostFactory = new PinkGhostFactory();
                break;
            case "i":
                ghostFactory = new BlueGhostFactory();
                break;
            case "c":
                ghostFactory = new YellowGhostFactory();
                break;
        }
        return ghostFactory.createGhost(x, y);
    }

    /**
     * Get the list of wall entities.
     *
     * @return The list of wall entities.
     */
    public static List<WallEntity> getWallEntities() {
        return wallEntities;
    }

    /**
     * Get the list of game entities.
     *
     * @return The list of game entities.
     */
    public List<Entity> getGameEntities() {
        return gameEntities;
    }

    /**
     * Refresh the state of all entities in the game.
     */
    public void refreshEntities() {
        boolean allPacGumsCollected = true;
        for (Entity entity : gameEntities) {
            if (!entity.isDestroyed())
                entity.refresh();
            if (entity instanceof PacGumEntity && !entity.isDestroyed()) {
                allPacGumsCollected = false;
            }
        }
        if (allPacGumsCollected) {
            System.out.println("Congratulations! You won the game!");
            isGameOver = true;
            showGameOver(true);
            pacmanEntity.reset();
        }
        if (isGameOver) {
            System.out.println("GameSession Over");
            setupGame();
            GameLauncher.getUIPanel().updateLives(3);
            GameLauncher.getUIPanel().updateScore(-GameLauncher.getUIPanel().getScore());
            isGameOver = false;
        }
    }

    /**
     * Handle user input.
     *
     * @param keyHandler Key handler for capturing user inputs.
     */
    public void handleInput(KeyHandler keyHandler) {
        pacmanEntity.handleInput(keyHandler);
    }

    /**
     * Draw all game entities onto the provided graphics context.
     *
     * @param graphics The graphics context to draw onto.
     */
    public void drawEntities(Graphics2D graphics) {
        for (Entity entity : gameEntities) {
            if (!entity.isDestroyed())
                entity.render(graphics);
        }
    }

    /**
     * Get the instance of PacmanEntity.
     *
     * @return The instance of PacmanEntity.
     */
    public static PacmanEntity getPacmanEntity() {
        return pacmanEntity;
    }

    /**
     * Get the instance of RedGhost.
     *
     * @return The instance of RedGhost.
     */
    public static RedGhost getRedGhostEntity() {
        return redGhostEntity;
    }

    @Override
    public void updatePacGumEaten(PacGumEntity pacGumEntity) {
        pacGumEntity.destroy();
    }

    @Override
    public void updateSuperPacGumEaten(SuperPacGumEntity superPacGumEntity) {
        superPacGumEntity.destroy();
        for (Ghost ghost : ghostEntities) {
            ghost.getState().superPacGumEaten();
        }
    }

    @Override
    public void updateGhostCollision(Ghost ghost) {
        if (ghost.getState() instanceof FrightenedState) {
            ghost.getState().eaten();
        } else if (!(ghost.getState() instanceof EatenState)) {
            System.out.println("Pacman has been caught by a ghost , lives left: " + pacmanEntity.getLives());
            GameLauncher.getUIPanel().updateLives(pacmanEntity.getLives());
            if (pacmanEntity.getLives() == 0) {
                System.out.println("GameSession Over");
                showGameOver(false);
            }
        }
    }

    /**
     * Show the game over dialog and handle the user's choice to restart or quit.
     *
     * @param won True if the player won, false if they lost.
     */
    private void showGameOver(boolean won) {
        pacmanEntity.resetLives();
        String result = won ? "Congratulations! You won the game!" : "Game Over. Your score is " + GameLauncher.getUIPanel().getScore();
        int dialogResult = JOptionPane.showOptionDialog(null,
                result + ". Do you want to restart the game?", "GameSession Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{"Restart", "Quit"}, null);
        if (dialogResult == JOptionPane.YES_OPTION) {
            isGameOver = true;
        } else {
            System.exit(0);
        }
    }

    /**
     * Set the flag indicating if this is the first user input.
     *
     * @param isFirstUserInputt True if this is the first user input, false otherwise.
     */
    public static void setFirstUserInput(boolean isFirstUserInputt) {
        isFirstUserInput = isFirstUserInputt;
    }

    /**
     * Get the flag indicating if this is the first user input.
     *
     * @return True if this is the first user input, false otherwise.
     */
    public static boolean getIsFirstUserInput() {
        return isFirstUserInput;
    }
}
