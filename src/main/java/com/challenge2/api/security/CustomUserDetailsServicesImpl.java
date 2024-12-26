package com.challenge2.api.security;

import com.challenge2.api.models.entities.User;
import com.challenge2.api.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomUserDetailsServicesImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow();
        System.out.println("Login attempting user's roles:");
        user.getRoles().forEach(System.out::println);
        log.info("user from DB: {}", user);
        return UserPrincipal.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .id(user.getId())
                .authorities(user
                        .getRoles()
                        .stream()
                        .map(role ->
                                new SimpleGrantedAuthority(role.getName()))
                        .toList()
                ).build();

    }
}
