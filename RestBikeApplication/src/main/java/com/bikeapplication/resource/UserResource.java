package com.bikeapplication.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bikeapplication.annotation.Admin;
import com.bikeapplication.bean.UserBeanClass;
import com.bikeapplication.delegate.AdminDelegate;

@Path("users")
public class UserResource {
	
	AdminDelegate adminDelegate = new AdminDelegate();

	@GET
	@Admin
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserBeanClass> viewAllUsers() {
		return adminDelegate.viewAllUsers();
	}
}
