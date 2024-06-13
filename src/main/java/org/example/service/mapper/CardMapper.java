package org.example.service.mapper;

import lombok.AllArgsConstructor;
import org.example.database.entity.Card;
import org.example.dto.CardDto;
import org.example.dto.ThemeDto;
import org.example.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.function.Predicate;

@AllArgsConstructor
@Component
public class CardMapper implements Mapper<Card, CardDto> {

    private final ThemeMapper themeMapper;
    private final UserMapper userMapper;

    @Override
    public CardDto map(Card from) {
        ThemeDto theme = Optional.ofNullable(from.getTheme())
                .map(themeMapper::map)
                .orElse(null);
        UserDto user = Optional.ofNullable(from.getUser())
                .map(userMapper::map)
                .orElse(null);

        return new CardDto(
                from.getId(),
                from.getImage(),
                from.getTitle(),
                from.getPrix(),
                from.getDescription(),
                from.getDate(),
                from.getAdresse(),
                from.getLink(),
                theme,
                user

        );
    }
}
