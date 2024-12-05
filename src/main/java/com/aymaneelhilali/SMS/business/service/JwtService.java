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
import java.util.HashMap;
import java.util.Map;

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


    public String generateToken(Long id, String email, String role, String prenom, String nom) {
        // Create a map of claims to include in the JWT payload
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("email", email);
        claims.put("role", role);
        claims.put("prenom", prenom);
        claims.put("nom", nom);

        // Build the JWT with the claims, subject, issued time, and expiration time
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())                 // The time the token was created
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))  // Token expiration time (1 hour from now)
                .signWith(getKey())                      // Sign the token using the secret key
                .compact();                              // Create the JWT string
    }
    // Privé
    // Privé
    // Privé
    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
