package org.example.service.mapper;

import lombok.RequiredArgsConstructor;
import org.example.database.entity.User;
import org.example.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserDto> {

    @Override
    public UserDto map(User from) {

        return new UserDto(
                from.getId(),
                from.getMail(),
                from.getUsername(),
                from.getPassword(),
                from.getFirstname(),
                from.getLastname(),
                from.getBirthDate(),
                from.getRole(),
                from.getAvatar()
        );


    }
}
