package org.example.http.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CardFilter;
import org.example.service.CardService;
import org.example.service.ThemeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final ThemeService themeService;

    @GetMapping
    public String findAll(Model model, CardFilter filter) {
        model.addAttribute("cards", cardService.findAll(filter));
        model.addAttribute("themes", themeService.findAll());
        model.addAttribute("filter", filter);
        return "index";
    }
}
