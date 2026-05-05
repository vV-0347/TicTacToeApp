package org.example;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static char[][] board = new char[3][3];
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    static char humanSymbol = 'X';
    static char computerSymbol = 'O';

    static boolean isHumanTurn = true;
    static boolean gameOver = false;

    public static void main(String[] args) {

        initializeBoard();

        while (!gameOver) {

            printBoard();

            if (isHumanTurn) {
                System.out.println("Human's Turn");

                int slot = getUserInput();
                int[] pos = convertSlotToIndex(slot);

                if (isValidMove(pos[0], pos[1])) {
                    placeMove(pos[0], pos[1], humanSymbol);
                    isHumanTurn = false; // switch turn
                } else {
                    System.out.println("Invalid move! Try again.");
                    continue; // retry same turn
                }

            } else {
                System.out.println("Computer's Turn");
                computerMove();
                isHumanTurn = true; // switch turn
            }

            // Check game status
            if (checkWin(humanSymbol)) {
                printBoard();
                System.out.println("Human wins!");
                gameOver = true;
            } else if (checkWin(computerSymbol)) {
                printBoard();
                System.out.println("Computer wins!");
                gameOver = true;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                gameOver = true;
            }
        }
    }

    // Initialize board
    static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print board
    static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Input
    static int getUserInput() {
        System.out.print("Enter a slot (1-9): ");
        return scanner.nextInt();
    }

    // Convert slot → row, col
    static int[] convertSlotToIndex(int slot) {
        int row = (slot - 1) / 3;
        int col = (slot - 1) % 3;
        return new int[]{row, col};
    }

    // Validate move
    static boolean isValidMove(int row, int col) {
        return (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-');
    }

    // Place move
    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    // Computer move (random)
    static void computerMove() {
        int row, col;

        while (true) {
            int slot = random.nextInt(9) + 1;
            int[] pos = convertSlotToIndex(slot);

            row = pos[0];
            col = pos[1];

            if (isValidMove(row, col)) {
                placeMove(row, col, computerSymbol);
                System.out.println("Computer chose slot: " + slot);
                break;
            }
        }
    }

    // ✅ Check win
    static boolean checkWin(char symbol) {

        // Rows & Columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                return true;
            }
        }

        // Diagonals
        if ((board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
            (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
            return true;
        }

        return false;
    }

    // ✅ Check draw
    static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}