package org.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.example.database.entity.Role;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserCreatEditDto {
    @Email
    @NotBlank
    String mail;

    @NotBlank
    String username;

    @NotBlank
    String password;

    @NotBlank
    @Size(min = 3, max = 30)
    String firstname;

    @NotBlank
    String lastname;

    LocalDate birthDate;
    Role role;
    MultipartFile avatar;
}
