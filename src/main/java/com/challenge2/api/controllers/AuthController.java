package com.challenge2.api.controllers;

import com.challenge2.api.models.payload.requests.LoginRequest;
import com.challenge2.api.models.payload.requests.RegisterRequest;
import com.challenge2.api.services.abstracts.AuthServices;
import com.challenge2.api.services.abstracts.UserServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserServices userServices;
    private final AuthServices authServices;

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@Valid @RequestBody RegisterRequest request) {
        return new ResponseEntity<>(userServices.save(request),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        return new ResponseEntity<>(authServices.login(request),
                HttpStatus.OK);
    }
}
