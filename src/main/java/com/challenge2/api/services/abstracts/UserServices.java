package com.challenge2.api.services.abstracts;

import com.challenge2.api.models.entities.User;
import com.challenge2.api.models.payload.requests.RegisterRequest;

public interface UserServices {
    String save(RegisterRequest registerRequest);
    User findByUsername(String username);
}
