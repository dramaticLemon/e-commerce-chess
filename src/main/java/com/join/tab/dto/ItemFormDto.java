package com.join.tab.dto;

import java.math.BigDecimal;

public class ItemFormDto {

	private Long id;
	private String name;
    private BigDecimal price;
    private Long category;

	public ItemFormDto() {
	}

	public ItemFormDto(String name, BigDecimal price, Long category) {
		this.name = name;
		this.price = price;
		this.category = category;
	}

	public ItemFormDto(String name, BigDecimal price, Long category, Long id) {
		this.name = name;
		this.price = price;
		this.category = category;
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
	
	
}
