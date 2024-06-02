package pacman.helpers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import pacman.game.GameplayPanel;
import java.util.ArrayList;

/**
 * Class to handle keyboard input for the game.
 * Implements KeyListener to respond to key events.
 */
public class KeyHandler implements KeyListener {

    public static List<Key> keys = new ArrayList<>();

    /**
     * Inner class representing a key and its pressed state.
     */
    public class Key {
        public boolean isPressed;

        /**
         * Constructor to initialize a key and add it to the list of keys.
         */
        public Key() {
            keys.add(this);
        }

        /**
         * Toggle the pressed state of the key.
         *
         * @param pressed The new pressed state.
         */
        public void toggle(boolean pressed) {
            if (pressed != isPressed) {
                isPressed = pressed;
            }
        }
    }

    public Key upKey = new Key();
    public Key downKey = new Key();
    public Key leftKey = new Key();
    public Key rightKey = new Key();

    /**
     * Constructor to initialize the KeyHandler with the gameplay panel.
     *
     * @param game The gameplay panel.
     */
    public KeyHandler(GameplayPanel game) {
        game.addKeyListener(this);
    }

    /**
     * Toggle the state of the key based on the KeyEvent.
     *
     * @param e The KeyEvent.
     * @param pressed The new pressed state.
     */
    public void toggle(KeyEvent e, boolean pressed) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftKey.toggle(pressed);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightKey.toggle(pressed);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upKey.toggle(pressed);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downKey.toggle(pressed);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e, false);
    }
}
