package com.zilker.restapi.filter;

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

import com.zilker.restapi.jwtutil.JwtTokenUtility;
import com.zilker.restapi.namebinding.Secured;

@Priority(Priorities.AUTHENTICATION)
@Provider
@Secured
public class JwtResponseFilter implements ContainerResponseFilter {

	Logger logger = Logger.getLogger(JwtResponseFilter.class.getName());
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		logger.info("response filter invoked...");
		JwtTokenUtility jwtTokenUtility = new JwtTokenUtility();
        if (requestContext.getProperty("auth-failed") != null) {
            boolean failed = (Boolean) requestContext.getProperty("auth-failed");
            if (failed) {
                logger.info("JWT auth failed. No need to return JWT token");
                return;
            }
        }
        logger.info("jwt is added in response");
        List<Object> jwt = new ArrayList<>();
        jwt.add(jwtTokenUtility.buildJwt("customer"));
        responseContext.getHeaders().put("jwt", jwt);
        logger.info("jwt in response = " + jwt.toString());
	}

}
