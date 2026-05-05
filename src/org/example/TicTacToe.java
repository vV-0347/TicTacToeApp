package org.example;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static char[][] board = new char[3][3];
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    static char humanSymbol = 'X';
    static char computerSymbol = 'O';

    public static void main(String[] args) {
        initializeBoard();
        printBoard();

        // Human move
        int slot = getUserInput();
        int[] pos = convertSlotToIndex(slot);

        if (isValidMove(pos[0], pos[1])) {
            placeMove(pos[0], pos[1], humanSymbol);
        } else {
            System.out.println("Invalid move!");
        }

        printBoard();

        // ✅ UC7: Computer move
        computerMove();

        printBoard();
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

    // User input
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

    // ✅ UC7: Computer random move
    static void computerMove() {
        int row, col;

        while (true) {
            int slot = random.nextInt(9) + 1; // 1–9
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
}