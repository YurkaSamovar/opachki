package org.example.http.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.database.entity.Role;
import org.example.database.repository.CompanyRepository;
import org.example.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes({"user"})
public class CreetingController {

    @ModelAttribute("roles")
    public List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }

    @GetMapping("/hello")
    public String hello(Model model, UserDto userDto) {
        model.addAttribute("user", userDto);
        return "greeting/hello";
    }


//    @RequestMapping(value = "hello", method = RequestMethod.GET) это другой способ указать маппинг
    @GetMapping("/hello/{id}")
    public String hello(@PathVariable("id") Integer id,
                        Model model) {
        //model.addAttribute("user", new UserDto(id, "Andrey"));
        return "greeting/hello";
    }

    @GetMapping("/bye")
    public String bye(@SessionAttribute("user") UserDto userDto) {
        // mv.setViewName("greeting/bye");
        return "greeting/bye";

        // можно просто вернуть строку return
    }
}
