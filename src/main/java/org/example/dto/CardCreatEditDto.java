package org.example.dto;

import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Value
public class CardCreatEditDto {

    MultipartFile image;
    String title;
    Integer prix;
    String description;
    LocalDate date;
    String adresse;
    String link;
    Integer theme;
}
