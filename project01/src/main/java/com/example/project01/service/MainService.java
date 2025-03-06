package com.example.project01.service;

import com.example.project01.dto.UserDto;
import com.example.project01.entity.UserEntity;
import com.example.project01.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public class MainService {

	private final UserRepository userRepository;

	public MainService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public String login(UserDto user, HttpSession session, Model model) {
		boolean check = userRepository.existsByUsernameAndPassword(user.getUsername(), user.getPassword());
		UserEntity userEntity = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		System.out.println(userEntity);
		System.out.println(check);
		if (check) {
			session.setAttribute("user", userEntity);
			return "redirect:/home";
		} else {
			model.addAttribute("msg", "login fail");
			return "/login";
		}
	}

	public UserEntity findUser(int no) {
		return userRepository.findById(no).orElseThrow(() -> new RuntimeException("User Not Found"));
	}

	public boolean sessionCheck(HttpSession session) {
		System.out.println("session is null");
		return session != null;
	}
}
