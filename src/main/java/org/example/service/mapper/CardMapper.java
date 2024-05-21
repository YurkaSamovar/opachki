package org.example.service.mapper;

import lombok.AllArgsConstructor;
import org.example.database.entity.Card;
import org.example.dto.CardDto;
import org.example.dto.ThemeDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class CardMapper implements Mapper<Card, CardDto> {

    private final ThemeMapper themeMapper;

    @Override
    public CardDto map(Card from) {
        ThemeDto theme = Optional.ofNullable(from.getTheme())
                .map(themeMapper::map)
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
                theme
        );
    }
}
