package cob.com.cobgateway.config;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	@Value("${jwt.security.key}")
	private String jwtKey;

	public String doGenerateToken(String subject) {
		Claims claims = Jwts.claims().setSubject(subject);
		return Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS256, jwtKey).compact();
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token).getBody();
	}

	public String getUsernameFromPublicToken(String publicToken) {
		final Claims claims = getAllClaimsFromToken(publicToken);
		return claims.getSubject();
	}
	
//	public Boolean isExpiredToken(String token) {
//		final Claims claims = getAllClaimsFromToken(token);
//		Date expireDate = claims.getExpiration();
//		if(expireDate.before(new Date(System.currentTimeMillis()))) {
//			return true;
//		}
//		return false;
//	}
	
	public Date refreshExpireDate(String token, Date expiredDate) {
		final Claims claims = getAllClaimsFromToken(token);
		claims.setExpiration(expiredDate);
		return claims.getExpiration();
	}
}