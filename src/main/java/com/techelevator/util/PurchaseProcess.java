package com.techelevator.util;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class PurchaseProcess implements LogTransaction{
    private BigDecimal balance = BigDecimal.valueOf(0.0);

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

    private LocalDateTime localdate = LocalDateTime.now();

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
            System.out.println("Please enter a valid number");
            System.out.println();
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
                    log(localdate.format(formatter) + " " + vending.getInventory().get(product).getName() + " " + name.getKey() + " " + vending.getInventory().get(product).getPrice() + " " + balance);

                } else if (product.equalsIgnoreCase(name.getKey()) && name.getValue().getPrice().compareTo( balance) > 0 && name.getValue().getQuantity() != 0) {
                    System.out.println("Insufficient funds");

                } else if (product.equalsIgnoreCase(name.getKey())  && name.getValue().getQuantity() == 0) {
                    System.out.println("Item is Sold Out");

                }
            }

        System.out.println();
    }

    public void finishTransaction() {
        int q1;
        int d1;
        int n1;

        BigDecimal change = balance;
        BigDecimal quarters = change.divide(BigDecimal.valueOf(0.25), RoundingMode.HALF_DOWN);
        q1 = quarters.intValue();
        change = change.subtract(BigDecimal.valueOf(q1 * 0.25));

        BigDecimal dimes = change.divide(BigDecimal.valueOf(0.10), RoundingMode.HALF_DOWN);
        d1 = dimes.intValue();
        change = change.subtract(BigDecimal.valueOf(d1 * 0.10));

        BigDecimal nickels = change.divide(BigDecimal.valueOf(0.05), RoundingMode.HALF_DOWN);
        n1 = nickels.intValue();
        change = change.subtract(BigDecimal.valueOf(0.05));

        balance = change;

        System.out.println("Change returned: $ quarters: " + q1 + " dimes: " + d1 + " nickels: " + n1);


    }


    @Override
    public void log(String message) {
        File file = new File("log.txt");
        boolean append = file.exists();

            try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, append))) {
                writer.println(message);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

}
