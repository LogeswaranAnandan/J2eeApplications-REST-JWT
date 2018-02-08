package com.bikeapplication.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.bikeapplication.bean.BikeBeanClass;
import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.bean.RentCalculatorBeanClass;
import com.bikeapplication.delegate.CustomerDelegate;

@Path("bikes")
public class BikeResource {
	CustomerDelegate delegate = new CustomerDelegate();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BikeBeanClass> viewAllBikes() {
		return delegate.viewAllBikes();
	}
	
	@POST
	@Path("/rent")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String rentBike(RentBeanClass rentBean) {
		System.out.println("Inside rent Bike method");
		return (delegate.rentBike(rentBean) + "");
	}
	
	@POST
	@Path("/return/{registrationNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public RentCalculatorBeanClass returnBike(@PathParam("registrationNumber") String registrationNumber, @QueryParam("bikeid") int bikeId, 
							@QueryParam("userid") int userId) {
		return delegate.returnBike(userId, bikeId, registrationNumber);
	}
}
