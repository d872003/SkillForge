package website.skillforge.be.util;

import io.jsonwebtoken.*;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import website.skillforge.be.entities.Account;

import java.util.Date;


@Component
public class TokenHandler {
    private final String SECRET_KEY = "skillforge";
    //    1s => 1000ms
//    private final UUID EXPIRATION = 1 * 60 * 1000;
    private final long EXPIRATION = 1 * 24 * 60 * 60 * 1000;

    // create token (encode)
    public String generateToken(Account account) {
        Date now = new Date(); // get current time
        Date expirationDate = new Date(now.getTime() + EXPIRATION);

        String token = Jwts.builder()
                .setSubject(account.getUsername())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        return token;
    }

    // validate token
    // get info from token (decode)
    public String getInfoByToken(String token) throws ExpiredJwtException, MalformedJwtException {
        String username;
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        username = claims.getSubject();
        // xuống đc đây => token đúng
        return username;
    }
}
