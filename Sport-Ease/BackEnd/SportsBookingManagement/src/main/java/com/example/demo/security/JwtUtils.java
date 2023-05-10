package com.example.demo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	@Autowired
	private Environment env;
	
	public String generateToken(String userName,String role) {
		Map<String,Object>  info = new HashMap<String, Object>();
		info.put("name", userName);
		info.put("role", role);
		return createToken(info);
	}
	
	public String getTokenUsername(String token) {
		Claims claims = decodeToken(token);
		String userName = claims.get("name", String.class);
		return userName;
	}
	
	public boolean validateToken(String token, UserDetails userDetails) {
		Claims claims = decodeToken(token);
		String role = userDetails.getAuthorities().toArray()[0].toString();
		if(!claims.get("name").equals(userDetails.getUsername()))
			return false;
		if(claims.getExpiration().before(new Date()))
			return false;
		if(!claims.get("role").equals(role))
			return false;
		return true;
	}

	private String createToken(Map<String, Object> info) {
		long curTime = System.currentTimeMillis();
		long expiration = env.getProperty("jwt.token.expiration.millis", Long.class);
		String secret = env.getProperty("jwt.token.secret");
		return Jwts.builder()
				.setSubject(null)
				.setClaims(info)
				.setIssuedAt(new Date(curTime))
				.setExpiration(new Date(curTime + expiration))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	private Claims decodeToken(String token) {
		String secret = env.getProperty("jwt.token.secret");
		return Jwts.parser()
			.setSigningKey(secret)
			.parseClaimsJws(token)
			.getBody();
	}	
}
