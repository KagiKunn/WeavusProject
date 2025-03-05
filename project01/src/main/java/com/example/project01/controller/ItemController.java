package com.example.project01.controller;

import com.example.project01.dto.ItemDto;
import com.example.project01.service.ItemService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    @GetMapping("/item")
    public String C() {
        return "/item";
    }
    @PostMapping("/itemRegistry")
    public String ItemRegistryProcess(@ModelAttribute ItemDto itemDto, HttpSession session) {
        if(itemService.itemRegistry(itemDto,session)){
            System.out.println("good");
        }
        else{
            System.out.println("fail");
        }
        return "redirect:/home";
    }
}
