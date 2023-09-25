package org.cis1200.game2048;

/**
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import java.io.*;
import java.util.LinkedList;

/**
 * This class is a model for TicTacToe.
 * 
 * This game adheres to a Model-View-Controller design framework.
 * This framework is very effective for turn-based games. We
 * STRONGLY recommend you review these lecture slides, starting at
 * slide 8, for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec36.pdf
 * 
 * This model is completely independent of the view and controller.
 * This is in keeping with the concept of modularity! We can play
 * the whole game from start to finish without ever drawing anything
 * on a screen or instantiating a Java Swing object.
 * 
 * Run this file to see the main method play a game of TicTacToe,
 * visualized with Strings printed to the console.
 */
public class Game2048 {

    private int[][] board;
    private int numTurns;
    private int score;

    private LinkedList<int[][]> boardHistory;
    private LinkedList<Integer> scoreHistory;

    /**
     * Constructor sets up game state.
     */
    public Game2048() {
        set();

    }

    public void addTwo() {

        if (!fullBoard()) {
            int x1 = (int) (Math.random() * 4);
            int y1 = (int) (Math.random() * 4);
            boolean notEmpty = true;

            while (notEmpty) {
                x1 = (int) (Math.random() * 4);
                y1 = (int) (Math.random() * 4);

                if (board[y1][x1] == 0) {
                    notEmpty = false;
                }
            }
            int val = (int) (Math.random() * 2) + 1;
            board[y1][x1] = 2 * val;
        }
    }

    public boolean fullBoard() {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    count++;
                }
            }
        }

        return (count == 16);
    }

    public void left() {
        move(2);
        addTwo();
        writeBoard();
    }

    public void up() {
        move(1);
        addTwo();
        writeBoard();
    }

    public void right() {
        move(3);
        addTwo();
        writeBoard();
    }

    public void down() {
        move(0);
        addTwo();
        writeBoard();
    }

    public void move(int dir) {
        int[][] newBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int c = 0; c < 4; c++) {
                newBoard[i][c] = board[i][c];
            }
        }
        boardHistory.addLast(newBoard);
        scoreHistory.addLast(getScore());

        // DOWN
        if (dir == 0) {
            for (int x = 0; x < 4; x++) {
                shift(0, x, "down");
                merge(0, x, "down");
                shift(0, x, "down");
            }

        }

        // UP
        if (dir == 1) {
            for (int x = 0; x < 4; x++) {
                shift(0, x, "up");
                merge(0, x, "up");
                shift(0, x, "up");
            }
        }

        // LEFT
        if (dir == 2) {
            for (int y = 0; y < 4; y++) {
                shift(y, 0, "left");
                merge(y, 0, "left");
                shift(y, 0, "left");
            }
        }

        // RIGHT
        if (dir == 3) {
            for (int y = 0; y < 4; y++) {
                shift(y, 0, "right");
                merge(y, 0, "right");
                shift(y, 0, "right");
            }
        }

    }

    public void shift(int y, int x, String dir) {

        if (dir.equals("up")) {
            for (int i = 1; i < 4; i++) {

                int range = i;
                int depth = 1;
                while (range > 0) {
                    if (board[i - depth][x] == 0) {
                        board[i - depth][x] = board[i - depth + 1][x];
                        board[i - depth + 1][x] = 0;
                    }
                    depth++;
                    range--;
                }

            }
        }

        if (dir.equals("down")) {
            for (int i = 2; i >= 0; i--) {

                int range = 3 - i;
                int depth = 1;
                while (range > 0) {
                    if (board[i + depth][x] == 0) {
                        board[i + depth][x] = board[i + depth - 1][x];
                        board[i + depth - 1][x] = 0;
                    }
                    depth++;
                    range--;
                }

            }
        }

        if (dir.equals("left")) {
            for (int i = 1; i < 4; i++) {

                int range = i;
                int depth = 1;
                while (range > 0) {
                    if (board[y][i - depth] == 0) {
                        board[y][i - depth] = board[y][i - depth + 1];
                        board[y][i - depth + 1] = 0;
                    }
                    depth++;
                    range--;
                }

            }
        }

        if (dir.equals("right")) {
            for (int i = 2; i >= 0; i--) {

                int range = 3 - i;
                int depth = 1;
                while (range > 0) {
                    if (board[y][i + depth] == 0) {
                        board[y][i + depth] = board[y][i + depth - 1];
                        board[y][i + depth - 1] = 0;
                    }
                    depth++;
                    range--;
                }

            }
        }

    }

    public void merge(int y, int x, String dir) {

        if (dir.equals("up")) {
            for (int r = 0; r < 3; r++) {
                if (board[r][x] == board[r + 1][x]) {
                    board[r][x] = 2 * board[r][x];
                    score += board[r][x];
                    board[r + 1][x] = 0;
                }
            }
        }

        if (dir.equals("down")) {
            for (int r = 3; r > 0; r--) {
                if (board[r][x] == board[r - 1][x]) {
                    board[r][x] = 2 * board[r][x];
                    score += board[r][x];
                    board[r - 1][x] = 0;
                }
            }
        }

        if (dir.equals("left")) {
            for (int r = 0; r < 3; r++) {
                if (board[y][r] == board[y][r + 1]) {
                    board[y][r] = 2 * board[y][r];
                    score += board[y][r];
                    board[y][r + 1] = 0;
                }
            }
        }

        if (dir.equals("right")) {
            for (int r = 3; r > 0; r--) {
                if (board[y][r] == board[y][r - 1]) {
                    board[y][r] = 2 * board[y][r];
                    score += board[y][r];
                    board[y][r - 1] = 0;
                }
            }
        }

    }

    public void writeBoard() {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                builder.append(board[i][j] + "");
                if (j < board.length - 1) {
                    builder.append(",");
                }
            }
            builder.append("\n");
        }
        builder.append(getScore() + "");
        BufferedWriter writer = null;
        try {
            try {
                File myObj = new File("2048.txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            writer = new BufferedWriter(new FileWriter("2048file.txt"));
            writer.write(builder.toString());
            writer.close();
        } catch (IOException e) {

        }

    }

    public int[][] readBoard() {
        int[][] board = new int[4][4];
        FileReader r = null;
        try {
            r = new FileReader("2048file.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedReader reader = new BufferedReader(r);
        String line = "";
        int row = 0;
        for (int i = 0; i < 5; i++) {
            try {
                line = reader.readLine();
            } catch (IOException e) {
            }

            if (i == 4) {
                int score = Integer.parseInt(line);
                setScore(score);
            } else {

                String[] vals = line.split(",");
                int col = 0;
                for (String c : vals) {
                    board[row][col] = Integer.parseInt(c);
                    col++;
                }
                row++;
            }
        }

        try {
            reader.close();
        } catch (IOException e) {

        }

        return board;

    }

    /**
     * checkWinner checks whether the game has reached a win condition.
     * checkWinner only looks for horizontal wins.
     *
     * @return 0 if nobody has won yet, 1 if player 1 has won, and 2 if player 2
     *         has won, 3 if the game hits stalemate
     */
    public boolean gameOver() {
        if (!fullBoard()) {
            return false;
        } else {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (withinGrid(c, r - 1) && board[r - 1][c] == board[r][c]) {
                        return false;
                    }
                    if (withinGrid(c, r + 1) && board[r + 1][c] == board[r][c]) {
                        return false;
                    }
                    if (withinGrid(c - 1, r) && board[r][c - 1] == board[r][c]) {
                        return false;
                    }
                    if (withinGrid(c + 1, r) && board[r][c + 1] == board[r][c]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    private boolean withinGrid(int x, int y) {

        return (x > 0 && x < 3 && y > 0 && y < 3);
    }

    /**
     * printGameState prints the current game state
     * for debugging.
     */
    public void printGameState() {
        System.out.println("\n\nTurn " + numTurns + ":\n");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                if (j < 3) {
                    System.out.print(" | ");
                }
            }
            if (i < 3) {
                System.out.println("\n--------------");
            }
        }
    }

    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset() {
        board = new int[4][4];
        numTurns = 0;
        score = 0;
        addTwo();
        addTwo();
        writeBoard();
    }

    public void set() {
        try {
            board = readBoard();
        } catch (RuntimeException e) {
            reset();
        }

        boardHistory = new LinkedList<>();
        scoreHistory = new LinkedList<>();
    }

    public void undo() {
        int[][] newBoard = boardHistory.pollLast();

        if (newBoard != null) {
            setBoard(newBoard.clone());
        }
        if (scoreHistory.size() != 0) {
            int oldScore = scoreHistory.pollLast();
            setScore(oldScore);
        }

        writeBoard();

    }

    public void setBoard(int[][] newB) {
        board = newB;
    }

    /**
     * getCell is a getter for the contents of the cell specified by the method
     * arguments.
     *
     * @param c column to retrieve
     * @param r row to retrieve
     * @return an integer denoting the contents of the corresponding cell on the
     *         game board. 0 = empty, 1 = Player 1, 2 = Player 2
     */
    public int getCell(int c, int r) {
        return board[r][c];
    }

    public int getScore() {
        return score;
    }

    public void setScore(int x) {
        score = x;
    }

    /**
     * This main method illustrates how the model is completely independent of
     * the view and controller. We can play the game from start to finish
     * without ever creating a Java Swing object.
     *
     * This is modularity in action, and modularity is the bedrock of the
     * Model-View-Controller design framework.
     *
     * Run this file to see the output of this method in your console.
     */
    public static void main(String[] args) {

    }
}
