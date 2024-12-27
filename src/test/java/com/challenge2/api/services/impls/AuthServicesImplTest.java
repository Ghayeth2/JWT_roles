package com.challenge2.api.services.impls;

import com.challenge2.api.models.entities.User;
import com.challenge2.api.models.payload.requests.LoginRequest;
import com.challenge2.api.security.JwtIssuer;
import com.challenge2.api.security.UserPrincipal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServicesImplTest {

    @Mock
    private JwtIssuer jwtIssuer;

    @Mock
    private Authentication authentication;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthServicesImpl authServices;

    @Test
    void authServices_Login_ReturnsToken() {
        Collection<? extends GrantedAuthority> authorities =
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        UserPrincipal principal = UserPrincipal.builder().email("test@gmail.com")
                .password("pass").id(0L).authorities(authorities).build();
        LoginRequest request = LoginRequest.builder().email("test@gmail.com")
                        .password("password").build();
        when(jwtIssuer.issuer(anyLong(), anyString(), any()))
                .thenReturn("token");
        when(authenticationManager.authenticate(any(
                UsernamePasswordAuthenticationToken.class
        ))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(principal);

        var token = authServices.login(request);
        assertNotNull(token);
    }

}