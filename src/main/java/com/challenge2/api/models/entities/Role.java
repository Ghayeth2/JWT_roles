package com.challenge2.api.models.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * If the User - Role relation is chosen as one-to-many:
 * User is the base and roles are allowed to be repeated
 * so in Roles table I can have admin, admin, user, admin..
 * and each of them will have the user_id.
 * However, if the relation with Role:
 * then the users are the one on the many side, not the roles
 * and users will have role_id not the other way around.
 *
 * Finally, if the meant is having Users having 
 */
@Entity @Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "roles")
public class Role {
    public Role(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
}
