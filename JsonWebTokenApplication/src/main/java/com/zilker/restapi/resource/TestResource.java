package com.zilker.restapi.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.zilker.restapi.namebinding.Secured;

@Path("test")
public class TestResource {
	
	@GET
	@Produces("text/plain")
	@Secured
	public String testMethod() {
		System.out.println("inside test method");
		return "in test method";
	}

}
