package com.zilker.restapi.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.zilker.restapi.jwtutil.JwtTokenUtility;

@Path("/main")
public class MainResource {


	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response mainMethod() {
		JwtTokenUtility jwt = new JwtTokenUtility();
		Response resp = Response.ok("user is authenticated").header("jwt", jwt.buildJwt("customer")).build();
		return resp;
	}

}
