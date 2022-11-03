package com.iscience.tutoring.sessions;

import java.util.Scanner;

public class IScienceClasses {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		boolean trueOrFalse = true;
		do {
			new ProcessBuilder("clear", "/c", "cls").inheritIO().start().waitFor();
			System.out.println(
					"What do you want to do?\n1. Change tutor information\n2. Change Session Information\n3. Change student information\n4. Change Session Type information\n5. Quit");
			int choice = in.nextInt();

			switch (choice) {
			case 1:
				TutorOperations ops = new TutorOperations();
				ops.displayChoices();
				break;

			case 2:
				SessionOperations ops1 = new SessionOperations();
				ops1.displayChoices();
				break;

			case 3:
				StudentOperations ops2 = new StudentOperations();
				ops2.displayChoices();
				break;
			case 4:
				SessionTypeOperations ops3 = new SessionTypeOperations();
				ops3.displayChoices();
				break;
			case 5:
				trueOrFalse = false;
				break; // quitting

			default:
				System.out.println("Invalid answer. Please enter the number next to the action of your choice(Ex: enter 1 to change tutor information)");
				break;
			}
		} while (trueOrFalse);

		in.close();
	}
}
