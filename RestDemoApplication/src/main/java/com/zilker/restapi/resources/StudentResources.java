package com.zilker.restapi.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zilker.restapi.bean.StudentBean;
import com.zilker.restapi.repository.StudentRepository;

@Path("students")
public class StudentResources {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<StudentBean> getStudents() {
		System.out.println("Inside get");
		StudentRepository repository = new StudentRepository();
		return repository.getStudentdetails();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public StudentBean insertStudentDetail(StudentBean bean) {
		System.out.println("Resource called");
		StudentRepository repository = new StudentRepository();
		repository.insertStudentDetail(bean);
		System.out.println("Inside resources");
		return bean;
	}
}
