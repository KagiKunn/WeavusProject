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
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final MainService mainService;

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public List<ItemEntity> findAll(Model model) {
        try{
            model.addAttribute("msg", "welcome");
            return itemRepository.findAll();
        }
        catch(Exception e){
            return List.of();
        }
    }
    @Transactional
    public boolean itemRegistry(ItemDto itemDto, HttpSession session) {
        System.out.println("itemRegistry called");
        try{
            UserEntity user = (UserEntity) session.getAttribute("user");
            ItemEntity itemEntity = ItemEntity.builder()
                    .name(itemDto.getName())
                    .description(itemDto.getDescription())
                    .createdAt(LocalDate.now())
                    .user(user)
                    .build();
            itemRepository.save(itemEntity);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void deleteItem(int no) {
        System.out.println("deleteItem called");
//        itemRepository.deleteById(no);
    }

    public boolean editItem(int userno, ItemDto itemDto,HttpSession session) {
        System.out.println("editItem called");
        int userId = -1;
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user != null) {
            userId = user.getId();
            System.out.println("User ID: " + userId);
            if(userId == userno){
                ItemEntity itemEntity = itemRepository.findById(itemDto.getId()).orElseThrow();
                itemEntity = itemEntity.toBuilder()
                        .name(itemDto.getName())
                        .description(itemDto.getDescription())
                        .updatedAt(LocalDate.now())
                        .build();
                itemRepository.save(itemEntity);
                return true;
            }
        }
        return false;
    }



    public ItemEntity findItem(int no) {
        return itemRepository.findById(no).orElseThrow(() -> new IllegalArgumentException("Item not found"));
    }
}
