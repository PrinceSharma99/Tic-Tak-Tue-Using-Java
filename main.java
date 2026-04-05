import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TicTacToe {
    private static String[][] board = new String[3][3];
    private static JButton[][] buttons = new JButton[3][3];
    private static String player = "X";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic Tac Toe - Player X Turn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 400);
        frame.setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        // Initialize board with empty values
        resetBoard();

        // Create buttons for the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 30)); // Adjusted font size to 30
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setPreferredSize(new Dimension(100, 100));

                // Add borders to each button
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

                int row = i;
                int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        onClick(row, col);
                    }
                });
                boardPanel.add(buttons[i][j]);
            }
        }

        // Reset button
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 30));
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        // Create a panel for the reset button and center it at the bottom
        JPanel resetPanel = new JPanel();
        resetPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        resetPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding around the reset button
        resetPanel.add(resetButton);

        // Add components to the frame
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(resetPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static void onClick(int row, int col) {
        if (board[row][col].equals(" ")) {
            board[row][col] = player;
            buttons[row][col].setText(player);
            buttons[row][col].setEnabled(false);
            buttons[row][col].setBackground(player.equals("X") ? Color.PINK : Color.LIGHT_GRAY);

            if (checkWinner()) {
                JOptionPane.showMessageDialog(null, "Player " + player + " Wins!");
                resetGame();
            } else if (checkDraw()) {
                JOptionPane.showMessageDialog(null, "It's a Draw!");
                resetGame();
            } else {
                player = player.equals("X") ? "O" : "X";
                ((JFrame) SwingUtilities.getWindowAncestor(buttons[0][0]))
                        .setTitle("Tic Tac Toe - Player " + player + " Turn");
            }
        }
    }

    private static boolean checkWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals(" ")) {
                return true;
            }
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals(" ")) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals(" ")) {
            return true;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals(" ")) {
            return true;
        }
        return false;
    }

    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void resetGame() {
        resetBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(" ");
                buttons[i][j].setEnabled(true);
                buttons[i][j].setBackground(null);
            }
        }
        player = "X";
        ((JFrame) SwingUtilities.getWindowAncestor(buttons[0][0])).setTitle("Tic Tac Toe - Player X Turn");
    }

    private static void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = " ";
            }
        }
    }
}