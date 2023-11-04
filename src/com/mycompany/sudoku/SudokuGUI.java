import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class SudokuGUI extends JFrame {
    private int[][] board;

    public SudokuGUI(int[][] board) {
        this.board = board;
        setTitle("Sudoku Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField textField = new JTextField();
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setFont(new Font("Arial", Font.BOLD, 20));
                if (board[i][j] != 0) {
                    textField.setText(String.valueOf(board[i][j]));
                    textField.setEditable(false);
                }
                panel.add(textField);
            }
        }
        add(panel);
    }

    public static void main(String[] args) {
        int[][] board = createBlankBoard();
        fillDiagonalSubgrids(board);
        fillBoard(board);

        SwingUtilities.invokeLater(() -> {
            SudokuGUI sudokuGUI = new SudokuGUI(board);
            sudokuGUI.setVisible(true);
        });
    }

    // Duplicate code in Main.java - fixme
    public static int[][] createBlankBoard() {
        return new int[9][9]; // hard coded size for now
    }

    // lets user solve the puzzle
    public static void solveSudoku(int[][] board) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nEnter row and column (1-9) and value (1-9) to fill or 0 to exit: ");

            System.out.println("\nRow: ");
            int row = scanner.nextInt();
            if (row == 0) {
                break;
            }
            System.out.println("\nColumn: ");
            int col = scanner.nextInt();
            System.out.println("\nValue: ");
            int value = scanner.nextInt();
            if (isValidMove(board, row - 1, col - 1, value)) {
                board[row - 1][col - 1] = value;
                printSudokuBoard(board);
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
        scanner.close();
    }

       // check if a move is valid
       public static boolean isValidMove(int[][] board, int row, int col, int num) {
        // Check row and column
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // Check subgrid
        int subgridStartRow = row - row % 3;
        int subgridStartCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[subgridStartRow + i][subgridStartCol + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void fillDiagonalSubgrids(int[][] board) {
        for (int i = 0; i < 9; i += 3) {
            fillSubgrid(board, i, i);
        }
    }

    private static void fillSubgrid(int[][] board, int row, int col) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        shuffleArray(nums);
        int index = 0;
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                board[i][j] = nums[index++];
            }
        }
    }

    private static void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public static boolean fillBoard(int[][] board) {
        // Find an unassigned cell
        int[] nextCell = findUnassignedLocation(board);
        if (nextCell == null) {
            return true; // Board is filled, puzzle solved
        }

        int row = nextCell[0];
        int col = nextCell[1];

        for (int num = 1; num <= 9; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;

                if (fillBoard(board)) {
                    return true;
                }

                board[row][col] = 0; // Backtrack
            }
        }

        return false; // No valid number can be placed, backtrack
    }

    private static int[] findUnassignedLocation(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    return new int[]{row, col};
                }
            }
        }
        return null; // All cells are filled
    }

    private static boolean isSafe(int[][] board, int row, int col, int num) {
        return isRowSafe(board, row, num) && isColSafe(board, col, num) && isSubgridSafe(board, row - row % 3, col - col % 3, num);
    }

    private static boolean isRowSafe(int[][] board, int row, int num) {
        for (int col = 0; col < 9; col++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean isColSafe(int[][] board, int col, int num) {
        for (int row = 0; row < 9; row++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSubgridSafe(int[][] board, int subRow, int subCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row + subRow][col + subCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printSudokuBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
