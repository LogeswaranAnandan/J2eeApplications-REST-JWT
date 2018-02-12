package com.bikeapplication.utility;

import java.util.Date;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.keys.HmacKey;

public class JwtTokenGenerator {

	public String generateJwt(long userId,String subject, String userRole) {
		NumericDate issuedAt = NumericDate.now();
		JwtClaims claims = new JwtClaims();
		claims.setSubject(subject);
		claims.setIssuedAt(issuedAt);
		claims.setExpirationTimeMinutesInTheFuture(60);
		claims.setClaim("role", userRole);
		claims.setClaim("userid", userId);

		String secret = "secret";
		String jwt = null;
		JsonWebSignature jws = new JsonWebSignature();
		try {
			jws.setPayload(claims.toJson());
			jws.setHeader("typ", "JWT");
			jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
			jws.setKey(new HmacKey(secret.getBytes("UTF-8")));
			jws.setDoKeyValidation(false);
			jwt = jws.getCompactSerialization();
		} catch (Exception e) {
			System.out.println("Problem occurred while generating JWT token");
		}
		return jwt;
	}

}
