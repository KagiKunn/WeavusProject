package com.example.project01.controller;

import com.example.project01.entity.UserEntity;
import com.example.project01.service.ItemService;
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
    private final ItemService itemService;

//    public HomeController(MainService mainService) {
//        this.mainService = mainService;
//    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("items",itemService.findAll());
        return "/home";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @PostMapping("/login")
    public String loginProcess(UserEntity user, Model model) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return mainService.login(user.getUsername(),user.getPassword());
    }
}
