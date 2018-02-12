package com.bikeapplication.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bikeapplication.bean.UserBeanClass;
import com.bikeapplication.delegate.BikeApplicationDelegate;

@Path("login")
public class LoginResource {
	BikeApplicationDelegate delegate = new BikeApplicationDelegate();
	
	@GET
	@Path("{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response userLogin(@PathParam("username") String userName,
									@PathParam("password") String password) {
		return delegate.validateLogin(userName, password);
	}

}
