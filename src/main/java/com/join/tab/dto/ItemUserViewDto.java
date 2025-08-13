package com.join.tab.dto;

import java.math.BigDecimal;

import com.join.tab.domain.Category;

public class ItemUserViewDto {
	
	private Long id;
	private String name;
	private BigDecimal price;
	private String fileUrl;
	private Category category;
	
	public ItemUserViewDto() {
	}

	public ItemUserViewDto(Long id, String name, BigDecimal price, String fileUrl, Category category) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.fileUrl = fileUrl;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Category getCategoryName() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
}
