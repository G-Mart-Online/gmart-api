package com.gmart.gmart_api.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gmart.gmart_api.dto.UserDto;
import com.gmart.gmart_api.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserAuthenticationProvider {

    private static final long TOKEN_VALIDITY_DURATION = 24 * 60 * 60 * 1000L; // 1 day in milliseconds
    private static final String FIRST_NAME_CLAIM = "firstName";
    private static final String LAST_NAME_CLAIM = "lastName";
    private final UserService userService;
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Autowired
    public UserAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserDto user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + TOKEN_VALIDITY_DURATION);

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim(FIRST_NAME_CLAIM, user.getFirstName())
                .withClaim(LAST_NAME_CLAIM, user.getLastName())
                .withClaim("roles", new ArrayList<>(user.getRoles())) // Add roles to the token
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            JWTVerifier verifier = JWT.require(algorithm)
                    .build();

            DecodedJWT decoded = verifier.verify(token);

            UserDto user = new UserDto.Builder()
                    .username(decoded.getSubject())
                    .firstName(decoded.getClaim(FIRST_NAME_CLAIM).asString())
                    .lastName(decoded.getClaim(LAST_NAME_CLAIM).asString())
                    .roles(new HashSet<>(decoded.getClaim("roles").asList(String.class)))
                    .build();

            // Create authorities based on roles
            Collection<GrantedAuthority> authorities = user.getRoles().stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(user, null, authorities);
        } catch (JWTVerificationException e) {
            throw e;
        }
    }

    public Authentication validateTokenStrongly(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            JWTVerifier verifier = JWT.require(algorithm)
                    .build();

            DecodedJWT decoded = verifier.verify(token);

            UserDto user = userService.findByUsername(decoded.getSubject());

            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        } catch (JWTVerificationException e) {
            throw e;
        }
    }
}
