package com.example.announcement_service.entity;

import com.example.announcement_service.dictionary.UserRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Accessors(chain = true)
@Table(name = "user", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Enumerated(STRING)
    private UserRole role;

    @Column(name = "password", nullable = false)
    private String password;
}
