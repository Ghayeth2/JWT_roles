package com.challenge2.api.services.impls;

import com.challenge2.api.models.entities.Role;
import com.challenge2.api.models.entities.User;
import com.challenge2.api.models.payload.requests.RegisterRequest;
import com.challenge2.api.repos.UserRepository;
import com.challenge2.api.services.abstracts.RoleServices;
import com.challenge2.api.services.abstracts.UserServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {
    private final PasswordEncoder passwordEncoder;
    private final RoleServices roleServices;
    private final UserRepository userRepository;
    @Override
    public String save(RegisterRequest registerRequest) {
        User user = User.builder()
                .fullName(registerRequest.getFullName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
//        if (userRepository.count() == 0){
//            user.setRoles(List.of(roleServices.getOrCreateRole("ROLE_ADMIN")));
//        } else {
//            user.setRoles(List.of(roleServices.getOrCreateRole("ROLE_USER")));
//        }

        user.setRoles(List.of(roleServices.getOrCreateRole("ROLE_ADMIN")));
        userRepository.save(user);
//        userRepository.findByEmail(registerRequest.getEmail())
//                        .get().getRoles().forEach(role -> {
//                            System.out.println(role.getName());
//                });
        log.info("Saved user: "+user.getFullName()+ " "
        + user.getEmail()+ " "+ user.getPassword());
        return "User registered successfully";
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow();
    }
}
