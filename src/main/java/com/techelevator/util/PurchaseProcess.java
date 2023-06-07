package com.techelevator.util;

import java.util.Scanner;

public class PurchaseProcess {
    private double balance = 0.0;

    public int printMenu(Scanner scanner) {
        System.out.println("Current Money Provided: $" + balance);
        System.out.println();
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");

        int choice = Integer.parseInt(scanner.nextLine());
        while (choice != 1 && choice != 2 && choice != 3) {
            System.out.println("Current Money Provided: $" + balance);
            System.out.println();
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            choice = Integer.parseInt(scanner.nextLine());
        }
        return choice;
    }

    public void feedMoney(Scanner scanner) {
        System.out.print("Enter whole dollar amount: ");
        try {
            int amount = Integer.parseInt(scanner.nextLine());
            if (amount > 0) {
                balance += amount;
                System.out.println("Money fed successful");
            } else {
                System.out.println("Invalid amount. Please enter whole dollar amount");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter whole dollar amount");
        }
        printMenu(scanner);
    }
}
