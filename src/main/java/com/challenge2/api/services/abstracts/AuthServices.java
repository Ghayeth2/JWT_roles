package com.challenge2.api.services.abstracts;

import com.challenge2.api.models.payload.requests.LoginRequest;
import com.challenge2.api.models.payload.responses.LoginResponse;

public interface AuthServices {
    LoginResponse login(LoginRequest loginRequest);
}
