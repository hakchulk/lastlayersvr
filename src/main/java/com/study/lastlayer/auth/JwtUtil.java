package com.study.lastlayer.auth;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
	private final SecretKey secretKey;

	public JwtUtil(String jwtSecret) {
		this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}

	public Claims validateToken(String token) {
		return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
	}

	public Long extractMemberId(Claims claims) {
		return claims.get("memberId", Long.class);
	}

	@Value("${jwt.expiration}")
	private long expirationTime;

	// --- JWT 발행 (이 부분을 추가하세요) ---
	public String createToken(String email, Long memberId) {
		Claims claims = Jwts.claims().setSubject(email);
		claims.put("memberId", memberId); // Long형 ID 저장

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expirationTime * 60 * 1000L);

		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(secretKey, SignatureAlgorithm.HS256).compact();
	}
}
