package org.example.http.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CardCreatEditDto;
import org.example.dto.UserCreatEditDto;
import org.example.service.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final CardService cardService;


    @GetMapping
    private String load() {
        return "event/event";
    }


    @PostMapping
    private String creat(@ModelAttribute @Validated CardCreatEditDto card,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("event", card);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:event/event";
        }

        cardService.create(card);
        return "redirect:/";
    }
}
