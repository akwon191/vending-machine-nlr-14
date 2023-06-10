package com.techelevator.util;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PurchaseProcess{
    private BigDecimal balance = BigDecimal.valueOf(0.00);

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");



    public int printMenu(Scanner scanner) {


        int choice = 0;


        while (choice != 1 && choice != 2 && choice != 3) {
            System.out.println("\u001B[32m" + "Current Money Provided: $" + balance + "\n");

            System.out.println("\u001B[31m"+ "(1) Feed Money");
            System.out.println("\u001B[32m" + "(2) Select Product");
            System.out.println("\u001B[34m" + "(3) Finish Transaction");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                System.out.println();
            }
            catch (NumberFormatException | NoSuchElementException e){
                System.out.println("\u001B[32m" + "Please enter a valid number\n");

            }
        }return choice;

    }



    public void feedMoney(Scanner scanner) {
        System.out.print("\u001B[32m" + "Enter whole dollar amount: ");
        try {
            int amount1 = Integer.parseInt(scanner.nextLine());
            BigDecimal amount =BigDecimal.valueOf(amount1);
            System.out.println();
            if (amount.compareTo(BigDecimal.ZERO) > 0 ) {
                balance = balance.add(amount);
                System.out.println("\u001B[32m" + "Money fed successfully");
                System.out.println();
                LocalDateTime localdate = LocalDateTime.now();
                log(localdate.format(formatter) + " " + "Feed Money: $"+ amount+ " $" + balance);


            } else {
                System.out.println("\u001B[32m" + "Invalid amount. Please enter whole dollar amount\n");

            }

        } catch (NumberFormatException e) {
            System.out.println("\n\u001B[32m" + "Invalid amount. Please enter whole dollar amount\n");

        }


    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance (BigDecimal balance) {
        this.balance = balance;
    }

    public void purchaseMenu(VendingMachineMenu vending, Scanner scanner) {
        try{
        vending.displayMenu();

        System.out.println("\n\u001B[32m" + "Enter Product Location: ");
        String product = scanner.nextLine();

        if (product.length() != 2) {
            System.out.println("Invalid location " + "\u001B[31m" + product + "\n");

            purchaseMenu(vending, scanner);
        } else {
            String[] products = product.split("");
            products[0] = products[0].toUpperCase();
            product = products[0] + products[1];
            if (!vending.getInventory().containsKey(product)) {
                System.out.println("\u001B[32m" + "Invalid Location " + "\u001B[31m" + product + "\n");

                purchaseMenu(vending, scanner);
            }
            for (Map.Entry<String, Product> name : vending.getInventory().entrySet()) {
                if (product.equalsIgnoreCase(name.getKey()) && balance.compareTo(name.getValue().getPrice()) > 0 && name.getValue().getQuantity() != 0) {
                    vending.buyItem(product.toUpperCase());
                    balance = balance.subtract(name.getValue().getPrice());
                    System.out.println("\u001B[32m" + "Dispensing " + vending.getInventory().get(product).getName() + " " + vending.getInventory().get(product).getMessage() + "\n");
                    LocalDateTime localdate = LocalDateTime.now();
                    log(localdate.format(formatter) + " " + vending.getInventory().get(product).getName() + " " + name.getKey() + " $" + vending.getInventory().get(product).getPrice() + " $" + balance);

                } else if (product.equalsIgnoreCase(name.getKey()) && name.getValue().getPrice().compareTo(balance) > 0 && name.getValue().getQuantity() != 0) {
                    System.out.println("\u001B[32m" + "Insufficient funds\n");


                } else if (product.equalsIgnoreCase(name.getKey()) && name.getValue().getQuantity() == 0) {
                    System.out.println("\u001B[32m" + "Item is Sold Out\n");

                }
            }
        }

            }catch (NoSuchElementException e){
            System.out.println();
        }
    }

    public void finishTransaction() {

            int q1 = 0;
            int d1 = 0;
            int n1 = 0;

            BigDecimal originalChange = balance;
        while(balance.compareTo(BigDecimal.ZERO) > 0) {

            BigDecimal quarters = balance.divide(BigDecimal.valueOf(0.25), RoundingMode.HALF_DOWN);
            q1 = quarters.intValue();
            balance = balance.subtract(BigDecimal.valueOf(q1 * 0.25));

            BigDecimal dimes = balance.divide(BigDecimal.valueOf(0.10), RoundingMode.HALF_DOWN);
            d1 = dimes.intValue();
            balance = balance.subtract(BigDecimal.valueOf(d1 * 0.10));

            BigDecimal nickels = balance.divide(BigDecimal.valueOf(0.05), RoundingMode.HALF_DOWN);
            n1 = nickels.intValue();
            balance = balance.subtract(BigDecimal.valueOf(n1 * 0.05));


        }
        LocalDateTime localdate = LocalDateTime.now();
        log(localdate.format(formatter) + " " + "Give Change: $"+ originalChange +" $" + balance);
        System.out.println("\u001B[32m"+"Change returned: $ quarters: " + q1 + " dimes: " + d1 + " nickels: " + n1);


    }



    private void log (String message) {
        File file = new File("log.txt");
        boolean append = file.exists();

            try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, append))) {
                writer.println(message);

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());;
            }
        }

}
