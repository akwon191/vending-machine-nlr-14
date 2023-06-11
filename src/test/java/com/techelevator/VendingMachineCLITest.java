package com.techelevator;

import com.techelevator.util.PurchaseProcess;
import com.techelevator.util.VendingMachineMenu;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class VendingMachineCLITest {
    private InputStream mockInput;
    private VendingMachineCLI vending;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    public void testRun_DisplayMenuOptionChosen() {

        mockInput = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(mockInput);
        Scanner scanner = new Scanner(System.in);

        vending = new VendingMachineCLI(scanner);
        System.setOut(new PrintStream(outContent));

        vending.getChoice();

        String expectedOutput =  "\u001B[31m" + "(1) Display Vending Machine Items\r\n" +
                "\u001B[32m" + "(2) Purchase\r\n" +
                "\u001B[34m" + "(3) exit\r\n\r\n";

        String out = outContent.toString();
        Assert.assertEquals(expectedOutput, out);

    }
    @Test (expected = NoSuchElementException.class)
    public void testRun_DisplayMenuOptionWrong() throws Exception {

        mockInput = new ByteArrayInputStream("q\n".getBytes());
        System.setIn(mockInput);
        Scanner scanner = new Scanner(System.in);

        vending = new VendingMachineCLI(scanner);


        vending.run();


    }
 @Test
    public void testMain(){
     mockInput = new ByteArrayInputStream("1\n2\n1\n5\n2\na1\n3\n3\n".getBytes());
     System.setIn(mockInput);
     Scanner scanner = new Scanner(System.in);

     vending = new VendingMachineCLI(scanner);
     System.setOut(new PrintStream(outContent));

     vending.main(new String[0] );

     String expectedOutput =  "\u001B[31m(1) Display Vending Machine Items\r\n\u001B[32m(2) Purchase\r\n\u001B[34m(3) exit\r\n\r\n\u001B[33mA1: Potato Crisps: $3.05: Available 5\r\n\u001B[33mA2: Stackers: $1.45: Available 5\r\n\u001B[33mA3: Grain Waves: $2.75: Available 5\r\n\u001B[33mA4: Cloud Popcorn: $3.65: Available 5\r\n\u001B[31mB1: Moonpie: $1.80: Available 5\r\n\u001B[31mB2: Cowtales: $1.50: Available 5\r\n\u001B[31mB3: Wonka Bar: $1.50: Available 5\r\n\u001B[31mB4: Crunchie: $1.75: Available 5\r\n\u001B[34mC1: Cola: $1.25: Available 5\r\n\u001B[34mC2: Dr. Salt: $1.50: Available 5\r\n\u001B[34mC3: Mountain Melter: $1.50: Available 5\r\n\u001B[34mC4: Heavy: $1.50: Available 5\r\n\u001B[35mD1: U-Chews: $0.85: Available 5\r\n\u001B[35mD2: Little League Chew: $0.95: Available 5\r\n\u001B[35mD3: Chiclets: $0.75: Available 5\r\n\u001B[35mD4: Triplemint: $0.75: Available 5\r\n\r\n\u001B[31m(1) Display Vending Machine Items\r\n\u001B[32m(2) Purchase\r\n\u001B[34m(3) exit\r\n\r\n\u001B[32mCurrent Money Provided: $0.0\n\r\n\u001B[31m(1) Feed Money\r\n\u001B[32m(2) Select Product\r\n\u001B[34m(3) Finish Transaction\r\n\r\n\u001B[32mEnter whole dollar amount: \r\n\u001B[32mMoney fed successfully\r\n\r\n\u001B[32mCurrent Money Provided: $5.0\n\r\n\u001B[31m(1) Feed Money\r\n\u001B[32m(2) Select Product\r\n\u001B[34m(3) Finish Transaction\r\n\r\n\u001B[33mA1: Potato Crisps: $3.05: Available 5\r\n\u001B[33mA2: Stackers: $1.45: Available 5\r\n\u001B[33mA3: Grain Waves: $2.75: Available 5\r\n\u001B[33mA4: Cloud Popcorn: $3.65: Available 5\r\n\u001B[31mB1: Moonpie: $1.80: Available 5\r\n\u001B[31mB2: Cowtales: $1.50: Available 5\r\n\u001B[31mB3: Wonka Bar: $1.50: Available 5\r\n\u001B[31mB4: Crunchie: $1.75: Available 5\r\n\u001B[34mC1: Cola: $1.25: Available 5\r\n\u001B[34mC2: Dr. Salt: $1.50: Available 5\r\n\u001B[34mC3: Mountain Melter: $1.50: Available 5\r\n\u001B[34mC4: Heavy: $1.50: Available 5\r\n\u001B[35mD1: U-Chews: $0.85: Available 5\r\n\u001B[35mD2: Little League Chew: $0.95: Available 5\r\n\u001B[35mD3: Chiclets: $0.75: Available 5\r\n\u001B[35mD4: Triplemint: $0.75: Available 5\r\n\n\u001B[32mEnter Product Location: \r\n\u001B[32mDispensing Potato Crisps Crunch Crunch, Yum!\n\r\n\u001B[32mCurrent Money Provided: $1.95\n\r\n\u001B[31m(1) Feed Money\r\n\u001B[32m(2) Select Product\r\n\u001B[34m(3) Finish Transaction\r\n\r\n\u001B[32mChange returned: $ quarters: 7 dimes: 2 nickels: 0\r\n\r\n\u001B[31m(1) Display Vending Machine Items\r\n\u001B[32m(2) Purchase\r\n\u001B[34m(3) exit\r\n\r\nThank you\r\n";

     String out = outContent.toString();
     Assert.assertEquals(expectedOutput, out);
 }
}
