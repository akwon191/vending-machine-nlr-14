package com.techelevator;

import com.techelevator.util.PurchaseProcess;
import com.techelevator.util.VendingMachineMenu;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

public class VendingMachineCLITest {
    private InputStream mockInput;
    private VendingMachineCLI vending;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    public void testRun_DisplayMenuOptionChosen() {

        mockInput = new ByteArrayInputStream("\n1".getBytes());
        System.setIn(mockInput);
        System.setOut(new PrintStream(outContent));
        Scanner scanner = new Scanner(System.in);

        vending = new VendingMachineCLI(scanner);
        vending.run();

        String expectedOutput =  "\u001B[31m" + "(1) Display Vending Machine Items\r\n" +
                "\u001B[32m" + "(2) Purchase\r\n" +
                "\u001B[34m" + "(3) exit\r\n" + ""
                ;

        String out = outContent.toString();
        Assert.assertEquals(expectedOutput, out);

    }
}
