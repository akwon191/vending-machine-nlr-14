package com.techelevator;

import com.techelevator.util.PurchaseProcess;
import com.techelevator.util.VendingMachineMenu;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;

public class PurchaseProcessTests {
    private PurchaseProcess purchaseProcess = new PurchaseProcess();

    @Test
    public void testPrintMenu_ValidChoice() {
            String input = "2\n";

            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            Scanner scanner = new Scanner(System.in);

            PurchaseProcess purchaseProcess = new PurchaseProcess();
            int choice = purchaseProcess.printMenu(scanner);

            Assert.assertEquals(2, choice);
    }

    @Test
    public void testFeedMoney() {
        String input = "10\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        PurchaseProcess purchaseProcess = new PurchaseProcess();
        purchaseProcess.feedMoney(scanner);

        BigDecimal expectedBalance = BigDecimal.valueOf(10.0);
        Assert.assertEquals(expectedBalance, purchaseProcess.getBalance());
    }

    @Test
    public void testFinishTransaction_ValidBalance() {
        PurchaseProcess purchaseProcess = new PurchaseProcess();
        purchaseProcess.setBalance(BigDecimal.valueOf(2.35));

        purchaseProcess.finishTransaction();

        BigDecimal expectedBalance = BigDecimal.valueOf(0.00);
        Assert.assertEquals(expectedBalance, purchaseProcess.getBalance());
    }
}
