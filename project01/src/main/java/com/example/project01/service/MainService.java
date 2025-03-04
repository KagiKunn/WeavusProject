package com.example.project01.service;

import com.example.project01.repository.UserRepository;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    private final UserRepository userRepository;

    public MainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String login(String username, String password) {
        Boolean check = userRepository.existsByUsernameAndPassword(username,password);
        System.out.println(check);
        if(check) {
            return "redirect:/";
        }
        else{
            return "redirect:/login";
        }
    }
}
