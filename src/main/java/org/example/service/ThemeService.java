package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.database.repository.ThemeRepository;
import org.example.dto.ThemeDto;
import org.example.service.mapper.ThemeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;

    public List<ThemeDto> findAll() {
        return themeRepository.findAll().stream()
                .map(themeMapper::map)
                .toList();
    }
}
