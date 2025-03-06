package com.example.project01.controller;

import com.example.project01.dto.UserDto;
import com.example.project01.service.ItemService;
import com.example.project01.service.MainService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MainService mainService;
    private final ItemService itemService;

    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/home";
    }
    
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("items",itemService.findAll(model));
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(UserDto user, Model model, HttpSession session) {
        return mainService.login(user, session, model);
    }
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }
}
