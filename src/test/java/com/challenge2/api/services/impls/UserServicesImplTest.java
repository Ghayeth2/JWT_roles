package com.challenge2.api.services.impls;

import com.challenge2.api.models.entities.Role;
import com.challenge2.api.models.entities.User;
import com.challenge2.api.models.payload.requests.RegisterRequest;
import com.challenge2.api.repos.UserRepository;
import com.challenge2.api.services.abstracts.RoleServices;
import com.challenge2.api.services.abstracts.UserServices;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Testing service class (Unit Testing)
 */
@ExtendWith(MockitoExtension.class)
class UserServicesImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleServices roleServices;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServicesImpl userServices;

    private User user;
    private RegisterRequest request;

    @BeforeEach
    void setUp() {
        user = User.builder().fullName("ful")
                .email("test@gmail.com")
                .password("pass")
                .build();
        request = RegisterRequest.builder()
                .fullName("ful")
                .password("pass")
                .email("test@gmail.com")
                .build();
    }

    @Test
    void userServices_SaveUser_ReturnsString() {
        Role role = new Role("ROLE_USER");
        when(passwordEncoder.encode(anyString()))
                .thenReturn("password");
        when(roleServices.getOrCreateRole(anyString()))
                .thenReturn(role);
        when(userRepository.save(any()))
                .thenReturn(user);
        String res = userServices.save(request);
        Assertions.assertThat(res)
                .isEqualTo("User registered successfully");
    }

    @Test
    void userServices_FindByUserName_ReturnsUser() {
        when(userRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(user));
        User res = userServices.findByUsername("str");
        Assertions.assertThat(res)
                .isEqualTo(user);
    }
}