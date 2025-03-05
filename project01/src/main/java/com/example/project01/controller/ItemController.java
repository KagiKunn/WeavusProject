package com.example.project01.controller;

import com.example.project01.dto.ItemDto;
import com.example.project01.dto.UserDto;
import com.example.project01.service.ItemService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/edit/{id}")
    public String modifyItem(@PathVariable Integer id, Model model) {
        System.out.println("item no : " + id);
        model.addAttribute("item",itemService.findItem(id));
        return "edit";
    }
    @PostMapping("/edit")
    public String editItem(@ModelAttribute ItemDto itemDto, @RequestParam int user,  HttpSession session) {
        System.out.println("!!!!!!!!!!!!!->>>" + user);
        itemService.editItem(user,itemDto,session);
        return "redirect:/home";
    }

    @PostMapping("/deleteItem")
    public String deleteItem(@RequestParam int no, HttpSession session) {
        System.out.println("item no : " + no);
        itemService.deleteItem(no);
        return "redirect:/home";
    }
}
