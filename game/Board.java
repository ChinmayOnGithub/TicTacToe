package game;

public class Board {
  private char[][] grid;
  private final int SIZE = 3;

  public Board() {
    grid = new char[SIZE][SIZE];
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        grid[row][col] = ' ';
      }
    }
  }

  public void printBoard() {
    for (int i = 0; i < SIZE; i++) {
      System.out.println(" " + grid[i][0] + " | " + grid[i][1] + " | " + grid[i][2]);
      if (i < SIZE - 1)
        System.out.println("---+---+---");
    }
  }

  public void printBoardWithNumbers() {
    int num = 1;
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        char c = grid[i][j] == ' ' ? Character.forDigit(num, 10) : grid[i][j];
        System.out.print(" " + c + " ");
        if (j < SIZE - 1)
          System.out.print("|");
        num++;
      }
      System.out.println();
      if (i < SIZE - 1)
        System.out.println("---+---+---");
    }
  }

  public boolean haveWon(char symbol) {
    // rows & cols
    for (int i = 0; i < grid.length; i++) {
      if (grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol)
        return true;
      if (grid[0][i] == symbol && grid[1][i] == symbol && grid[2][i] == symbol)
        return true;
    }
    // diagonals
    if (grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol)
      return true;
    if (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol)
      return true;

    return false;
  }

  public boolean isFull() {
    for (int i = 0; i < SIZE; i++)
      for (int j = 0; j < SIZE; j++)
        if (grid[i][j] == ' ')
          return false;
    return true;
  }

  public boolean placeMove(int cell, char symbol) {

    int row = (cell - 1) / 3;
    int col = (cell - 1) % 3;

    if (row < 0 || row >= SIZE || col < 0 || col >= SIZE)
      return false;
    if (grid[row][col] != ' ')
      return false;
    grid[row][col] = symbol;
    return true;
  }

}
