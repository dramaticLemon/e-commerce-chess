package com.join.tab.services.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.join.tab.domain.Category;
import com.join.tab.domain.Item;
import com.join.tab.dto.CategoryDto;
import com.join.tab.dto.ItemDto;
import com.join.tab.exception.DuplicateFieldException;
import com.join.tab.repository.CotegoryRepository;

@Service
public class AdminCategoryService {
	
	private final CotegoryRepository categoryRepository;

	public AdminCategoryService(CotegoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@Transactional
	public void createCategory(CategoryDto categoryDto) {

		if (categoryRepository.existsByNameOrCode(categoryDto.getName(), categoryDto.getCode())) {
        throw new DuplicateFieldException("Category name or code already exists");
    	}

		Category category = new Category();
		category.setName(categoryDto.getName());
		category.setCode(categoryDto.getCode());
		category.setCreatedAt(categoryDto.getCreatedAt());
		category.setUpdateAt(categoryDto.getUpdatedAt());

		if (categoryDto.getItems() != null) {
			category.setItems(
				categoryDto.getItems().stream()
					.map((ItemDto itemDto) -> { 
						Item item = new Item();
						item.setName(itemDto.getName());
						item.setCategory(category);
						return item;
					})
					.collect(Collectors.<Item>toSet())
			);
			
		}
		categoryRepository.save(category);
		
	}


	public List<CategoryDto> getAllCategory() {
		return categoryRepository.getAllCategory()
            .stream()
            .map(cat -> new CategoryDto(
				cat.getId(),
				cat.getName(),
				cat.getCode(),
				cat.getCreatedAt(), 
    			cat.getUpdatedAt()
				)
			).collect(Collectors.toList());
	}

	public void delete(Long id) {
		categoryRepository.deleteById(id);
	}

	public void updateCategory(CategoryDto categoryDto) {
		Category category = categoryRepository.getCategory(categoryDto.getId());
		category.setName(categoryDto.getName());
		categoryRepository.update(category);
	}

}
