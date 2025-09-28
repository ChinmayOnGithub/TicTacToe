import javax.swing.*;
import java.awt.*;

import game.Board;
import game.Player;

public class TicTacToeUI {

    private final JFrame frame;
    private final JButton[][] buttons = new JButton[3][3];
    private final Board board = new Board();
    private final Player p1 = new Player("Player_1", 'X');
    private final Player p2 = new Player("Player_2", 'O');
    private Player current = p1;

    public TicTacToeUI() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setResizable(false);

        JPanel panel = new JPanel(new GridLayout(3, 3, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton("");
                btn.setFont(new Font("Arial", Font.BOLD, 60));
                final int cell = i * 3 + j + 1; // 1-9 mapping
                btn.addActionListener(e -> handleMove(cell, btn));
                buttons[i][j] = btn;
                panel.add(btn);
            }
        }

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void handleMove(int cell, JButton btn) {
        if (!board.placeMove(cell, current.getSymbol()) || !btn.getText().isEmpty())
            return;

        btn.setText(String.valueOf(current.getSymbol()));

        if (board.haveWon(current.getSymbol())) {
            JOptionPane.showMessageDialog(frame, current.getName() + " wins!");
            resetBoard();
            return;
        }

        if (board.isFull()) {
            JOptionPane.showMessageDialog(frame, "It's a draw!");
            resetBoard();
            return;
        }

        current = (current == p1) ? p2 : p1;
    }

    // reset board ui (buttons)
    private void resetBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttons[i][j].setText("");

        boardReset();
        current = p1;
    }

    // reset board grid
    private void boardReset() {
        for (int i = 1; i <= 9; i++)
            board.placeMove(i, ' ');
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeUI::new);
    }
}
