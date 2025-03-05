package com.example.project01.service;

import com.example.project01.dto.ItemDto;
import com.example.project01.dto.UserDto;
import com.example.project01.entity.ItemEntity;
import com.example.project01.entity.UserEntity;
import com.example.project01.repository.ItemRepository;
import com.example.project01.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public List<ItemEntity> findAll() {
        try{
            return itemRepository.findAll();
        }
        catch(Exception e){
            return List.of();
        }
    }
    @Transactional
    public boolean itemRegistry(ItemDto itemDto, HttpSession session) {
        System.out.println("method called");
        try{
            System.out.println(session.getAttribute("user"));
            UserDto userDto = (UserDto) session.getAttribute("user");
            System.out.println("User ID from session: " + userDto.getUsername());
            UserEntity user = userRepository.findByUsername(userDto.getUsername()).orElseThrow(() -> new IllegalArgumentException("User not found with auth_id: "));
            System.out.println("user here : " + user.getId());
            ItemEntity itemEntity = ItemEntity.builder()
                    .name(itemDto.getName())
                    .description(itemDto.getDescription())
                    .createdAt(LocalDate.now())
                    .user(user)
                    .build();
            System.out.println("build complete!");
            itemRepository.save(itemEntity);
            return true;
        } catch (Exception e){
            System.out.println("Gatcha!");
            return false;
        }
    }
}
