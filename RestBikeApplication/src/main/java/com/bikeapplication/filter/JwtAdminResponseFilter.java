package com.bikeapplication.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import com.bikeapplication.annotation.Admin;
import com.bikeapplication.utility.JwtTokenGenerator;

@Priority(Priorities.AUTHENTICATION)
@Provider
@Admin
public class JwtAdminResponseFilter implements ContainerResponseFilter {

	Logger logger = Logger.getLogger(JwtCustomerResponseFilter.class.getName());

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		logger.info("Admin response filter invoked...");
		JwtTokenGenerator jwtTokenUtility = new JwtTokenGenerator();
		if (requestContext.getProperty("auth-failed") != null) {
			boolean failed = (Boolean) requestContext.getProperty("auth-failed");
			if (failed) {
				logger.info("JWT auth failed. No need to return JWT token");
				return;
			}
		}
		logger.info("jwt is added in response");
		String jwtData = requestContext.getSecurityContext().getUserPrincipal().getName();
		int userId = Integer.parseInt(jwtData.split(" ")[0].toString());
		String subject = jwtData.split(" ")[1];
		String userRole = jwtData.split(" ")[2];
		List<Object> jwt = new ArrayList<>();
		jwt.add(jwtTokenUtility.generateJwt(userId, subject, userRole));
		responseContext.getHeaders().put("jwt", jwt);
	}

}
