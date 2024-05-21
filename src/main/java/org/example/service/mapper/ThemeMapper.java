package org.example.service.mapper;

import org.example.database.entity.Theme;
import org.example.dto.ThemeDto;
import org.springframework.stereotype.Component;

@Component
public class ThemeMapper implements Mapper<Theme, ThemeDto> {
    @Override
    public ThemeDto map(Theme from) {
        return new ThemeDto(
                from.getId(),
                from.getName()
        );
    }
}
