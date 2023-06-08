package com.techelevator;

import com.techelevator.util.PurchaseProcess;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Scanner;

public class PurchaseProcessTests {
    private PurchaseProcess purchaseProcess = new PurchaseProcess();

    @Test
    public void testPrintMenu_ValidChoice() {
        String input = "2";
        Scanner scanner = new Scanner(System.in);

        BigDecimal choice = BigDecimal.valueOf(PurchaseProcess.printMenu(scanner));

        Assert.assertEquals(2, choice);
    }

    @Test
    public void testFeedMoney_isValidAmount() {
        String input = "5";
        Scanner scanner = new Scanner(System.in);

        PurchaseProcess.feedMoney(scanner);

        Assert.assertEquals(new BigDecimal("5.00"), PurchaseProcess.getBalance());
    }
}
