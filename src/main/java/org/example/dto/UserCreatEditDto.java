package org.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.example.database.entity.Role;

import java.time.LocalDate;

@Value
public class UserCreatEditDto {
    @Email
    String username;

    @NotBlank
    @Size(min = 3, max = 30)
    String firstname;

    @NotNull
    String lastname;

    LocalDate birthDate;

    Role role;

    Integer companyId;
}
