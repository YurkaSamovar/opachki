package org.example.http.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.database.entity.Role;
import org.example.dto.PageResponse;
import org.example.dto.UserCreatEditDto;
import org.example.dto.UserDto;
import org.example.dto.UserFilter;
import org.example.service.CompanyService;
import org.example.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.Serializable;

@Controller
@RequestMapping("/users") // здесь указывает маппинг для всех методов поэтому в некоторых и не пишем маппинг
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CompanyService companyService;

    @GetMapping
    public String findAll(Model model, UserFilter filter, Pageable pageable) {
//        model.addAttribute("users", userService.findAll());
//        model.addAttribute("users", userService.findAll(filter, pageable));
        Page<UserDto> page = userService.findAll(filter, pageable);
        model.addAttribute("users", PageResponse.of(page));
        model.addAttribute("filter", filter);
        return "user/users";
    }

    @GetMapping("/{id}")
    public String fyndById(@PathVariable Integer id, Model model) {

        return userService.findById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    model.addAttribute("roles", Role.values());
                    model.addAttribute("companies", companyService.findAll());
                    return "user/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute @Validated UserCreatEditDto user,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
//            redirectAttributes.addAttribute("username", user.getUsername());
//            redirectAttributes.addAttribute("firstname", user.getFirstname()); //так вводим атрибуты по одному
            redirectAttributes.addFlashAttribute("user", user); // а так все сразу
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/users/registration";

        }

        var userDto = userService.create(user);
        return "redirect:/users/" + userDto.getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id")  Integer id, @ModelAttribute @Validated UserCreatEditDto user,
                         BindingResult bindingResult) {
        return userService.update(id, user)
                .map(it -> "redirect:/users/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        return userService.delete(id) ? "redirect:/users" : null;
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("user") UserCreatEditDto user) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyService.findAll());
        return "user/registration";
    }
}
