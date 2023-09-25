# java2048
Java-based remake of the popular game 2048, showcasing skills in Java programming, algorithm design, and graphical user interface (GUI) development.
  Game.java was used to contain the main method that initialized the runnable game class and run it. Game2048.java
  housed all the core logic and state of the game itself. This included the 2D-Array that stored where all the
  values were. It also had my move algorithms that shifted values to different directions, and accurately merged
  the correct values together. It also kept track of score, board and score history to allow for undo functionality,
  and File I/O to read and write the game state to a text file. GameBoard.java contained my code that handled
  keyboard input and adjusted text overlays to communicate the game status to the user. It also had my paint components
  that ensured each tile was painted the correct color and with its respective value. Lastly, Run2048.java was used
  to write the top-level widgets and GUI, including the instructions panel, undo button, and reset button. NOTE: Run in
  Java 19.
