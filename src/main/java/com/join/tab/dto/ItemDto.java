package com.join.tab.dto;

import java.math.BigDecimal;

import com.join.tab.domain.Category;

public class ItemDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Category category;
    private int idCategory;
    private int quantity;

    public ItemDto() {}

    public ItemDto(Long id, String name, BigDecimal price, Category category, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public ItemDto(Long id, String name, BigDecimal price, int idCategory, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.idCategory = idCategory;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    
}
