package com.challenge2.api.services.impls;

import com.challenge2.api.models.entities.Role;
import com.challenge2.api.models.entities.User;
import com.challenge2.api.models.payload.requests.LoginRequest;
import com.challenge2.api.models.payload.responses.LoginResponse;
import com.challenge2.api.security.JwtIssuer;
import com.challenge2.api.security.UserPrincipal;
import com.challenge2.api.services.abstracts.AuthServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServicesImpl implements AuthServices {
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var principal = (UserPrincipal) authentication.getPrincipal();
        System.out.println("Principal's email: " + principal.getEmail());
        System.out.println("Roles from Principal:");
        principal.getAuthorities().forEach(System.out::println);
        List<String> roles = new ArrayList<>();
        principal.getAuthorities()
                        .forEach(
                                authority ->
                                        roles.add(authority.getAuthority())
                        );
        System.out.println("roles in login attempt: " );
        roles.forEach(System.out::println);
        var jwtToken = jwtIssuer.issuer(principal.getId(),
                principal.getEmail(), roles);

        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }
}
