package com.iscience.tutoring.sessions;

import java.io.IOException;
import java.util.Scanner;

import com.iscience.tutoring.model.Tutor;
import com.iscience.tutoring.store.TutorStore;

public class TutorOperations {

	public void displayChoices() throws InterruptedException, IOException {
		TutorStore store = new TutorStore();
		Scanner in = new Scanner(System.in);
		boolean trueOrFalse = true;

		do {
			new ProcessBuilder("clear", "/c", "cls").inheritIO().start().waitFor();
			System.out.println(
					"What would you like to do?\n1. Create Tutor\n2. Update Tutor Phone Number\n3. Delete Tutor\n4. Go Back");
			int choice = in.nextInt();

			switch (choice) {

			case 1:
				System.out.println("What is the name of the tutor?");
				in.nextLine();
				String name = in.nextLine();

				Tutor t1 = new Tutor(name);

				System.out.println("What is the phone number of the tutor?");
				String phoneNumber = in.nextLine();
				t1.setPhoneNumber(phoneNumber);

				store.createTutor(t1);
				System.out.println("Tutor created Successfully.");
				break;

			case 2:
				System.out.println("Whose phone number do you want to change? Tutor name please.");
				in.nextLine();
				String tutorName = in.nextLine();
				System.out.println("What is the new phone number:");
				String newPhoneNumber = in.nextLine();
				store.updateTutorPhoneNumber(tutorName, newPhoneNumber);
				System.out.println("Tutor updated Successfully.");
				break;

			case 3:
				System.out.println("Who got fired?");
				in.nextLine();
				String firedTutor = in.nextLine();
				store.deleteTutor(firedTutor);
				System.out.println("Tutor removed Successfully.");
				break;

			case 4:
				trueOrFalse = false;
				return;

			default:
				System.out.println("invalid answer");
			}
			try {
				System.out.println("\n\n\n\n");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (trueOrFalse);
		in.close();
	}

}
