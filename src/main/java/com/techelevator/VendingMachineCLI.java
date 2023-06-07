package com.techelevator;

import com.techelevator.util.Candy;
import com.techelevator.util.ConsoleUtility;
import com.techelevator.util.Gum;
import com.techelevator.util.Product;

import java.util.InputMismatchException;
import java.util.Scanner;

public class VendingMachineCLI {

	// Use this for keyboard input - it is initialized in the constructor
	private Scanner userInput;

	public VendingMachineCLI(Scanner userInput) {
		this.userInput = userInput;
	}

	public void run() {

		int choice = getChoice();
		Product mnm = new Candy();
		System.out.println(mnm.getMessage());



	}

	public int getChoice(){
		try {
			String ansiGreen = ConsoleUtility.ANSI_GREEN;
			String ansiBlue = ConsoleUtility.ANSI_BLUE;
			System.out.println(ansiGreen + "(1) Display Vending Machine Items");
			System.out.println("(2) Purchase");
			System.out.println("(3) exit");
			int choice = Integer.parseInt(userInput.nextLine());

			while (choice != 1 && choice != 2 && choice != 3) {
				System.out.println(ansiGreen + "(1) Display Vending Machine Items");
				System.out.println("(2) Purchase");
				System.out.println("(3) exit");
				choice = Integer.parseInt(userInput.nextLine());
			}
			return choice;
		} catch (NumberFormatException e){
			System.out.println("Please input a number");
		}
		return 0;

	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		VendingMachineCLI cli = new VendingMachineCLI(input);
		cli.run();
	}
}
