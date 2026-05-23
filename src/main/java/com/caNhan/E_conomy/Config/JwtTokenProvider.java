package com.caNhan.E_conomy.Config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String JWT_SECRET = "chuyen_gi_kho_co_giai_phap_cong_nghe_thong_tin_2026";

    private final long JWT_EXPIRATION = 900000L;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
    }


    public String generateToken(String username, String fullName, Long userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);


        return Jwts.builder()
                .setSubject(username)
                .claim("userId",userId)
                .claim("fullName",fullName)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public boolean validateToken(String token) {
        try {

            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);


            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Token không hợp lệ (Sai cấu trúc)");
        } catch (ExpiredJwtException ex) {
            System.out.println("Token đã hết hạn sử dụng");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Token không được hỗ trợ");
        } catch (IllegalArgumentException ex) {
            System.out.println("Chuỗi Claims trong JWT trống");
        } catch (SignatureException ex) {
            System.out.println("Chữ ký không trùng khớp! Token đã bị sửa đổi trái phép!");
        }
        return false;
    }


    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("userId", Long.class);
    }

    public String getFullNameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("fullName", String.class);
    }
}
