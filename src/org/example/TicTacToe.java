package org.example;

import java.util.Scanner;

public class TicTacToe {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int slot = getUserInput();

        int[] position = convertSlotToIndex(slot);

        System.out.println("Row: " + position[0]);
        System.out.println("Column: " + position[1]);
    }

    // UC3: Get user input
    static int getUserInput() {
        System.out.print("Enter a slot (1-9): ");
        return scanner.nextInt();
    }

    // UC4: Convert slot to row & column
    static int[] convertSlotToIndex(int slot) {
        int row = (slot - 1) / 3;
        int col = (slot - 1) % 3;

        return new int[]{row, col};
    }
}