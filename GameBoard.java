package org.cis1200.game2048;

/*
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private Game2048 mod; // model for the game
    private JLabel status; // current status text

    // Game constants
    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 400;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        mod = new Game2048(); // initializes model for the game
        status = statusInit; // initializes the status JLabel

        setFocusable(true);
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                int p = e.getKeyCode();

                if (p == KeyEvent.VK_RIGHT) {
                    mod.right();
                }
                if (p == KeyEvent.VK_UP) {
                    mod.up();
                }
                if (p == KeyEvent.VK_LEFT) {
                    mod.left();
                }
                if (p == KeyEvent.VK_DOWN) {
                    mod.down();
                }

                updateStatus();
                repaint();

            }
        }

        );

    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        mod.reset();
        status.setText("Begin");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    public void set() {
        mod.set();
        status.setText("Continuing saved game. Score: " + mod.getScore());
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    public void undo() {
        mod.undo();
        status.setText("Score: " + mod.getScore());
        repaint();

        requestFocusInWindow();
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        status.setText("Score: " + mod.getScore());

        if (mod.gameOver()) {
            status.setText("GAME OVER! Final Score: " + mod.getScore());
        }

    }

    /**
     * Draws the game board.
     * 
     * There are many ways to draw a game board. This approach
     * will not be sufficient for most games, because it is not
     * modular. All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper
     * methods. Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draws board grid
        g.drawLine(100, 0, 100, 400);
        g.drawLine(200, 0, 200, 400);
        g.drawLine(300, 0, 300, 400);
        g.drawLine(0, 100, 400, 100);
        g.drawLine(0, 200, 400, 200);
        g.drawLine(0, 300, 400, 300);

        // Draws X's and O's
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                int value = mod.getCell(j, i);
                if (value == 0) {
                    g.setColor(new Color(204, 192, 179));
                    g.fillRect(j * 100 + 5, i * 100 + 5, 90, 90);
                }
                if (value == 2) {
                    g.setColor(new Color(238, 228, 218));
                    g.fillRect(j * 100 + 5, i * 100 + 5, 90, 90);
                }
                if (value == 4) {
                    g.setColor(new Color(237, 224, 200));
                    g.fillRect(j * 100 + 5, i * 100 + 5, 90, 90);
                }
                if (value == 8) {
                    g.setColor(new Color(242, 177, 121));
                    g.fillRect(j * 100 + 5, i * 100 + 5, 90, 90);
                }
                if (value == 16) {
                    g.setColor(new Color(245, 149, 99));
                    g.fillRect(j * 100 + 5, i * 100 + 5, 90, 90);
                }
                if (value == 32) {
                    g.setColor(new Color(246, 124, 95));
                    g.fillRect(j * 100 + 5, i * 100 + 5, 90, 90);
                }
                if (value == 64) {
                    g.setColor(new Color(246, 94, 59));
                    g.fillRect(j * 100 + 5, i * 100 + 5, 90, 90);
                }
                if (value == 128) {
                    g.setColor(new Color(237, 207, 114));
                    g.fillRect(j * 100 + 5, i * 100 + 5, 90, 90);
                }
                if (value == 256) {
                    g.setColor(new Color(237, 204, 97));
                    g.fillRect(j * 100 + 5, i * 100 + 5, 90, 90);
                }
                if (value == 512) {
                    g.setColor(new Color(237, 200, 80));
                    g.fillRect(j * 100 + 5, i * 100 + 5, 90, 90);
                }
                if (value == 1024) {
                    g.setColor(new Color(237, 197, 63));
                    g.fillRect(j * 100 + 5, i * 100 + 5, 90, 90);
                }
                if (value == 2048) {
                    g.setColor(new Color(237, 194, 46));
                    g.fillRect(j * 100 + 5, i * 100 + 5, 90, 90);
                }

                g.setColor(Color.BLACK);
                if (value != 0) {
                    g.drawString("" + value, 50 + 100 * j, 50 + 100 * i);
                }

            }
        }
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
