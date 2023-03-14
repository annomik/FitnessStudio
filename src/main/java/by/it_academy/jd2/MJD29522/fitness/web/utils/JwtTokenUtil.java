package by.it_academy.jd2.MJD29522.fitness.web.utils;

import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.entity.UserEntity;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class JwtTokenUtil {

    private static final String jwtSecret = "NDQ1ZjAzNjQtMzViZi00MDRjLTljZjQtNjNjYWIyZTU5ZDYw";
    private static final String jwtIssuer = "ITAcademy";

//    public static String generateAccessToken(UserEntity user) {
//        return generateAccessToken(user.getMail());
////    }
//
//    public static String generateAccessToken(UserDTO user) {
//        return generateAccessToken(user.getMail());
//    }

    public static String generateAccessToken( UserDTO userDTO) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("role", userDTO.getRole());
        map.put("uuid", userDTO.getUuid());
        return  Jwts.builder()
                .setSubject(userDTO.getMail())
                .addClaims(map)
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7))) // 1 week
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public static String generateAccessToken(String mail ) {
        return Jwts.builder()
                .setSubject(mail)
              //  .setPayload(user.getRole().toString())
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7))) // 1 week
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public static String getUserMail(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public static Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public static boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
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
