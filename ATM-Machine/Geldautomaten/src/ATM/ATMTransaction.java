package ATM;

/**
 * Copyright by Joshua Ragusa
 */
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.text.StyledEditorKit;

public class ATMTransaction {

	public static void main(String args[]) {
		
		int balance = 5000, withdraw, deposit;
		Scanner s = new Scanner(System.in);
		
		while (true) {
			System.out.println("Automated Teller Machine");
			System.out.println("Choose number 1 for Withdraw");
			System.out.println("Choose number 2 for Deposit");
			System.out.println("Choose number 3 for Check Balance");
			System.out.println("Exit");
			System.out.println("Choose the operation you want to operate");
			int n = s.nextInt();
			
			switch (n) {
			case 1:
				System.out.print("Enter money to be withdraw: ");
				withdraw = s.nextInt();
				if (balance >= withdraw) {
					balance = balance - withdraw;
					System.out.println("Please save your Money");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				} else {
					System.out.println("Not enough money saved");
				}
				System.out.println("");
				break;

			case 2:
				System.out.println("Enter money to be deposited: ");
				deposit = s.nextInt();
				balance = balance + deposit;
				System.out.println("Your Money has been succesfully depsited");
				System.out.println("");
				break;

			case 3:
				System.out.println("Your balance is: " + balance);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				break;

			case 4:
				System.exit(0);
			}

		}
	}
}
