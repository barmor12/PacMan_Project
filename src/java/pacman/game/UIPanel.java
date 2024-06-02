package pacman.game;

import javax.imageio.ImageIO;
import javax.swing.*;

import pacman.entities.PacGumEntity;
import pacman.entities.SuperPacGumEntity;
import pacman.ghosts.Ghost;
import pacman.ghosts.ghostStates.FrightenedState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class representing the UI panel for the game.
 * This class extends JPanel and implements Observer.
 */
public class UIPanel extends JPanel implements Observer {
    public static int panelWidth;
    public static int panelHeight;

    private int gameScore = 0;
    private JLabel scoreLabel;
    private BufferedImage heartSprite;

    /**
     * Constructor to initialize the UI panel.
     *
     * @param panelWidth Width of the panel.
     * @param panelHeight Height of the panel.
     */
    public UIPanel(int panelWidth, int panelHeight) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setBackground(Color.black);
        scoreLabel = new JLabel("Score: " + gameScore);
        scoreLabel.setFont(scoreLabel.getFont().deriveFont(20.0F));
        scoreLabel.setForeground(Color.white);
        this.add(scoreLabel, BorderLayout.WEST);
        updateLives(3);
    }

    /**
     * Update the game score.
     *
     * @param scoreIncrement Amount to increment the score by.
     */
    public void updateScore(int scoreIncrement) {
        this.gameScore += scoreIncrement;
        this.scoreLabel.setText("Score: " + gameScore);
    }

    /**
     * Update the number of lives displayed on the UI.
     *
     * @param lives Number of lives to display.
     */
    public void updateLives(int lives) {
        this.removeAll();
        System.out.println("Lives: " + lives);
        for (int i = 0; i < lives; i++) {
            try {
                heartSprite = ImageIO.read(new File("src/resources/sprites/heart.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            JLabel heartLabel = new JLabel(new ImageIcon(heartSprite));
            this.add(heartLabel, BorderLayout.WEST);
        }
        this.add(scoreLabel, BorderLayout.WEST);
        this.revalidate();
        this.repaint();
    }

    /**
     * Get the current game score.
     *
     * @return The current game score.
     */
    public int getScore() {
        return gameScore;
    }

    @Override
    public void updatePacGumEaten(PacGumEntity pacGum) {
        updateScore(10);
    }

    @Override
    public void updateSuperPacGumEaten(SuperPacGumEntity superPacGum) {
        updateScore(100);
    }

    @Override
    public void updateGhostCollision(Ghost ghost) {
        if (ghost.getState() instanceof FrightenedState) {
            updateScore(500);
        }
    }
}
