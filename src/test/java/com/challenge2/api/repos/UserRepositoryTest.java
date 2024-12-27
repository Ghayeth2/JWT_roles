package com.challenge2.api.repos;

import com.challenge2.api.models.entities.Role;
import com.challenge2.api.models.entities.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User user;
    @BeforeEach
    void setUp() {
        user = User.builder().fullName("full test")
                .email("test@gmail.com").password("pass")
                .roles(List.of(new Role("ROLE_ADMIN"))).build();
    }

    @Test
    void UserRepo_Save_ReturnsSavedUser() {
        User savedUser = userRepository.save(user);
        Assertions.assertNotNull(savedUser);
    }

    @Test
    void UserRepo_FindByEmail_ReturnsUser() {
        String email = "test@gmail.com";
        userRepository.save(user);
        User foundUser = userRepository.findByEmail(email).get();
        Assertions.assertEquals(email, foundUser.getEmail());
    }

    @Test
    void UserRepo_FindByEmail_ReturnsNull() {
        Optional<User> foundUser = userRepository.findByEmail("test@gmail.com");
        assertThat(foundUser).isEmpty();
    }
}