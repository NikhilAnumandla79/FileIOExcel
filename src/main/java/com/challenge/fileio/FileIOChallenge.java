package com.challenge.fileio;

import java.util.Scanner;

public class FileIOChallenge {
	public static void main(String args[]) {

		Scanner sc = new Scanner(System.in);
		byte menu = sc.nextByte();
		sc.nextLine();
		boolean exit =false;
		do {
			switch (menu) {
			case 1:
				new Operations().writeNotepad();
				break;
			case 2:
				System.out.print("Enter name:");
				String name = sc.nextLine();
				new Operations().getExpenses(name);
				break;
			case 3:
				System.out.print("Enter name:");
				String updated = sc.nextLine();
				System.out.println("enter updated expenses :");
				double expenses = sc.nextDouble();
				sc.nextLine();
				new Operations().updateExpenses(updated, expenses);
				break;
			case 4:
				new Operations().creatTotal();
				break;
			case 0:
				exit = true;
				break;
			}

			if (exit)
				break;
			System.out.print("Choose action from menu : ");
			menu = sc.nextByte();
			sc.nextLine();

		} while (!exit);
		System.out.println("Exited!!!");
		sc.close();

		

		

		

		

	}
}
