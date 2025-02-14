package com.facul.cefet_store.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

        //neZ6fdBwLZ4PDBz1p5L+DW4O1JMTb1pLpEDcygIAdhA=
        public static final String SECRET = "j5Xxe5a8ZPSO4o5OfoY1EAvpfIa1h620YLiXbF31e0Y=";

        public String generateToken(String userName) {
                Map<String, Object> claims = new HashMap<>();
                return createToken(claims, userName);
        }

        private String createToken(Map<String, Object> claims, String userName) {
                return Jwts.builder()
                        .setClaims(claims)
                        .setSubject(userName)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 10000 * 60 * 30))
                        .signWith(getSignKey(), SignatureAlgorithm.HS256)
                        .compact();
        }

        //chave random
        /*
        public String randomSecretKey() {
                SecureRandom secureRandom = new SecureRandom();
                byte[] keyBytes = new byte[32];
                secureRandom.nextBytes(keyBytes);
                return Base64.getEncoder().encodeToString(keyBytes);
        }
        */

        private Key getSignKey() {
                byte[] keybytes = Decoders.BASE64.decode(SECRET);
                return Keys.hmacShaKeyFor(keybytes);
        }

        public String extractUsername(String token) {
                return extractClaim(token, Claims::getSubject);
        }

        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
                final Claims claims = extractAllClaims(token);
                return claimsResolver.apply(claims);
        }

        private Claims extractAllClaims(String token) {
                return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
        }

        private Boolean isTokenExpired(String token) {
                return extractExpiration(token).before(new Date());
        }

        public Date extractExpiration(String token) {
                return extractClaim(token, Claims::getExpiration);
        }

        public Boolean validateToken(String token, UserDetails userDetails) {
                final String username = extractUsername(token);
                return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }
}