package org.cis1200.game2048;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * You can use this file (and others) to test your
 * implementation.
 */

public class GameTest {

    @Test
    public void testTwoMergingCellsInOneMove() {
        Game2048 game = new Game2048();
        int[][] board = { { 2, 0, 0, 0 }, { 2, 0, 0, 0 }, { 4, 0, 0, 0 }, { 4, 0, 0, 0 } };
        game.setBoard(board);
        game.up();

        assertEquals(4, game.getCell(0, 0));
        assertEquals(8, game.getCell(0, 1));
        assertEquals(0, game.getCell(0, 2));
        assertEquals(0, game.getCell(0, 3));

    }

    @Test
    public void cellShiftsAcrossBoard() {
        Game2048 game = new Game2048();
        int[][] board = { { 2, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
        game.setBoard(board);
        game.down();

        assertEquals(game.getCell(0, 0), 0);
        assertEquals(game.getCell(0, 3), 2);

    }

    @Test
    public void multipleRowsMerging() {
        Game2048 game = new Game2048();
        int[][] board = { { 2, 0, 2, 0 }, { 0, 0, 0, 0 }, { 8, 0, 0, 8 }, { 0, 0, 0, 0 } };
        game.setBoard(board);
        game.right();

        assertEquals(4, game.getCell(3, 0));
        assertEquals(16, game.getCell(3, 2));
        assertEquals(0, game.getCell(0, 0));
        assertEquals(0, game.getCell(2, 0));

    }

    @Test
    public void resetBoard() {
        Game2048 game = new Game2048();
        int[][] board = { { 2, 0, 2, 0 }, { 0, 0, 0, 0 }, { 8, 0, 0, 8 }, { 0, 0, 0, 0 } };
        game.setBoard(board);
        game.reset();

        assertEquals(0, game.getCell(3, 0));
        assertEquals(0, game.getCell(3, 2));
        assertEquals(0, game.getCell(0, 0));
        assertEquals(0, game.getCell(2, 0));

    }

    @Test
    public void undoTurn() {
        Game2048 game = new Game2048();
        int[][] board = { { 2, 0, 2, 0 }, { 0, 0, 0, 0 }, { 8, 0, 0, 8 }, { 0, 0, 0, 0 } };
        game.setBoard(board);
        game.left();
        game.undo();

        assertEquals(2, game.getCell(2, 0));
        assertEquals(2, game.getCell(0, 0));
        assertEquals(8, game.getCell(0, 2));
        assertEquals(8, game.getCell(3, 2));

    }

}
