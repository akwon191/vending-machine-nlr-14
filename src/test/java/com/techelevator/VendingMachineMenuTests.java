package com.techelevator;

import com.techelevator.util.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class VendingMachineMenuTests {

    private File file;
    private VendingMachineMenu vendingMachineMenu;
    @Before
    public Map<String, Product> createExpectedInventory() {
        Map<String, Product> inventory = new TreeMap<>();

        inventory.put("A1", new Chip("Potato Chips", new BigDecimal("1.50"), 5));
        inventory.put("B2", new Candy("Chocolate Bar", new BigDecimal("1.25"), 5));
        inventory.put("C3", new Drink("Soda", new BigDecimal("1.75"), 5));
        inventory.put("D4", new Gum("Mint Gum", new BigDecimal("0.75"), 5));

        return inventory;
    }

    @Test
    public void testVendingMachineMenu_Constructor() {
        File file = new File("testInventory.txt");
        VendingMachineMenu vendingMachineMenu = new VendingMachineMenu(file);
        Map<String, Product> expectedInventory = createExpectedInventory();

        Assert.assertEquals(expectedInventory, vendingMachineMenu.getInventory());
    }

    @Test
    public void testDisplayMenu() {
        Map<String, Product> inventory = createExpectedInventory();
        vendingMachineMenu.getInventory();

        vendingMachineMenu.displayMenu();

        String expectedOutput = "A1: Chips: $1.50: Available 5\n" +
                "B2: Candy: $1.25: Sold Out\n" +
                "C3: Soda: $2.00: Available 3\n" +
                "D4: Gum: $0.75: Available 10\n";

    }
}
