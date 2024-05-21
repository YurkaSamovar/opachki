package org.example.dto;

import java.time.LocalDate;

public record CardFilter(Integer theme,
                         LocalDate date,
                         Integer prix) {
}
