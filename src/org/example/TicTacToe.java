package org.example;

import java.util.Scanner;

public class TicTacToe {

    static char[][] board = new char[3][3];
    static Scanner scanner = new Scanner(System.in);
    static char humanSymbol = 'X';

    public static void main(String[] args) {
        initializeBoard();
        printBoard();

        int slot = getUserInput();
        int[] pos = convertSlotToIndex(slot);

        if (isValidMove(pos[0], pos[1])) {
            board[pos[0]][pos[1]] = humanSymbol;
            System.out.println("Move accepted!");
        } else {
            System.out.println("Invalid move! Try again.");
        }

        printBoard();
    }

    // UC1: Initialize board
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

    // UC3: Input
    static int getUserInput() {
        System.out.print("Enter a slot (1-9): ");
        return scanner.nextInt();
    }

    // UC4: Convert slot → row, col
    static int[] convertSlotToIndex(int slot) {
        int row = (slot - 1) / 3;
        int col = (slot - 1) % 3;
        return new int[]{row, col};
    }

    // ✅ UC5: Validate move
    static boolean isValidMove(int row, int col) {

        // Check bounds
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }

        // Check if cell is empty
        if (board[row][col] != '-') {
            return false;
        }

        return true;
    }
}