package com.zilker.restapi.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.zilker.restapi.jwtutil.JwtTokenUtility;

@Path("/main")
public class MainResource {


	@GET
	@Path("{userName}/{password}")
	public Response mainMethod(@PathParam("userName") String userName, @PathParam("password") String password) {
		JwtTokenUtility jwt = new JwtTokenUtility();
		Response response = null;
		if(userName.equals("lokesh") && password.equals("loki")) {
			response = Response.ok("user is authenticated").header("jwt", jwt.buildJwt(userName)).build();
		} else {
			response = Response.status(Response.Status.UNAUTHORIZED).build();
		}
		return response;
	}

}
