package com.mycompany.sudoku;

public class Main {

    public static void main(String[] args) {
        sayHello();

        int[][] board = createExampleBoard();
        printSudokuboard(board);
    }

    public static void sayHello() {
        System.out.println("Welcome to my app!");
        System.out.println("- - - - - - - - - - ");
        System.out.println("- - - SUDOKU! - - - ");
        System.out.println("- - - - - - - - - - ");
    }

    public static int[][] createExampleBoard() {
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        return board;
    }

    public static void printSudokuboard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}