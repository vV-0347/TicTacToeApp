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

    public static void main(String[] args) {

        initializeBoard();

        while (true) {

            printBoard();

            if (isHumanTurn) {
                System.out.println("Human's Turn");

                int slot = getUserInput();
                int[] pos = convertSlotToIndex(slot);

                if (isValidMove(pos[0], pos[1])) {
                    placeMove(pos[0], pos[1], humanSymbol);

                    // UC9: Check win
                    if (checkWin(humanSymbol)) {
                        printBoard();
                        System.out.println("Human wins!");
                        break;
                    }

                    // ✅ UC10: Check draw
                    if (isBoardFull()) {
                        printBoard();
                        System.out.println("It's a draw!");
                        break;
                    }

                    isHumanTurn = false;

                } else {
                    System.out.println("Invalid move! Try again.");
                    continue;
                }

            } else {
                System.out.println("Computer's Turn");

                computerMove();

                // UC9: Check win
                if (checkWin(computerSymbol)) {
                    printBoard();
                    System.out.println("Computer wins!");
                    break;
                }

                // ✅ UC10: Check draw
                if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    break;
                }

                isHumanTurn = true;
            }
        }
    }

    // UC1
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

    // UC3
    static int getUserInput() {
        System.out.print("Enter a slot (1-9): ");
        return scanner.nextInt();
    }

    // UC4
    static int[] convertSlotToIndex(int slot) {
        int row = (slot - 1) / 3;
        int col = (slot - 1) % 3;
        return new int[]{row, col};
    }

    // UC5
    static boolean isValidMove(int row, int col) {
        return (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-');
    }

    // UC6
    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    // UC7
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

    // UC9
    static boolean checkWin(char symbol) {

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol &&
                board[i][1] == symbol &&
                board[i][2] == symbol) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (board[0][j] == symbol &&
                board[1][j] == symbol &&
                board[2][j] == symbol) {
                return true;
            }
        }

        if (board[0][0] == symbol &&
            board[1][1] == symbol &&
            board[2][2] == symbol) {
            return true;
        }

        if (board[0][2] == symbol &&
            board[1][1] == symbol &&
            board[2][0] == symbol) {
            return true;
        }

        return false;
    }

    // ✅ UC10: Check draw
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