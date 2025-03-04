package com.example.project01.service;

import com.example.project01.dto.UserDto;
import com.example.project01.entity.UserEntity;
import com.example.project01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final UserRepository userRepository;

    @Transactional
    public String SignUp(UserDto user) {
        try{
            if(userRepository.existsByUsername(user.getUsername())){
                return "id cannot be duplicated";
            }
            else{
                System.out.println("pass");
            }
            UserEntity userEntity = UserEntity.builder().username(user.getUsername()).password(user.getPassword()).build();
            userRepository.save(userEntity);
            return "true";
        } catch (Exception e){
            return "err";
        }
    }
}
