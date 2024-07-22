package io.app.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Signature;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
	private static final String SECRET_KEY="3zEYxnei5RPxxCVdg57NTtMbdXk6bnFnOKiw1taxdUC06fRdQybzqGQkV1Yvc8DROAqNOyTQDwKse09PX+gQag==";

	public String extractUsername(String token){
		return extractClaims(token,Claims::getSubject);
	}
	public boolean isTokenValid(String token,UserDetails userDetails){
		String username=extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
	public boolean isTokenExpired(String token){
		return extractExpiration(token).before(new Date());
	}

	public Date extractExpiration(String token){
		return extractClaims(token,Claims::getExpiration);
	}

	private <T> T extractClaims(String token, Function<Claims,T> resolver){
		Claims claims=extractAllClaims(token);
		return resolver.apply(claims);
	}


	private Claims extractAllClaims(String token) {
		Claims claims=null;
		try {
			claims=Jwts.parserBuilder()
					.setSigningKey(getSignInKey())
					.build()
					.parseClaimsJws(token)
					.getBody();
		}catch (SignatureException exception){
			throw new SignatureException("Please Provide Token in Format");
		}
		return claims;
	}

	public String generateToken(UserDetails userDetails){
		return generateToken(new HashMap<>(),userDetails);
	}

	private String generateToken(Map<String,Object> claims, UserDetails userDetails){
			return Jwts.builder()
					.setClaims(claims)
					.setSubject(userDetails.getUsername())
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis()* 24 * 60 * 60 * 1000))
					.signWith(getSignInKey()).compact();
	}

	private SecretKey getSignInKey(){
		byte[] key= Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(key);
	}

}
