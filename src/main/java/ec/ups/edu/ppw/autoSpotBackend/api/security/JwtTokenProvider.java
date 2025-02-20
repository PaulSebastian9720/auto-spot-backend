package ec.ups.edu.ppw.autoSpotBackend.api.security;

import ec.ups.edu.ppw.autoSpotBackend.api.config.JwtSignedKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Date;

@ApplicationScoped
public class JwtTokenProvider {

    @Inject
    private JwtSignedKey jwtSignedKey;


    public String createToken( String mail, String username,String role) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + this.jwtSignedKey.getValidityInMilliseconds());

        return Jwts.builder()
                .setSubject(mail)
                .claim("username",username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, jwtSignedKey.getSignedKey())
                .compact();
    }

    public String getMailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.jwtSignedKey.getSignedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.jwtSignedKey.getSignedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class);
    }



    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(this.jwtSignedKey.getSignedKey())
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;        }
    }

    public boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(this.jwtSignedKey.getSignedKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return true;
        }
    }

}
