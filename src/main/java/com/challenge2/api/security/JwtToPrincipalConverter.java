package com.challenge2.api.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.challenge2.api.models.entities.Role;
import com.challenge2.api.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Will take DecodedJWT and return UserPrincipal.
 * id, email, roles will get extracted from the
 * decodedJWT.
 */
@Component
@RequiredArgsConstructor
public class JwtToPrincipalConverter {
    private final UserRepository userRepository;

    public UserPrincipal jwtToPrincipal(DecodedJWT decodedJWT) {
        var authorities = getAuthorities(decodedJWT);
       userRepository.findByEmail(decodedJWT.getClaim("email").asString())
               .get().getRoles().forEach(role -> {
                   System.out.println("Role: " + role.getName());
               });
        System.out.println("User authorities: " + authorities);
        return UserPrincipal.builder()
                .id(Long.valueOf(decodedJWT.getSubject()))
                .email(decodedJWT.getClaim("email").asString())
                .authorities(authorities)
                .build();
    }

    private List<SimpleGrantedAuthority> getAuthorities(DecodedJWT decodedJWT) {
        var claim = decodedJWT.getClaim("roles");
        if (claim.isNull() || claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
