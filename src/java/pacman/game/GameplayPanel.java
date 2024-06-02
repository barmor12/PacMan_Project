package pacman.game;

import javax.imageio.ImageIO;
import javax.swing.*;

import pacman.helpers.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

/**
 * Class representing the gameplay panel where the game is rendered.
 * This class extends JPanel and implements Runnable.
 */
public class GameplayPanel extends JPanel implements Runnable {
    public static int gameWidth;
    public static int gameHeight;
    private Thread gameThread;
    private boolean gameRunning = false;

    private BufferedImage gamesprites;
    private Graphics2D gameGraphics;
    private Image bgImage;

    private KeyHandler keyInputHandler;

    private GameSession gameSession;

    /**
     * Constructor to initialize the gameplay panel.
     *
     * @param width Width of the game panel.
     * @param height Height of the game panel.
     * @throws IOException If there is an error loading the background image.
     */
    public GameplayPanel(int width, int height) throws IOException {
        this.gameWidth = width;
        this.gameHeight = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        bgImage = ImageIO.read(new File("src/resources/sprites/background.png"));
    }

    /**
     * This method is called by the Swing framework when the component is added to a container.
     * It starts the game thread.
     */
    @Override
    public void addNotify() {
        super.addNotify();

        if (gameThread == null) {
            gameThread = new Thread(this, "GameThread");
            gameThread.start();
        }
    }

    /**
     * Initialize the game, setting up the game session, graphics, and input handler.
     */
    public void initializeGame() {
        gameRunning = true;
        gamesprites = new BufferedImage(gameWidth, gameHeight, BufferedImage.TYPE_INT_ARGB);
        gameGraphics = (Graphics2D) gamesprites.getGraphics();

        keyInputHandler = new KeyHandler(this);

        gameSession = new GameSession();
    }

    /**
     * Update the game state by refreshing entities.
     */
    public void updateGame() {
        gameSession.refreshEntities();
    }

    /**
     * Handle user input.
     *
     * @param key Key handler for capturing user inputs.
     */
    public void handleInput(KeyHandler key) {
        gameSession.handleInput(key);
    }

    /**
     * Render the game by drawing the background and game entities.
     */
    public void renderGame() {
        if (gameGraphics != null) {
            gameGraphics.drawImage(bgImage, 0, 0, gameWidth, gameHeight, null);
            gameSession.drawEntities(gameGraphics);
        }
    }

    /**
     * Draw the rendered game image onto the panel.
     */
    public void drawGame() {
        Graphics g = this.getGraphics();
        g.drawImage(gamesprites, 0, 0, gameWidth, gameHeight, null);
        g.dispose();
    }

    /**
     * The main game loop which handles game updates, rendering, and frame rate control.
     */
    @Override
    public void run() {
        initializeGame();

        final double GAME_HERTZ = 60.0;
        final double TIME_BEFORE_UPDATE = 1000000000 / GAME_HERTZ;

        final int MAX_UPDATES_BEFORE_RENDER = 5;

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60.0;
        final double TOTAL_TIME_BEFORE_RENDER = 1000000000 / TARGET_FPS;

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        int oldFrameCount = 0;

        while (gameRunning) {
            double now = System.nanoTime();
            int updateCount = 0;
            while ((now - lastUpdateTime) > TIME_BEFORE_UPDATE && (updateCount < MAX_UPDATES_BEFORE_RENDER)) {
                handleInput(keyInputHandler);
                updateGame();
                lastUpdateTime += TIME_BEFORE_UPDATE;
                updateCount++;
            }

            if (now - lastUpdateTime > TIME_BEFORE_UPDATE) {
                lastUpdateTime = now - TIME_BEFORE_UPDATE;
            }

            renderGame();
            drawGame();
            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime) {
                if (frameCount != oldFrameCount) {
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while ((now - lastRenderTime < TOTAL_TIME_BEFORE_RENDER) && (now - lastUpdateTime < TIME_BEFORE_UPDATE)) {
                Thread.yield();

                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    System.err.println("ERROR yielding thread");
                }

                now = System.nanoTime();
            }
        }
    }
}
