package hn.test.store.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hn.test.store.configuration.MyPropertiesConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtImpl {

	public static String JWT_SECRET_KEY = "";
	public static long JWT_TOKEN_VALIDITY = 0L;

	@Autowired
	public JwtImpl(MyPropertiesConfig myPropertiesConfig) {
		JWT_SECRET_KEY = myPropertiesConfig.getKeyJwt();
		JWT_TOKEN_VALIDITY = myPropertiesConfig.getTokenDuration();
	}

	public Claims getAllClaimsFromToken(String token) {

		token = token.replace("Bearer ", "");
		final var key = Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}

	public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
		final var claims = this.getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Date getExpirationDateFromToken(String token) {
		return this.getClaimsFromToken(token, Claims::getExpiration);
	}

	public Boolean isTokenExpired(String token) {
		final var expirationDate = this.getExpirationDateFromToken(token);
		return expirationDate.before(new Date());
	}

	public String generateToken(Long customerId, String customerName) {
		final Map<String, Object> claims = new HashMap<>();
		claims.put("customerId", customerId);
		claims.put("customerName", customerName);
		return this.getToken(claims, customerName);
	}

	public String getToken(Map<String, Object> extraClaims, String subject) {
		final var key = Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
		return Jwts.builder().setClaims(extraClaims).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(key, SignatureAlgorithm.HS256).compact();
	}
	
}
