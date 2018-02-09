package com.bikeapplication.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bikeapplication.bean.ApplicationBeanClass;
import com.bikeapplication.delegate.CustomerDelegate;

@Path("rented")
public class RentResource {
	CustomerDelegate delegate = new CustomerDelegate();

	@GET
	@Path("user/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ApplicationBeanClass> viewUserRentedBikes(@PathParam("userId") int userId) {
		return delegate.viewUserRentedBikes(userId);
	}
	
	@GET
	@Path("history/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ApplicationBeanClass> viewRentHistory(@PathParam("userId") int userId) {
		return delegate.viewRentHistory(userId);
	}
}
