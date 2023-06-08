package com.techelevator.util;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class PurchaseProcess {
    private BigDecimal balance = BigDecimal.valueOf(0.0);

    public int printMenu(Scanner scanner) {
        System.out.println("Current Money Provided: $" + balance);
        System.out.println();
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");

        try {
            int choice = Integer.parseInt(scanner.nextLine());


        while (choice != 1 && choice != 2 && choice != 3) {
            System.out.println("Current Money Provided: $" + balance);
            System.out.println();
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            choice = Integer.parseInt(scanner.nextLine());
        }return choice;

    }catch (NumberFormatException e){
            printMenu(scanner);
        }
        return 0;
    }

    public void feedMoney(Scanner scanner) {
        System.out.print("Enter whole dollar amount: ");
        try {
            BigDecimal amount =new BigDecimal(scanner.nextLine());
            if (amount.compareTo(BigDecimal.ZERO) > 0) {
                balance = balance.add(amount);
                System.out.println("Money fed successful");
                System.out.println();
            } else {
                System.out.println("Invalid amount. Please enter whole dollar amount");
                System.out.println();
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter whole dollar amount");
        }


    }
    public void purchaseMenu(VendingMachineMenu vending, Scanner scanner){
        vending.displayMenu();
        System.out.println();
        System.out.println("Enter Product Location: ");
        String product = scanner.nextLine();
        String[] products = product.split("");
        products[0] = products[0].toUpperCase();
        product = products[0]+products[1];
        while (!vending.getInventory().containsKey(product)){
            System.out.println();
            System.out.println("Enter Product Location: ");
            product = scanner.nextLine();
            products = product.split("");
            products[0] = products[0].toUpperCase();
            product = products[0]+products[1];
        }
            for (Map.Entry<String, Product> name : vending.getInventory().entrySet()) {
                if (product.equalsIgnoreCase(name.getKey()) && balance.compareTo(name.getValue().getPrice()) > 0 && name.getValue().getQuantity() != 0) {
                    vending.buyItem(product.toUpperCase());
                    balance = balance.subtract( name.getValue().getPrice());
                    System.out.println("Dispensing " + vending.getInventory().get(product).getName() + " " +  vending.getInventory().get(product).getMessage());
                } else if (product.equalsIgnoreCase(name.getKey()) && name.getValue().getPrice().compareTo( balance) > 0 && name.getValue().getQuantity() != 0) {
                    System.out.println("Insufficient funds");

                } else if (product.equalsIgnoreCase(name.getKey())  && name.getValue().getQuantity() == 0) {
                    System.out.println("Item is Sold Out");

                }
            }

        System.out.println();
        printMenu(scanner);
    }
}
