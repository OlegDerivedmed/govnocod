package ua.kiev.gossips.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.RandomStringUtils;
import ua.kiev.gossips.entities.GossipUser;
import ua.kiev.gossips.entities.Token;
import ua.kiev.gossips.entities.TokenState;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;

public class TokenGenerator {

    public Token generateToken(GossipUser gossipUser) {
        String randString = RandomStringUtils.random(64, true, true);
        SecretKey secretKey = Keys.hmacShaKeyFor(randString.getBytes());
        String jwt = Jwts.builder()
                .claim("email", gossipUser.getEmail())
                .signWith(secretKey)
                .compact();
        return Token.builder()
                .gossipUser(gossipUser)
                .state(TokenState.ACTIVE)
                .expires(LocalDateTime.now().plusDays(2))
                .value(jwt)
                .build();
    }
}
