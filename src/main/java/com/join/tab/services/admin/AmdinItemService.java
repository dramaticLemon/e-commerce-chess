package com.join.tab.services.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.join.tab.domain.Category;
import com.join.tab.domain.Item;
import com.join.tab.dto.ItemDto;
import com.join.tab.dto.ItemFormDto;
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

	public AmdinItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public List<ItemDto> getAllItems() {
		return itemRepository.getAllItem()
            .stream()
            .map(cat -> new ItemDto(
				cat.getId(),
				cat.getName(),
				cat.getPrice(),
				cat.getCategory()
				)
			).collect(Collectors.toList());
	}

	@Transactional
	public void createItem(ItemFormDto itemFormDto) {

		if (itemRepository.existsByName(itemFormDto.getName())) {
        	throw new DuplicateFieldException("Item name already exists");
    	}

		Item item = new Item();
		item.setName(itemFormDto.getName());
		item.setPrice(itemFormDto.getPrice());
		Category category = new Category();
		category.setId(itemFormDto.getCategory());
		item.setCaregory(category);
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

		Category category = new Category();
		category.setId(itemFormDto.getCategory());
		
		item.setCaregory(category);
		
		itemRepository.update(item);
	}
}
