package com.heuy_kt.AdminApp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.hibernate.annotations.DialectOverride;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    //generate random key from key generators online
    private static final String SECRET_KEY = "7gUjfm3DqY2Ovp8f2kQjwB5jsd0wkgHlpcFHvyp9AwqALOWhAVg7Z+FtP0ryP7q7";
    public String extractAdminUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userEmail = extractAdminUsername(token);
        return (userDetails.getUsername().equals(userEmail) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        Claims claim = extractAllClaims(token);
        return claimsResolver.apply(claim);
    }
    private Claims extractAllClaims(String token){
        return Jwts.
                parser().
                setSigningKey(getSignInKey()).
                build().
                parseClaimsJws(token).
                getBody();

    }

    //no idea the methods here, check later.
    private Key getSignInKey() {
        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
        //decoding with the signature from the jwt website
        return Keys.hmacShaKeyFor(keyByte);
    }
}
