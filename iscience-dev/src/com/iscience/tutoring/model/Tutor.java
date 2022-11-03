package com.iscience.tutoring.model;

public class Tutor {

	private String tutorName;
	private String phoneNumber;

	public Tutor(String tutor) {
		this.tutorName = tutor.toUpperCase();
	}

	public void setTutorName(String name) {
		this.tutorName = name.toUpperCase();
	}

	public String getTutorName() {
		return tutorName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String toString() {
		return "Tutor [tutor=" + tutorName + ", phoneNumber=" + phoneNumber + "]";
	}
}
