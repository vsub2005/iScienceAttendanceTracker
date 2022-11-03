package com.iscience.tutoring.sessions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.iscience.tutoring.model.Student;
import com.iscience.tutoring.model.Tutor;
import com.iscience.tutoring.model.TutoringSession;
import com.iscience.tutoring.store.StudentStore;
import com.iscience.tutoring.store.TutorStore;
import com.iscience.tutoring.store.TutoringSessionStore;
import com.mongodb.client.FindIterable;

public class SessionOperations {

	private TutoringSessionStore sessionStore = new TutoringSessionStore();
	private TutorStore tutorStore = new TutorStore();
	private StudentStore studentStore = new StudentStore();

	public void displayChoices() throws InterruptedException, IOException {
		Scanner in = new Scanner(System.in);
		boolean trueOrFalse = true;
		Tutor tutor;
		TutoringSession session;
		Student student;

		do {
			new ProcessBuilder("clear", "/c", "cls").inheritIO().start().waitFor();
			System.out.println(
					"What would you like to do?\n1. Create Session\n2. Assign tutor to session\n3. Add student to session\n4. Remove student from session\n5. Delete session\n6. Go Back");
			int choice = in.nextInt();

			switch (choice) {

			case 1:
				System.out.println("What is the subject of the session?");
				in.nextLine();
				String subject = in.nextLine();

				System.out.println("What is the day of the lesson (day of the week)?");
				String day = in.nextLine();

				System.out.println("At what time is the lesson?");
				String time = in.nextLine();

				session = new TutoringSession(subject, day, time);

				System.out.println("Who is the tutor?");
				tutor = tutorStore.findTutor(in.nextLine());
				if (tutor == null) {
					System.out.println("Tutor not found.");
					break;
				}

				System.out.println("How long is the session (in minutes)?");
				session.setSessionLength(in.nextInt());

				System.out.println("What is the cost per student for this session?");
				in.nextLine();
				session.setPrice(in.nextLine());

				sessionStore.createTutoringSession(session);

				System.out.println("Session Created Successfully.");
				break;

			case 2:
				System.out.println("Which tutor should be assigned to a session?");
				in.nextLine();
				tutor = tutorStore.findTutor(in.nextLine());
				if (tutor == null) {
					System.out.println("Tutor not found.");
					break;
				}
				System.out.println("Which session should the tutor be assigned to?");
				session = getSession(in);
				session.setTutor(tutor);
				sessionStore.updateTutoringSession(session);
				System.out.println("Session updated Successfully.");
				break;

			case 3:
				System.out.println("Which student should be assigned to a session?");
				in.nextLine();
				student = studentStore.findStudent(in.nextLine());
				if (student == null) {
					System.out.println("Student not found.");
					break;
				}
				System.out.println("Which session should the student be assigned to?");
				session = getSession(in);
				session.addStudent(student);
				sessionStore.updateTutoringSession(session);
				System.out.println("Session updated Successfully.");
				break;

			case 4:
				System.out.println("Which student should be removed from a session?");
				in.nextLine();
				student = studentStore.findStudent(in.nextLine());
				if (student == null) {
					System.out.println("Student not found.");
					break;
				}
				System.out.println("Which session should the student be assigned to?");
				session = getSession(in);
				session.getStudents().remove(student);
				sessionStore.updateTutoringSession(session);
				System.out.println("Session updated Successfully.");
				break;

			case 5:
				session = getSession(in);
				sessionStore.deleteTutoringSession(session.getSubject(), session.getDay(), session.getTime());
				System.out.println("Session removed Successfully.");
				break;

			case 6:
				trueOrFalse = false;
				return;

			default:
				System.out.println("Please enter a valid answer?");
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

	private TutoringSession getSession(Scanner in) {
		List<TutoringSession> list = new ArrayList<>();
		FindIterable<TutoringSession> completeList = sessionStore.getAllTutoringSessions();
		int index = 0;
		for (TutoringSession aSession : completeList) {
			list.add(aSession);
			System.out.println(
					(index + 1) + ". " + aSession.getSubject() + "-" + aSession.getDay() + "-" + aSession.getTime());
			index++;
		}
		boolean keepLooping = true;
		do {
			System.out.println("Which session ?");
			index = in.nextInt();
			if ((index < 1) || (index > list.size())) {
				System.out.println("Wrong choice. Valid choice 1 to " + list.size());
			} else {
				keepLooping = false;
			}
		} while (keepLooping);
		return list.get(index - 1);
	}
}
