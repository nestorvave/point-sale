package com.accio.point_sale.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private long expiration;

	private SecretKey key;

	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	public String generateToken(String username) {
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(key)
				.compact();
	}

	public String getUserFromToken(String token) {
		return Jwts.parser()
				.verifyWith(key).build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}

	public boolean validateJwtToken(String token) {
		try {
			Jwts.parser()
					.verifyWith(key).build()
					.parseSignedClaims(token);
			return true;
		} catch (Exception e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		}
		return false;
	}
}
