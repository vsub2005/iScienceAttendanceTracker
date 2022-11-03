package com.iscience.tutoring.store;

import static com.mongodb.client.model.Filters.eq;

import com.iscience.tutoring.model.Student;
import com.mongodb.client.MongoCollection;

public class StudentStore extends MongoStore {

	private MongoCollection<Student> studentsCollection;

	public StudentStore() {
		super();
		studentsCollection = dbClient.getDatabase("iscience").getCollection("students", Student.class);
	}

	public boolean createStudent(Student newStudent) {
		if (findStudent(newStudent.getStudentName()) != null) {
			System.out.println("Student " + newStudent.getStudentName()
					+ " is already present. Please use update Student option.");
			return false;
		}
		studentsCollection.insertOne(newStudent);
		return true;
	}

	public Student findStudent(String fullName) {
		return studentsCollection.find(eq("studentName", fullName.toUpperCase())).first();
	}

	public void updateStudent(Student updated) {
		studentsCollection.findOneAndReplace(eq("studentName", updated.getStudentName()), updated);
	}

	public boolean deleteStudent(String fullName) {
		if (findStudent(fullName.toUpperCase()) == null) {
			System.out.println("Student " + fullName + " is not present. Nothing to delete");
			return false;
		}
		studentsCollection.findOneAndDelete(eq("studentName", fullName.toUpperCase()));
		return true;
	}

	public boolean updateStudentPhoneNumber(String studentName, String newNumber) {
		Student current = findStudent(studentName);
		if (current == null) {
			System.out.println("Student " + studentName + " is not present. Nothing to update");
			return false;
		}
		current.setStudentPhoneNumber(newNumber);
		updateStudent(current);
		return true;
	}

	public boolean updateParentPhoneNumber(String studentName, String newNumber) {
		Student current = findStudent(studentName);
		if (current == null) {
			System.out.println("Student " + studentName + " is not present. Nothing to update");
			return false;
		}
		current.setParentPhoneNumber(newNumber);
		updateStudent(current);
		return true;
	}

	public boolean updateStudentGrade(String studentName, int newNumber) {
		Student current = findStudent(studentName);
		if (current == null) {
			System.out.println("Student " + studentName + " is not present. Nothing to update");
			return false;
		}
		current.setGrade(newNumber);
		updateStudent(current);
		return true;
	}

	public boolean updateStudentCourseGrade(String studentName, String newNumber) {
		Student current = findStudent(studentName);
		if (current == null) {
			System.out.println("Student " + studentName + " is not present. Nothing to update");
			return false;
		}
		current.setCourseGrade(newNumber);
		updateStudent(current);
		return true;
	}

	public static void main(String[] args) {
		StudentStore store = new StudentStore();
		Student aparna = new Student("Aparna Lanka");
		aparna.setStudentPhoneNumber("408-368-0754");
		store.createStudent(aparna);
		aparna.setStudentPhoneNumber("408-996-7221");
		store.updateStudent(aparna);
		store.deleteStudent(aparna.getStudentName());
	}
}
