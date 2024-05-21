package org.example.service.mapper;

import lombok.RequiredArgsConstructor;
import org.example.database.entity.User;
import org.example.dto.CompanyDto;
import org.example.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserDto> {

    private final CompanyMapper companyMapper;

    @Override
    public UserDto map(User from) {
        CompanyDto company = Optional.ofNullable(from.getCompany())
                .map(companyMapper::map)
                .orElse(null);

        return new UserDto(
                from.getId(),
                from.getUsername(),
                from.getFirstname(),
                from.getLastname(),
                from.getBirthDate(),
                from.getRole(),
                company
        );


    }
}
