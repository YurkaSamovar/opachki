package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
@Value
public class ThemeDto {

    Integer id;
    String name;
}
