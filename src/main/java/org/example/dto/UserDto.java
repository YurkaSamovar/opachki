package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.example.database.entity.Role;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserDto {

    Integer id;
    String mail;
    String username;
    String password;
    String firstname;
    String lastname;
    LocalDate birthDate;
    Role role;
    String avatar;
}
