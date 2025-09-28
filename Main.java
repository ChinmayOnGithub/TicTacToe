import java.util.Scanner;

class Board {
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

  public boolean placeMove(int row, int col, char symbol) {
    if (row < 0 || row >= SIZE || col < 0 || col >= SIZE)
      return false;
    if (grid[row][col] != ' ')
      return false;
    grid[row][col] = symbol;
    return true;
  }

}

class Player {
  private String name;
  private char symbol;

  public Player(String name, char symbol) {
    this.name = name;
    this.symbol = symbol;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public char getSymbol() {
    return symbol;
  }

  public void setSymbol(char symbol) {
    this.symbol = symbol;
  }

}

public class Main {
  public static void main(String[] args) {
    Player p1 = new Player("Player_1", 'X');
    Player p2 = new Player("Player_2", 'O');

    Player curr = p1;

    // create board
    Board board = new Board();

    try (Scanner sc = new Scanner(System.in)) {
      while (true) {
        board.printBoard();
        System.out.print(curr.getName() + " (" + curr.getSymbol() + "), " + "enter: ");

        int row = sc.nextInt();
        int col = sc.nextInt();

        if (!board.placeMove(row, col, curr.getSymbol())) {
          System.out.println("Invalid move. Try again!");
          continue;
        }

        // check winner
        if (board.haveWon(curr.getSymbol())) {
          board.printBoard();
          System.out.println(curr.getName() + " wins!");
          break;
        }

        // check draw
        if (board.isFull()) {
          board.printBoard();
          System.out.println("It's a draw!");
          break;
        }

        // switch player
        curr = (curr == p1) ? p2 : p1;
      }
    }
  }
}