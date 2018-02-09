package com.rest.jersey.repository;

import java.util.ArrayList;
import java.util.List;

import com.rest.jersey.beans.StudentBean;

public class StudentRepository {

	List<StudentBean> studentList = new ArrayList<>();
	
	public StudentRepository() {
		StudentBean student1 = new StudentBean();
		StudentBean student2 = new StudentBean();
		student1.setRollNumber(101);
		student1.setStudentName("lokesh");
		student2.setRollNumber(102);
		student2.setStudentName("loki");
		studentList.add(student1);
		studentList.add(student2);
	}
	
	public List<StudentBean> getStudents() {
		return studentList;
	}

	public void insertStudentDetail(StudentBean studentBean) {
		studentList.add(studentBean);
	}

	public StudentBean getStudent(int id) {
		for (StudentBean temp : studentList) {
			if(temp.getRollNumber() == id) {
				return temp;
			}
		}
		return null;
	}
}
