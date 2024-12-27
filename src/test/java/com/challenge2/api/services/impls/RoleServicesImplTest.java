package com.challenge2.api.services.impls;

import com.challenge2.api.models.entities.Role;
import com.challenge2.api.repos.RoleRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RoleServicesImplTest {

    @Mock
    private RoleRepo roleRepo;

    @InjectMocks
    private RoleServicesImpl roleServices;

    @Test
    void roleServices_GetOrCreateRole_GetsOrCreatesRole() {
        Role role = new Role("ROLE_ADMIN");
        Mockito.when(roleRepo.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(role));
        Role res = roleServices.getOrCreateRole("ROLE_ADMIN");
        assertThat(res).isEqualTo(role);
    }
}