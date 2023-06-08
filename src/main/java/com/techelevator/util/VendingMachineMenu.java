package com.techelevator.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VendingMachineMenu {
    private Map<String, Product> inventory = new TreeMap<>();

    public VendingMachineMenu(File file) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] item = line.split("\\|");
                if (item.length == 4) {
                    if (item[3].equals("Chip")) {
                        inventory.put(item[0], new Chip(item[1],new BigDecimal(item[2]), 5));
                    } else if (item[3].equals("Candy")) {
                        inventory.put(item[0], new Candy(item[1], new BigDecimal(item[2]), 5));
                    } else if (item[3].equals("Drink")) {
                        inventory.put(item[0], new Drink(item[1], new BigDecimal(item[2]), 5));
                    } else if (item[3].equals("Gum")) {
                        inventory.put(item[0], new Gum(item[1], new BigDecimal(item[2]), 5));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayMenu() {
        for (Map.Entry<String, Product> entry : inventory.entrySet()) {
            if (entry.getValue().getQuantity() > 0) {
                System.out.println(entry.getKey() + ": " + entry.getValue().getName() + ": " + entry.getValue().getPrice() + ": Available " + entry.getValue().getQuantity());

            } else {
                System.out.println(entry.getKey() + ": " + entry.getValue().getName() + ": " + entry.getValue().getPrice() + ": Sold Out");

            }
        }
    }

    public Map<String, Product> getInventory() {
        return inventory;
    }
    public void buyItem(String name){
        Product currentItem = inventory.get(name);
        if (currentItem.getQuantity()>0){
        currentItem.setQuantity(currentItem.getQuantity()-1);

        }
    }
}

