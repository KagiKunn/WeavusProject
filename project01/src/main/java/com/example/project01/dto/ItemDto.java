package com.example.project01.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ItemDto {
    private int id;
    private String name;
    private String description;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private UserDto userId;
}
