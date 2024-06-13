package org.example.http.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CardCreatEditDto;
import org.example.service.CardService;
import org.example.service.UserService;
import org.example.service.mapper.UserCreateEditMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final CardService cardService;
    private final UserService userService;

    @GetMapping("/{id}")
    public String fyndById(@PathVariable Integer id, Model model) {
        return cardService.findById(id)
                .map(card -> {
                    model.addAttribute("card", card);
                    return "event/event";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/add")
    public String event(Model model, Principal principal) {
        model.addAttribute("id", userService.findByUsername(principal.getName()).getId());
        return "event/ajouter";
    }


    @PostMapping("/add")
    private String creat(@ModelAttribute @Validated CardCreatEditDto card,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Principal principal) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("event", card);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:event/ajouter";
        }
        card.setUser(userService.findByUsername(principal.getName()).getId());
        cardService.create(card);
        return "redirect:/";
    }
}
