package pacman;

import javax.swing.*;

import pacman.game.GameplayPanel;
import pacman.game.UIPanel;

import java.io.IOException;

/**
 * Class to launch the Pacman game.
 */
public class GameLauncher {
    private static UIPanel panelUI;

    /**
     * Main method to launch the game.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        JFrame gameFrame = new JFrame();
        gameFrame.setTitle("Pacman");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelGameWindow = new JPanel();

        try {
            GameplayPanel gameplayPanel = new GameplayPanel(448, 496);
            panelGameWindow.add(gameplayPanel);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        panelUI = new UIPanel(256, 496);
        panelGameWindow.add(panelUI);

        gameFrame.setContentPane(panelGameWindow);
        gameFrame.setResizable(false);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

    /**
     * Get the UI panel.
     *
     * @return The UI panel.
     */
    public static UIPanel getUIPanel() {
        return panelUI;
    }

    /**
     * Start a new game.
     */
    public static void newGame() {
        panelUI = new UIPanel(256, 496);
        GameplayPanel gameplayPanel = (GameplayPanel) panelUI.getParent().getComponent(0);
        gameplayPanel.initializeGame();
    }
}
