
import java.util.Scanner;

import game.Board;
import game.Player;

public class Main {
  public static void main(String[] args) {

    Player p1 = new Player("Player_1", 'X');
    Player p2 = new Player("Player_2", 'O');

    Player curr = p1;

    // create board
    Board board = new Board();
    System.out.println("Welcome to Tic-Tac-Toe!");
    System.out.println("Players take turns to enter a number from 1 to 9.");
    System.out.println("Cells are numbered as follows:");
    board.printBoardWithNumbers();

    try (Scanner sc = new Scanner(System.in)) {
      while (true) {
        board.printBoard();
        System.out.print(curr.getName() + " (" + curr.getSymbol() + "), " + "enter: ");

        int cell = sc.nextInt();

        // check if valid number
        if (cell < 1 || cell > 9) {
          System.out.println("Invalid cell. Enter a number between 1 and 9.");
          continue;
        }

        // play a move
        if (!board.placeMove(cell, curr.getSymbol())) {
          System.out.println("Cell already taken. Try again!");
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