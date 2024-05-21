package org.example.service;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.database.entity.Card;
import org.example.database.repository.CardRepository;
import org.example.dto.CardCreatEditDto;
import org.example.dto.CardDto;
import org.example.dto.CardFilter;
import org.example.service.mapper.CardCreateEditMapper;
import org.example.service.mapper.CardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final CardCreateEditMapper cardCreateEditMapper;
    private final ImageService imageService;

    public List<CardDto> findAll(CardFilter filter) {
        return cardRepository.findAllByFilter(filter).stream()
                .map(cardMapper::map)
                .toList();
    }

    public List<CardDto> findAll() {
        return cardRepository.findAll().stream()
                .map(cardMapper::map)
                .toList();
    }

    @Transactional
    public CardDto create(CardCreatEditDto cardCreatEditDto) {
        return Optional.of(cardCreatEditDto)
                .map(dto -> {
                    uploadImage(dto.getImage());
                    return cardCreateEditMapper.map(dto);
                })
                .map(cardRepository::save)
                .map(cardMapper::map)
                .orElseThrow();
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if(!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }
}
