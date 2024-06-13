package org.example.dto;

import lombok.Data;
import lombok.Setter;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;


@Data
public class CardCreatEditDto {
    MultipartFile image;
    String title;
    Integer prix;
    String description;
    LocalDate date;
    String adresse;
    String link;
    Integer theme;
    Integer user;
}
