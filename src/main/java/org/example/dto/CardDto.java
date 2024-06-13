package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@Value
@AllArgsConstructor
public class CardDto {

    Integer id;
    String image;
    String title;
    Integer prix;
    String description;
    LocalDate date;
    String adresse;
    String link;
    ThemeDto theme;
    UserDto user;
}
