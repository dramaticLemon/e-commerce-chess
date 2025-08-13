package com.join.tab.dto;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

public class ItemFormDto {

	private Long id;
	private String name;
    private BigDecimal price;
    private Long category;
	private MultipartFile file;
	private int quantity;

	public ItemFormDto() {
	}

	public ItemFormDto(String name, BigDecimal price, Long category, int quantity) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.quantity = quantity;
	}

	public ItemFormDto(String name, BigDecimal price, Long category, Long id, MultipartFile file, int quantity) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.id = id;
		this.file = file;
		this.quantity = quantity;
	}

	public ItemFormDto(String name, BigDecimal price, Long category, Long id, int quantity) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.id = id;
		this.quantity = quantity;
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
	public Long getCategory() {
		return category;
	}
	public void setCategory(Long category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
