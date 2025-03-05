package com.example.project01.dto;

import com.example.project01.entity.ItemEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private int id;
    private String username;
    private String password;
    private List<ItemDto> items;
}
