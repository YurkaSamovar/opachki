package org.example.service.mapper;

import lombok.RequiredArgsConstructor;
import org.example.database.entity.Card;
import org.example.database.entity.Theme;
import org.example.database.entity.User;
import org.example.database.repository.ThemeRepository;
import org.example.database.repository.UserRepository;
import org.example.dto.CardCreatEditDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class CardCreateEditMapper implements Mapper<CardCreatEditDto, Card> {

    private final ThemeRepository themeRepository;
    private final UserRepository userRepository;

    @Override
    public Card map(CardCreatEditDto from) {
        Card card = new Card();
        return copy(from, card);
    }

    private Card copy(CardCreatEditDto from, Card to) {
        Optional.ofNullable(from.getImage())
                .filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image -> to.setImage(image.getOriginalFilename()));
        to.setTitle(from.getTitle());
        to.setPrix(from.getPrix());
        to.setDescription(from.getDescription());
        to.setDate(from.getDate());
        to.setAdresse(from.getAdresse());
        to.setLink(from.getLink());
        to.setTheme(getTheme(from.getTheme()));
        to.setUser(getUser(from.getUser()));

        return to;
    }

    private Theme getTheme(Integer themeId) {
        return Optional.ofNullable(themeId)
                .flatMap(themeRepository::findById)
                .orElse(null);
    }

    private User getUser(Integer userId) {
        return Optional.ofNullable(userId)
                .flatMap(userRepository::findById)
                .orElse(null);
    }
}
