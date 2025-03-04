package com.example.project01.controller;

import com.example.project01.dto.UserDto;
import com.example.project01.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final TaskService taskService;

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
    @PostMapping("/signup")
    public String signupProcess(@ModelAttribute UserDto user, Model model) {
        System.out.println(user);
        String msg = taskService.SignUp(user);
        if(msg.equals("true")) {
            return "redirect:/login";
        }
        model.addAttribute("msg", msg);
        return "signup";
    }
}
