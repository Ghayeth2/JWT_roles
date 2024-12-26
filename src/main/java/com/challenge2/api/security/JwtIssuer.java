package com.challenge2.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.challenge2.api.models.entities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class JwtIssuer {
    private final JwtProperties properties;
    // The list<> for JWT known SDK should not be objects, it isn't supported

    public String issuer(Long id, String email, List<String> roles) {
        System.out.println("Roles to encode with Token: " );
        roles.forEach(role -> {
            System.out.println("Role: " + role);
        });
        return JWT
                .create()
                .withSubject(String.valueOf(id))
                .withClaim("email", email)
                .withClaim("roles", roles)
                .withExpiresAt(Instant.now().plus(Duration.of(1,
                        ChronoUnit.DAYS)))
                .sign(Algorithm.HMAC256(properties.getSecret()));
    }
}
