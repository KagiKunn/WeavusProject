package com.example.project01.service;

import com.example.project01.dto.ItemDto;
import com.example.project01.entity.ItemEntity;
import com.example.project01.entity.UserEntity;
import com.example.project01.repository.ItemRepository;
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

	public List<ItemEntity> findAll(Model model) {
		try {
			return itemRepository.findAll();
		} catch (Exception e) {
			return List.of();
		}
	}

	@Transactional
	public boolean itemRegistry(ItemDto itemDto, HttpSession session) {
		System.out.println("itemRegistry called");
		try {
			UserEntity user = (UserEntity) session.getAttribute("user");
			if (user == null) {
				return false;
			}
			ItemEntity itemEntity = ItemEntity.builder()
					.name(itemDto.getName())
					.description(itemDto.getDescription())
					.createdAt(LocalDate.now())
					.user(user)
					.build();
			itemRepository.save(itemEntity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteItem(int no, HttpSession session) {
		System.out.println("deleteItem called");
		if (!mainService.sessionCheck(session)) {
			return false;
		}
		int userId;
		UserEntity user = (UserEntity) session.getAttribute("user");
		if (user != null) {
			userId = user.getId();
			if (userId == no) {
				itemRepository.deleteById(no);
				return true;
			}
		}
		return false;
	}

	public boolean editItem(int userno, ItemDto itemDto, HttpSession session) {
		System.out.println("editItem called");
		int userId;
		if (!mainService.sessionCheck(session)) {
			return false;
		}
		UserEntity user = (UserEntity) session.getAttribute("user");
		if (user != null) {
			userId = user.getId();
			System.out.println("User ID: " + userId);
			if (userId == userno) {
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
