package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.example.database.entity.Role;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Value
public class UserDto {

    Integer id;
    String username;
    String firstname;
    String lastname;
    LocalDate birthDate;
    Role role;
    CompanyDto companyId;
}
