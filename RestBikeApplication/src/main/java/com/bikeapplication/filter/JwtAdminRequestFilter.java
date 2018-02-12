package com.bikeapplication.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;

import com.bikeapplication.annotation.Admin;
import com.bikeapplication.customexception.UnauthorizedAccessException;

@Priority(Priorities.AUTHENTICATION)
@Provider
@Admin
public class JwtAdminRequestFilter implements ContainerRequestFilter {

	static Logger logger = Logger.getLogger(JwtCustomerRequestFilter.class.getName());
	public static final String AUTHENTICATION_HEADER = "Authorization";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.info("AdminAuthentication filter invoked");
		String authHeader = requestContext.getHeaderString("Authorization");
		logger.info("Admin Auth header = " + authHeader);

		try {
			if (authHeader.startsWith("Bearer")) {
				String returnedData = validate(authHeader.split(" ")[1]);
				final String userId = returnedData.split(" ")[0];
				final String subject = returnedData.split(" ")[1];
				final String role = returnedData.split(" ")[2];
				if (subject != null) {
					requestContext.setSecurityContext(new SecurityContext() {

						@Override
						public boolean isUserInRole(String role) {
							return false;
						}

						@Override
						public boolean isSecure() {
							return false;
						}

						@Override
						public Principal getUserPrincipal() {
							return new Principal() {

								@Override
								public String getName() {
									return userId + " " + subject + " " + role;
								}
							};
						}

						@Override
						public String getAuthenticationScheme() {
							// TODO Auto-generated method stub
							return null;
						}

					});
				}
			} else {
				logger.warning("No JWT token is found");
				requestContext.setProperty("auth-failed", true);
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			}
		} catch (Exception e) {
			logger.warning("Problem occurred while validating the token");
			logger.warning("JWT toke is Invalid");
			requestContext.setProperty("auth-failed", true);
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}

	private String validate(String jwt)
			throws UnsupportedEncodingException, InvalidJwtException, UnauthorizedAccessException {
		String secret = "secret";
		String subject = null;
		String role = null;
		String userId = null;
		JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireSubject()
				.setVerificationKey(new HmacKey(secret.getBytes("UTF-8"))).setRelaxVerificationKeyValidation().build();
		JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
		subject = jwtClaims.getClaimValue("sub").toString();
		role = jwtClaims.getClaimValue("role").toString();
		userId = jwtClaims.getClaimValue("userid").toString();
		if (!role.equalsIgnoreCase("admin")) {
			System.out.println("role = " + role);
			throw new UnauthorizedAccessException();
		}
		return userId + " " + subject + " " + role;
	}

}
