package by.it_academy.jd2.MJD29522.fitness.web.utils;

import by.it_academy.jd2.MJD29522.fitness.config.properties.JWTProperty;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenUtil {

    private final JWTProperty property;

    public JwtTokenUtil(JWTProperty property) {
        this.property = property;
    }

    public String generateAccessToken(UserDTO userDTO) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("fio", userDTO.getFio());
        map.put("mail", userDTO.getMail());
        map.put("role", userDTO.getRole());
        map.put("uuid", userDTO.getUuid());
        return  Jwts.builder()
                //.setSubject(userDTO.getMail())
                .setClaims(map)
                .setIssuer(property.getIssuer())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7))) // 1 week
                .signWith(SignatureAlgorithm.HS512, property.getSecret())
                .compact();
    }

    public String getUserMail(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.get("mail", String.class);
    }

    public String getUserRole(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class);
    }

    public String getUserUUDI(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.get("uuid", String.class);
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.get("fio", String.class);
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(property.getSecret()).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            //logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            //logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            //logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            //logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            //logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }
}
