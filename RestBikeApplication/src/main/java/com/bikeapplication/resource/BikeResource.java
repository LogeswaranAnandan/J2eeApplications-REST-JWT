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

import com.bikeapplication.annotation.Admin;
import com.bikeapplication.annotation.Customer;
import com.bikeapplication.bean.BikeBeanClass;
import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.bean.RentCalculatorBeanClass;
import com.bikeapplication.delegate.AdminDelegate;
import com.bikeapplication.delegate.CustomerDelegate;

@Path("bikes")
public class BikeResource {
	CustomerDelegate customerDelegate = new CustomerDelegate();
	AdminDelegate adminDelegate = new AdminDelegate();
	
	@GET
	@Customer
	@Produces(MediaType.APPLICATION_JSON)
	public List<BikeBeanClass> viewAllBikes() {
		return customerDelegate.viewAllBikes();
	}
	
	@POST
	@Path("/rent")
	@Customer
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String rentBike(RentBeanClass rentBean) {
		System.out.println("Inside rent Bike method");
		return (customerDelegate.rentBike(rentBean) + "");
	}
	
	@POST
	@Path("/return/{registrationNumber}")
	@Customer
	@Produces(MediaType.APPLICATION_JSON)
	public RentCalculatorBeanClass returnBike(@PathParam("registrationNumber") String registrationNumber, 
												@QueryParam("bikeid") int bikeId, 
												@QueryParam("userid") int userId) {
		return customerDelegate.returnBike(userId, bikeId, registrationNumber);
	}
	
	@POST
	@Path("/addbike/{bikeid}/{registrationNumber}")
	@Admin
	@Produces(MediaType.TEXT_PLAIN)
	public String addNewRegistrationNumber(@PathParam("bikeid") int bikeId, 
											@PathParam("registrationNumber") String registrationNumber) {
		boolean result = adminDelegate.addNewRegistrationNumber(bikeId, registrationNumber);
		return result + "";
	}
}
