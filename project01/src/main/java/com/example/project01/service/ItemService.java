package com.example.project01.service;

import com.example.project01.dto.ItemDto;
import com.example.project01.entity.ItemEntity;
import com.example.project01.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<ItemEntity> findAll() {
        try{
            return itemRepository.findAll();
        }
        catch(Exception e){
            return List.of();
        }
    }

    public String itemRegistry(ItemDto itemDto) {
        try{
            ItemEntity itemEntity = ItemEntity.builder()
                    .name(itemDto.getName())
                    .description(itemDto.getDescription())
                    .createdAt(LocalDate.now())

                    .build();
            itemRepository.save(itemEntity);
            return "success";
        } catch (Exception e){
            return "fail";
        }
    }
}
