package com.iscience.tutoring.sessions;

import java.io.IOException;
import java.util.Scanner;

import com.iscience.tutoring.model.SessionType;

public class SessionTypeOperations {
	
	public void displayChoices() throws InterruptedException, IOException
	{
		Scanner in = new Scanner(System.in);
		boolean TrueOrFalse = true;
		
		do
		{
			new ProcessBuilder("clear", "/c", "cls").inheritIO().start().waitFor();
			System.out.println("What would you like to do?\n1. Create Subject\n2. Create Price\n3. Go back");
			int choice = in.nextInt();
			
			switch(choice)
			{
			
			case 1: 
				System.out.println("What is one subject that you teach?");
				in.nextLine();
				String subject = in.nextLine();
				
				SessionType std1 = new SessionType(subject);
				break;
			case 2:
				System.out.println("What is the price of this class?");
				in.nextLine();
				int price = in.nextInt();
				
				//std1.setPrice(price);
				break;
			case 3:
				TrueOrFalse = false;
				return;
			default:
				System.out.println("Invalid entry, please enter the number next to the action of your choice(Ex: enter 1 for subject creation)");
			}
		} while(TrueOrFalse);
		in.close();
	}
}
