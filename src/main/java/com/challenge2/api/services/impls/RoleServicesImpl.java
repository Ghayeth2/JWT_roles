package com.challenge2.api.services.impls;

import com.challenge2.api.models.entities.Role;
import com.challenge2.api.repos.RoleRepo;
import com.challenge2.api.services.abstracts.RoleServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class RoleServicesImpl implements RoleServices {
    private final RoleRepo roleRepo;
    @Override
    public Role getOrCreateRole(String roleName) {
        Optional<Role> role = roleRepo.findByName(roleName);
        return role.orElseGet(() -> roleRepo.save(new Role(roleName)));
    }
}
