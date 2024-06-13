package org.example.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String mail;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Value("USER")
    private Role role;

    @Value("opachki.png")
    private String avatar;
}
