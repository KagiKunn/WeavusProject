package com.example.project01.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
