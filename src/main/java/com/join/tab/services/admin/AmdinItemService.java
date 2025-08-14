package com.join.tab.services.admin;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.join.tab.domain.Category;
import com.join.tab.domain.Item;
import com.join.tab.dto.ItemDto;
import com.join.tab.dto.ItemFormDto;
import com.join.tab.dto.ItemUserViewDto;
import com.join.tab.exception.DuplicateFieldException;
import com.join.tab.repository.ItemRepository;

/**
 * create 
 * update
 * delete
 * read item (get)
 */
@Service
public class AmdinItemService {
	
	private final ItemRepository itemRepository;
	private final MinioService minioService;

	public AmdinItemService(
		ItemRepository itemRepository,
		MinioService minioService
		) {
		this.itemRepository = itemRepository;
		this.minioService = minioService;
	}

	public List<ItemDto> getAllItems() {
		return itemRepository.getAllItem()
            .stream()
            .map(cat -> new ItemDto(
				cat.getId(),
				cat.getName(),
				cat.getPrice(),
				cat.getCategory(),
				cat.getQuantity()
				)
			).collect(Collectors.toList());
	}

	@Transactional
	public void createItem(ItemFormDto itemFormDto) throws Exception{

		if (itemRepository.existsByName(itemFormDto.getName())) {
        	throw new DuplicateFieldException("Item name already exists");
    	}
		InputStream inputStream = new BufferedInputStream(itemFormDto.getFile().getInputStream(), 3 * 1024 * 1024);

		String fileUrl = minioService.uploadFile(inputStream, itemFormDto.getFile().getOriginalFilename());
		Item item = new Item();
		item.setName(itemFormDto.getName());
		item.setPrice(itemFormDto.getPrice());
		Category category = new Category();
		category.setId(itemFormDto.getCategory());
		item.setCategory(category);
		item.setFileUrl(fileUrl);
		item.setQuantity(itemFormDto.getQuantity());

		itemRepository.save(item);
	}

	public void dropItem(Long id) {
		itemRepository.deleteById(id);
	}

	public void dropItem(String name) {
		itemRepository.deleteByName(name);
	}

	public Item getItem(Long id) {
		return itemRepository.getItem(id);
	}
	public void getItem(String name) {}

	
	public void updateItem(ItemFormDto itemFormDto) {
		Item item = itemRepository.getItem(itemFormDto.getId());
		item.setName(itemFormDto.getName());
		item.setPrice(itemFormDto.getPrice());
		item.setQuantity(itemFormDto.getQuantity());

		Category category = new Category();
		category.setId(itemFormDto.getCategory());
		
		item.setCategory(category);

		itemRepository.update(item);
	}

	public List<ItemUserViewDto> getAllItemsForUser() {
		return itemRepository.getAllItemsForUser();
	}
}
