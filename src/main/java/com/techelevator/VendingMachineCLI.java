package com.techelevator;

import com.techelevator.util.*;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VendingMachineCLI {
	private VendingMachineMenu vending = new VendingMachineMenu(new File("vendingmachine.csv"));
	private PurchaseProcess purchaseProcess = new PurchaseProcess();

	// Use this for keyboard input - it is initialized in the constructor
	private Scanner userInput;

	public VendingMachineCLI(Scanner userInput) {
		this.userInput = userInput;

	}

	public void run() {

		int choice = getChoice();

		if (choice == 1) {
			vending.displayMenu();
			System.out.println();
			run();
		}
		while (choice == 2) {
			int i = purchaseProcess.printMenu(userInput);


			if (i == 1) {
				purchaseProcess.feedMoney(userInput);

			}
			if (i == 2) {
				purchaseProcess.purchaseMenu(vending, userInput);

			}
			if (i == 3) {
				purchaseProcess.finishTransaction();
				System.out.println();
				run();
				break;
			}


		}
		if (choice == 3) {
			System.out.println("Thank you");

		}


	}

	public int getChoice(){
		try {
			String ansiGreen = ConsoleUtility.ANSI_GREEN;
			String ansiRed = ConsoleUtility.ANSI_RED;
			String ansiBlue = ConsoleUtility.ANSI_BLUE;

			int choice = 0;

			while (choice != 1 && choice != 2 && choice != 3) {
				System.out.println(ansiRed + "(1) Display Vending Machine Items");
				System.out.println(ansiGreen + "(2) Purchase");
				System.out.println(ansiBlue + "(3) exit");
				choice = Integer.parseInt(userInput.nextLine());
				System.out.println();
			}
			return choice;
		} catch (NumberFormatException e){
			System.out.println("\u001B[32m"+ "Please type a number");
			System.out.println();
			run();

		}
		return 0;

	}
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		VendingMachineCLI cli = new VendingMachineCLI(input);
		cli.run();
	}
}
