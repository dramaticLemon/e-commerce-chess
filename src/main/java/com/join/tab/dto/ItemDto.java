package com.join.tab.dto;

import java.math.BigDecimal;

import com.join.tab.domain.Category;

public class ItemDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Category category;
    private int idCategory;

    public ItemDto() {}

    public ItemDto(Long id, String name, BigDecimal price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public ItemDto(Long id, String name, BigDecimal price, int idCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.idCategory = idCategory;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    
    
}
