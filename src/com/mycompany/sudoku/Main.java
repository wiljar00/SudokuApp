package com.mycompany.sudoku;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        sayHello();

        System.out.println("\n");

        int[][] newBoard = createBlankBoard();
        System.out.println("Blank Board: ");
        printSudokuboard(newBoard);

        System.out.println("\n");

        fillDiagonalSubgrids(newBoard);
        System.out.println("Filled Board: ");
        printSudokuboard(newBoard);
    }

    public static void sayHello() {
        System.out.println("Welcome to my app!");
        System.out.println("- - - - - - - - - - ");
        System.out.println("- - - SUDOKU! - - - ");
        System.out.println("- - - - - - - - - - ");
    }


    public static int[][] createBlankBoard() {
        int[][] board = new int[9][9]; // hard coded size for now
        return board;
    }

    // fills each of the main diagonal 3x3 subgrids with random, valid numbers by calling the fillSubgrid method
    public static void fillDiagonalSubgrids(int[][] board) {
        for (int i = 0; i < 9; i += 3) {
            fillSubgrid(board, i, i);
        }
    }

    // fills a 3x3 subgrid starting from the specified row and column with shuffled numbers from 1 to 9
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

    // shuffles the array of numbers to be placed in the subgrid, ensuring that 
    // each row, column, and 3x3 subgrid contains unique numbers from 1 to 9 
    private static void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
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