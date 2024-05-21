package org.example.service;
import lombok.RequiredArgsConstructor;
import org.example.annotation.IT;
import org.example.database.entity.Role;
import org.example.dto.UserCreatEditDto;
import org.example.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserServiceTest {

    private final Integer USER_ID = 1;
    private final Integer COMPANY_ID = 1;
    private final UserService userService;

    @Test
    void findAll() {
        List<UserDto> result = userService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void findById() {
        Optional<UserDto> result = userService.findById(1);
        assertTrue(result.isPresent());
    }

    @Test
    void save() {
        UserCreatEditDto user = new UserCreatEditDto(
                "stalin@gmail.com",
                "Iosif",
                "Opachki",
                LocalDate.of(2000, 11, 11),
                Role.ADMIN,
                COMPANY_ID
        );

        var userDto = userService.create(user);
        assertEquals(userDto.getUsername(), user.getUsername());
        assertEquals(userDto.getFirstname(), user.getFirstname());
        assertEquals(userDto.getLastname(), user.getLastname());
        assertEquals(userDto.getBirthDate(), user.getBirthDate());
        assertEquals(userDto.getRole(), user.getRole());
        assertEquals(userDto.getCompanyId(), user.getCompanyId());
    }
}