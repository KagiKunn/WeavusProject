package com.example.project01.controller;

import com.example.project01.entity.UserEntity;
import com.example.project01.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MainService mainService;

//    public HomeController(MainService mainService) {
//        this.mainService = mainService;
//    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/B")
    public String B() {
        return "signup";
    }

    @GetMapping("/C")
    public String C() {
        return "Cpage";
    }

    @PostMapping("/login")
    public String loginProcess(UserEntity user, Model model) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return mainService.login(user.getUsername(),user.getPassword());
    }
}
