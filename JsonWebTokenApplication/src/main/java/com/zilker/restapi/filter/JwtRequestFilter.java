package com.zilker.restapi.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;

import com.zilker.restapi.namebinding.Secured;

@Priority(Priorities.AUTHENTICATION)
@Provider
@Secured
public class JwtRequestFilter implements ContainerRequestFilter {
	static Logger logger = Logger.getLogger(JwtRequestFilter.class.getName());
	public static final String AUTHENTICATION_HEADER = "Authorization";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.info("Authentication filter invoked");
		String authHeader = requestContext.getHeaderString("Authorization");
		logger.info("Auth header = " + authHeader);

		if (authHeader.startsWith("Bearer")) {
			try {
				System.out.println("JWT being tested:\n" + authHeader.split(" ")[1]);
				final String subject = validate(authHeader.split(" ")[1]);
				if (subject != null) {
					logger.info("subject is found = " + subject);
				} else {
					logger.info("subject is null");
				}
			} catch (Exception e) {
				logger.warning("Problem occurred while validating the token");
				logger.warning("JWT toke is Invalid");
	            requestContext.setProperty("auth-failed", true);
	            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			}
		} else {
			logger.warning("No JWT token is found");
            requestContext.setProperty("auth-failed", true);
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}

	private String validate(String jwt) throws UnsupportedEncodingException, InvalidJwtException {
		String secret = "secret";
		String subject = null;
			JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireSubject()
					.setVerificationKey(new HmacKey(secret.getBytes("UTF-8"))).setRelaxVerificationKeyValidation()
					.build();
			JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
			subject = jwtClaims.getClaimValue("sub").toString();
			logger.info("claims = " + jwtClaims.toString());
		return subject;
	}

}
