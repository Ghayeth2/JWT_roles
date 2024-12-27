package com.challenge2.api.repos;
import com.challenge2.api.models.entities.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class RoleRepoTest {
    @Autowired
    private RoleRepo roleRepo;
    private Role role;
    @BeforeEach
    void setUp() {
        role = Role.builder().name("ROLE_ADMIN").build();
    }

    @Test
    void roleRepo_Save_ReturnsSavedRole() {
        roleRepo.save(role);
        roleRepo.save(role);
        assertThat(role).isNotNull();
    }

    @Test
    void roleRepo_FindByName_ReturnsRole() {
        roleRepo.save(role);
        Role returnedRole = roleRepo.findByName("ROLE_ADMIN").get();
        assertThat(returnedRole.getName()).isEqualTo(role.getName());
    }

}