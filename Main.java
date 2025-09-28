import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    char[][] board = new char[3][3];

    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[0].length; col++) {
        board[row][col] = ' ';
      }
    }

    char player = 'X';
    boolean gameOver = false;
    try (Scanner sc = new Scanner(System.in)) {
      while (!gameOver) {
        printBoard(board);
        System.out.print("Player " + player + " enter: ");
        int row = sc.nextInt();
        int col = sc.nextInt();

        if (row > board.length || row < 0 || col > board[0].length || col < 0) {
          System.out.println("Invalid input. OutOfBounds!");
          return;
        }

        if (board[row][col] == ' ') {
          board[row][col] = player;
          gameOver = haveWon(board, player);
          if (gameOver) {
            System.out.println("Player " + player + " has won!");
            printBoard(board);
            return;
          } else {
            player = (player == 'X') ? 'O' : 'X';
          }
        } else {
          System.out.println("Can't place here... invalid input");
        }
      }
    }

  }

  private static boolean haveWon(char[][] board, char player) {
    // check rows
    for (int row = 0; row < board.length; row++) {
      if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
        return true;
      }
    }
    // check cols
    for (int col = 0; col < board[0].length; col++) {
      if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
        return true;
      }
    }

    if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
      return true;
    }

    if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
      return true;
    }
    return false;
  }

  private static void printBoard(char[][] board) {
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[0].length; col++) {
        System.out.print(board[row][col] + "| ");
      }
      System.out.println();
    }
  }
}