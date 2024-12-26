package com.challenge2.api.services.abstracts;

import com.challenge2.api.models.entities.Role;

public interface RoleServices {
    Role getOrCreateRole (String roleName);
}
