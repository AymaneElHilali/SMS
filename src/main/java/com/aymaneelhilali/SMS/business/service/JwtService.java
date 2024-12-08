package com.aymaneelhilali.SMS.business.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import io.jsonwebtoken.Claims;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.function.Function;

@Service
public class JwtService {

    //The secret Key that we will use for the Signature
    private String secretKey;

    public JwtService() {
        //generate the secret key for securité
        // add the try and the catch because of NoSuchAlgorithmException
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public String generateToken(String email) {
        // Create a map of claims to include in the JWT payload
        Map<String, Object> claims = new HashMap<>();
//        claims.put("id", id);
        claims.put("email", email);
//        claims.put("role", role);
//        claims.put("prenom", prenom);
//        claims.put("nom", nom);

        // Build the JWT with the claims, subject, issued time, and expiration time
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())                 // The time the token was created
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))  // Token expiration time (1 hour from now)
                .signWith(getKey())                      // Sign the token using the secret key
                .compact();                              // Create the JWT string
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    //get the Email from the Subject (.setSubject(email))
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())  // Ensure you use the correct signing key
                .build()
                .parseClaimsJws(token)  // This will parse the JWT and return the claims
                .getBody();  // Extracts the body of the claims
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
