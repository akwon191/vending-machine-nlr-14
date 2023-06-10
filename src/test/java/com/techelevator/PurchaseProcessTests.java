package com.techelevator;

import com.techelevator.util.PurchaseProcess;
import com.techelevator.util.VendingMachineMenu;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.util.Scanner;

public class PurchaseProcessTests {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PurchaseProcess purchaseProcess;
 @Before
 public void initializePurchase(){
     purchaseProcess = new PurchaseProcess();
 }
    @Test
    public void testPrintMenu_ValidChoice() {
            String input = "2\n";

            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            Scanner scanner = new Scanner(System.in);


            int choice = purchaseProcess.printMenu(scanner);

            Assert.assertEquals(2, choice);
    }

    @Test
    public void testFeedMoney() {
        String input = "10\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);


        purchaseProcess.feedMoney(scanner);

        BigDecimal expectedBalance = BigDecimal.valueOf(10.0);
        Assert.assertEquals(expectedBalance, purchaseProcess.getBalance());
    }

    @Test
    public void testFinishTransaction_ValidBalance() {

        purchaseProcess.setBalance(BigDecimal.valueOf(2.35));

        purchaseProcess.finishTransaction();

        BigDecimal expectedBalance = new BigDecimal("0.00");
        Assert.assertEquals(expectedBalance, purchaseProcess.getBalance());
    }
    @Test
    public void testPurchaseMenu()  {
        String input = "A1\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));
        Scanner scanner = new Scanner(System.in);
        VendingMachineMenu vending = new VendingMachineMenu(new File("testScanner.csv"));


        String expectedOutput = "\u001B[33m"+"A1: Potato Chips: $1.50: Available 5\r\n" +
                "\u001B[31m"+"B2: Chocolate Bar: $1.25: Available 5\r\n" +
                "\u001B[34m"+"C3: Soda: $1.75: Available 5\r\n" +
                "\u001B[35m"+"D4: Mint Gum: $0.75: Available 5\r\n" + "\n\u001B[32m"+"Enter Product Location: \r\n"+
        "\u001B[32m"+"Dispensing Potato Chips Crunch Crunch, Yum!\n\r\n";

        purchaseProcess.setBalance(BigDecimal.valueOf(20));
        purchaseProcess.purchaseMenu(vending,scanner);
        String out = outContent.toString();
        Assert.assertEquals(expectedOutput,out);

    }
    @Test
    public void testPrintMenuError1()  {
        String input = "l\n";
        File file = new File("testscannererror.csv");

        System.setOut(new PrintStream(outContent));
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        purchaseProcess.printMenu(scanner);
        String expectedOutput = "\u001B[32m" + "Current Money Provided: $" + 0.0 + "\n\r\n"+ "\u001B[31m"+
                "(1) Feed Money\r\n" +"\u001B[32m"
                + "(2) Select Product\r\n" + "\u001B[34m" +
                "(3) Finish Transaction\r\n" +"\u001B[32m" +
                "Please enter a valid number\n\r\n" + "\u001B[32m" + "Current Money Provided: $" + 0.0 + "\n\r\n"+ "\u001B[31m"+
                "(1) Feed Money\r\n" +"\u001B[32m"
                + "(2) Select Product\r\n" + "\u001B[34m" +
                "(3) Finish Transaction\r\n\r\n";
        String out = outContent.toString();

        Assert.assertEquals(expectedOutput,out);

    }
    @Test
    public void testPurchaseMenuError1()  {
        String input = "q\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));
        Scanner scanner = new Scanner(System.in);
        VendingMachineMenu vending = new VendingMachineMenu(new File("testScanner.csv"));


        String expectedOutput = "\u001B[33m"+"A1: Potato Chips: $1.50: Available 5\r\n" +
                "\u001B[31m"+"B2: Chocolate Bar: $1.25: Available 5\r\n" +
                "\u001B[34m"+"C3: Soda: $1.75: Available 5\r\n" +
                "\u001B[35m"+"D4: Mint Gum: $0.75: Available 5\r\n" + "\n\u001B[32m"+"Enter Product Location: \r\n"+
                "Invalid location " + "\u001B[31m" + "q\n" + "\r\n" + "\u001B[33m"+"A1: Potato Chips: $1.50: Available 5\r\n" +
                "\u001B[31m"+"B2: Chocolate Bar: $1.25: Available 5\r\n" +
                "\u001B[34m"+"C3: Soda: $1.75: Available 5\r\n" +
                "\u001B[35m"+"D4: Mint Gum: $0.75: Available 5\r\n" + "\n\u001B[32m"+"Enter Product Location: \r\n\r\n";

        purchaseProcess.purchaseMenu(vending,scanner);
        String out = outContent.toString();
        Assert.assertEquals(expectedOutput,out);

    }

    @Test
    public void feedMoneyTooSmallTest(){
        String input = "-10\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        System.setOut(new PrintStream(outContent));

        purchaseProcess.feedMoney(scanner);
        String out = outContent.toString();

        String expectedOutput = "\u001B[32m" + "Enter whole dollar amount: \r\n" + "\u001B[32m" + "Invalid amount. Please enter whole dollar amount\n\r\n";
        Assert.assertEquals(expectedOutput, out);
    }
    @Test
    public void feedMoneyNotNumbertest(){
        String input = "q\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        System.setOut(new PrintStream(outContent));

        purchaseProcess.feedMoney(scanner);
        String out = outContent.toString();

        String expectedOutput = "\u001B[32m" + "Enter whole dollar amount: " + "\n\u001B[32m" + "Invalid amount. Please enter whole dollar amount\n\r\n";
        Assert.assertEquals(expectedOutput, out);
    }
    @Test
    public void testPurchaseMenuWrong()  {
        String input = "A2\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));
        Scanner scanner = new Scanner(System.in);
        VendingMachineMenu vending = new VendingMachineMenu(new File("testScanner.csv"));


        String expectedOutput = "\u001B[33m"+"A1: Potato Chips: $1.50: Available 5\r\n" +
                "\u001B[31m"+"B2: Chocolate Bar: $1.25: Available 5\r\n" +
                "\u001B[34m"+"C3: Soda: $1.75: Available 5\r\n" +
                "\u001B[35m"+"D4: Mint Gum: $0.75: Available 5\r\n" + "\n\u001B[32m"+"Enter Product Location: \r\n"+
                "\u001B[32m" + "Invalid Location " + "\u001B[31m" + "A2" + "\n\r\n" + "\u001B[33m"+"A1: Potato Chips: $1.50: Available 5\r\n" +
                "\u001B[31m"+"B2: Chocolate Bar: $1.25: Available 5\r\n" +
                "\u001B[34m"+"C3: Soda: $1.75: Available 5\r\n" +
                "\u001B[35m"+"D4: Mint Gum: $0.75: Available 5\r\n" + "\n\u001B[32m"+"Enter Product Location: \r\n\r\n" ;


        purchaseProcess.purchaseMenu(vending,scanner);
        String out = outContent.toString();
        Assert.assertEquals(expectedOutput,out);

    }
    @Test
    public void testItemSoldOut()  {
        String input = "A1\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));
        Scanner scanner = new Scanner(System.in);
        VendingMachineMenu vending = new VendingMachineMenu(new File("testScanner.csv"));
        vending.buyItem("A1");
        vending.buyItem("A1");
        vending.buyItem("A1");
        vending.buyItem("A1");
        vending.buyItem("A1");

        String expectedOutput = "\u001B[33m"+"A1: Potato Chips: $1.50: Sold Out\r\n" +
                "\u001B[31m"+"B2: Chocolate Bar: $1.25: Available 5\r\n" +
                "\u001B[34m"+"C3: Soda: $1.75: Available 5\r\n" +
                "\u001B[35m"+"D4: Mint Gum: $0.75: Available 5\r\n" + "\n\u001B[32m"+"Enter Product Location: \r\n"+
                "\u001B[32m" + "Item is Sold Out\n\r\n";


        purchaseProcess.purchaseMenu(vending,scanner);
        String out = outContent.toString();
        Assert.assertEquals(expectedOutput,out);

    }
    @Test
    public void testNoMoney()  {
        String input = "A1\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));
        Scanner scanner = new Scanner(System.in);
        VendingMachineMenu vending = new VendingMachineMenu(new File("testScanner.csv"));


        String expectedOutput = "\u001B[33m"+"A1: Potato Chips: $1.50: Available 5\r\n" +
                "\u001B[31m"+"B2: Chocolate Bar: $1.25: Available 5\r\n" +
                "\u001B[34m"+"C3: Soda: $1.75: Available 5\r\n" +
                "\u001B[35m"+"D4: Mint Gum: $0.75: Available 5\r\n" + "\n\u001B[32m"+"Enter Product Location: \r\n"+
                "\u001B[32m" + "Insufficient funds\n\r\n";


        purchaseProcess.purchaseMenu(vending,scanner);
        String out = outContent.toString();
        Assert.assertEquals(expectedOutput,out);

    }
}
