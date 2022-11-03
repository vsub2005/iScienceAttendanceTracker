package com.iscience.tutoring.store;

import static com.mongodb.client.model.Filters.eq;

import com.iscience.tutoring.model.Tutor;
import com.mongodb.client.MongoCollection;

public class TutorStore extends MongoStore {

	private MongoCollection<Tutor> tutorsCollection;

	public TutorStore() {
		super();
		tutorsCollection = dbClient.getDatabase("iscience").getCollection("tutors", Tutor.class);
	}

	public boolean createTutor(Tutor newTutor) {
		if (findTutor(newTutor.getTutorName()) != null) {
			System.out.println(
					"Tutor " + newTutor.getTutorName() + " is already present. Please use update Tutor option.");
			return false;
		}
		tutorsCollection.insertOne(newTutor);
		return true;
	}

	public Tutor findTutor(String fullName) {
		return tutorsCollection.find(eq("tutorName", fullName.toUpperCase())).first();
	}

	public void updateTutor(Tutor updated) {
		tutorsCollection.findOneAndReplace(eq("tutorName", updated.getTutorName()), updated);
	}

	public boolean deleteTutor(String fullName) {
		if (findTutor(fullName) == null) {
			System.out.println("Tutor " + fullName + " is not present. Nothing to delete");
			return false;
		}
		tutorsCollection.findOneAndDelete(eq("tutorName", fullName.toUpperCase()));
		return true;
	}

	public boolean updateTutorPhoneNumber(String tutorName, String newNumber) {
		Tutor current = findTutor(tutorName);
		if (current == null) {
			System.out.println("Tutor " + tutorName + " is not present. Nothing to update");
			return false;
		}
		current.setPhoneNumber(newNumber);
		updateTutor(current);
		return true;
	}

	public static void main(String[] args) {
		TutorStore store = new TutorStore();
		Tutor aparna = new Tutor("Aparna Lanka");
		aparna.setPhoneNumber("408-368-0754");
		store.createTutor(aparna);
		aparna.setPhoneNumber("408-996-7221");
		store.updateTutor(aparna);
		store.deleteTutor(aparna.getTutorName());
	}
}
