package com.rest.jersey.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rest.jersey.beans.StudentBean;
import com.rest.jersey.repository.StudentRepository;

@Path("/students")
public class StudentResource {

	StudentRepository repository = new StudentRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<StudentBean> getStudentDetails() {
		System.out.println("Inside students");
		return repository.getStudents();
	}
	
	@POST
	@Path("/student")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public StudentBean insertStudentDetail(StudentBean studentBean) {
		System.out.println(studentBean);
		repository.insertStudentDetail(studentBean);
		return studentBean;
	}
	
	@GET
	@Path("student/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public StudentBean getStudent(@PathParam("id") int id) {
		System.out.println("Inside id ");
		System.out.println(id);
		return repository.getStudent(id);
	}
}
