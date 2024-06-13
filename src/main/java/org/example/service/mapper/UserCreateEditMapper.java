package org.example.service.mapper;

import lombok.RequiredArgsConstructor;
import org.example.database.entity.User;
import org.example.dto.UserCreatEditDto;
import org.example.dto.UserDto;
import org.example.service.ImageService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreatEditDto, User> {

    private final ImageService imageService;

    public User map(UserCreatEditDto from, User to) {
        copy(from, to);
        return to;
    }

    @Override
    public User map(UserCreatEditDto from) {
        User user = new User();
        copy(from, user);
        return user;
    }



    private void copy(UserCreatEditDto from, User user) {
        user.setMail(from.getMail());
        user.setUsername(from.getUsername());
        user.setPassword(from.getPassword());
        user.setFirstname(from.getFirstname());
        user.setLastname(from.getLastname());
        user.setBirthDate(from.getBirthDate());
        user.setRole(from.getRole());
        Optional.ofNullable(from.getAvatar())
                .filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image -> user.setAvatar(image.getOriginalFilename()));
    }
}
