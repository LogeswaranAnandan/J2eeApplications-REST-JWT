package com.zilker.restapi.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StudentBean {
	
	private int rollNumber;
	private String studentName;
	
	public int getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	@Override
	public String toString() {
		return "StudentBean [rollNumber=" + rollNumber + ", studentName=" + studentName + "]";
	}
	
}
