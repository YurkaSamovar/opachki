package org.example.http.rest;

import lombok.RequiredArgsConstructor;
import org.example.dto.PageResponse;
import org.example.dto.UserCreatEditDto;
import org.example.dto.UserDto;
import org.example.dto.UserFilter;
import org.example.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public PageResponse<UserDto> findAll(UserFilter filter, Pageable pageable) {
//        model.addAttribute("users", userService.findAll());
//        model.addAttribute("users", userService.findAll(filter, pageable));
        Page<UserDto> page = userService.findAll(filter, pageable);
        return PageResponse.of(page);
    }

    @GetMapping("/{id}")
    public UserDto fyndById(@PathVariable Integer id) {

        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@Validated @RequestBody UserCreatEditDto user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable("id")  Integer id,
                         @Validated @RequestBody UserCreatEditDto user) {
        return userService.update(id, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable("id") Integer id) {
        if(!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}