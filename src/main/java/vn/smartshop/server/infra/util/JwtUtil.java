package vn.smartshop.server.infra.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.smartshop.server.model.dto.request.RefreshToken;
import vn.smartshop.server.model.entity.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class JwtUtil {

    private static String jwtSecret ="duynd";


    private static int jwtExpirationMs = 600000;

    private static int refreshExpirationMs = 21600000;

    public static String generateJwtToken(User user, List<String> permissionLst) {
        Map<String,Object> payload = new HashMap<>();
        payload.put("userName",user.getUserName());
        payload.put("role","role");
        payload.put("permissionLst",permissionLst);
        return Jwts.builder()
                .setSubject(user.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .addClaims(payload)
                .compact();
    }

    public static String generateRefreshToken(String userName) {
        RefreshToken refreshToken = RefreshToken.builder().userName(userName).endDate(new Date(new Date().getTime()+refreshExpirationMs)).build();
        return EncryptUtil.encrypt(JsonUtil.writeValueAsString(refreshToken));
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public static boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

}
