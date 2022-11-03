package com.iscience.tutoring.model;

public class Student {

	private String studentName;
	private String studentPhoneNumber;
	private String courseGrade;
	private int grade;
	private String parentName;
	private String parentPhoneNumber;
	private String schoolName;

	public Student(String student) {
		this.studentName = student.toUpperCase();
	}

	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String student) {
		this.studentName = student.toUpperCase();
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getStudentPhoneNumber() {
		return studentPhoneNumber;
	}

	public void setStudentPhoneNumber(String studentPhoneNumber) {
		this.studentPhoneNumber = studentPhoneNumber;
	}

	public String getCourseGrade() {
		return courseGrade;
	}

	public void setCourseGrade(String letterGrade) {
		this.courseGrade = letterGrade;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentPhoneNumber() {
		return parentPhoneNumber;
	}

	public void setParentPhoneNumber(String parentPhoneNumber) {
		this.parentPhoneNumber = parentPhoneNumber;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object that) {
		if (!(that instanceof Student)) {
			return false;
		}
		Student thatObj = (Student) that;
		if (!this.getStudentName().equalsIgnoreCase(thatObj.getStudentName().toUpperCase())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Student [student=" + studentName + ", StudentPhoneNumber=" + studentPhoneNumber + ", courseGrade="
				+ courseGrade + ", grade=" + grade + ", parentName=" + parentName + ", parentPhoneNumber="
				+ parentPhoneNumber + ", schoolName=" + schoolName + "]";
	}
}
