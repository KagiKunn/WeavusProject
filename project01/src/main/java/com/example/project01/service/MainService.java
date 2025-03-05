package com.example.project01.service;

import com.example.project01.dto.UserDto;
import com.example.project01.entity.UserEntity;
import com.example.project01.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    private final UserRepository userRepository;

    public MainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String login(UserDto user, HttpSession session) {
        Boolean check = userRepository.existsByUsernameAndPassword(user.getUsername(), user.getPassword());

        System.out.println(check);
        if(check) {
            session.setAttribute("user", user);
            return "redirect:/home";
        }
        else{
            return "redirect:/login";
        }
    }
}
