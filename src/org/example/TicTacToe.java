package org.example;

import java.util.Scanner;

public class TicTacToe {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int slot = getUserInput();
        System.out.println("You selected slot: " + slot);
    }

    // Method to accept user input
    static int getUserInput() {
        System.out.print("Enter a slot number (1-9): ");
        int slot = scanner.nextInt();
        return slot;
    }
}