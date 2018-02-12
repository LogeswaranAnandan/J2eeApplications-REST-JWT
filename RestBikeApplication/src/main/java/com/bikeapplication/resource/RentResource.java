package com.bikeapplication.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bikeapplication.annotation.Admin;
import com.bikeapplication.annotation.Customer;
import com.bikeapplication.bean.ApplicationBeanClass;
import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.delegate.AdminDelegate;
import com.bikeapplication.delegate.CustomerDelegate;

@Path("rented")
public class RentResource {
	CustomerDelegate customerDelegate = new CustomerDelegate();
	AdminDelegate adminDelegate = new AdminDelegate();
	@GET
	@Path("user/{userId}")
	@Customer
	@Produces(MediaType.APPLICATION_JSON)
	public List<ApplicationBeanClass> viewUserRentedBikes(@PathParam("userId") int userId) {
		return customerDelegate.viewUserRentedBikes(userId);
	}
	
	@GET
	@Path("history/{userId}")
	@Customer
	@Produces(MediaType.APPLICATION_JSON)
	public List<ApplicationBeanClass> viewRentHistory(@PathParam("userId") int userId) {
		return customerDelegate.viewRentHistory(userId);
	}
	
	@GET
	@Path("allbikes")
	@Admin
	@Produces(MediaType.APPLICATION_JSON)
	public List<RentBeanClass> viewAllRentedBikes() {
		return adminDelegate.viewAllRentedBikes();
	}
}
