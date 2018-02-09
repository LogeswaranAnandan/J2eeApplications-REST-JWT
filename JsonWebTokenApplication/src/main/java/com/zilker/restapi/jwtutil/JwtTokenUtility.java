package com.zilker.restapi.jwtutil;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;

public class JwtTokenUtility {

	public String buildJwt(String subject) {
		JwtClaims claims = new JwtClaims();
		claims.setSubject(subject);

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
			System.out.println("Problem occurred while serialization");
		}

		System.out.println("Claim:\n" + claims);
		System.out.println("JWS:\n" + jws);
		System.out.println("JWT:\n" + jwt);

		return jwt;
	}

}
