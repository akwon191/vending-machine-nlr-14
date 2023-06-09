package com.techelevator;

import com.techelevator.util.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class VendingMachineMenuTests {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @Test
    public void createExpectedInventory() {

        VendingMachineMenu menu = new VendingMachineMenu(new File("testmenu.csv"));
        Map<String,Product> expectedInventory = new TreeMap<>();

            Map.Entry<String, Product> a1 = Map.entry("A1", new Chip("Potato Chips", new BigDecimal("1.50"), 5));
            Map.Entry<String, Product> b2 = Map.entry("B2", new Candy("Chocolate Bar", new BigDecimal("1.25"), 5));
            Map.Entry<String, Product> c3 = Map.entry("C3", new Drink("Soda", new BigDecimal("1.75"), 5));
            Map.Entry<String, Product> d4 = Map.entry("D4", new Gum("Mint Gum", new BigDecimal("0.75"), 5));
            menu.setInventory(a1);
            menu.setInventory(b2);
            menu.setInventory(c3);
            menu.setInventory(d4);
            expectedInventory.put(a1.getKey(), a1.getValue());
            expectedInventory.put(b2.getKey(), b2.getValue());
            expectedInventory.put(c3.getKey(), c3.getValue());
            expectedInventory.put(d4.getKey(), d4.getValue());

            Assert.assertEquals(expectedInventory, menu.getInventory());

    }



    @Test
    public void testDisplayMenu() {
        System.setOut(new PrintStream(outContent));
        VendingMachineMenu menu = new VendingMachineMenu(new File("testScanner.csv"));

        menu.buyItem("A1");
        menu.buyItem("A1");
        menu.buyItem("A1");
        menu.buyItem("A1");
        menu.buyItem("A1");
        menu.displayMenu();
        String expectedOutput = "\u001B[33m"+"A1: Potato Chips: $1.50: Sold Out\r\n" +
                "\u001B[31m"+"B2: Chocolate Bar: $1.25: Available 5\r\n" +
                "\u001B[34m"+"C3: Soda: $1.75: Available 5\r\n" +
                "\u001B[35m"+"D4: Mint Gum: $0.75: Available 5\r\n";

        Assert.assertEquals(expectedOutput,outContent.toString());
    }

    @Test
    public void testForException(){
        System.setOut(new PrintStream(outContent));
        VendingMachineMenu test = new VendingMachineMenu(new File(""));
        String expectedOutput = "\u001B[32m" + "Vending CSV Not Found\r\n";
        Assert.assertEquals(expectedOutput,outContent.toString());
    }
}
