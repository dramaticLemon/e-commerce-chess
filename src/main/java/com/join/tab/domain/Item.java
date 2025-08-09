package com.join.tab.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "items")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message="Name must not be blank")
	@Size(max=255, message="Name must be at most 255 characters")
	@Column(name = "name", nullable = false)
	private String name;

	@NotBlank(message="Name must not be null")
	@DecimalMin(value="0.0", inclusive=false, message="Price must be greater then 0")
	@Column(name = "price", nullable = false)
	private BigDecimal price;
	
	@NotNull(message="Category must not be null")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable=false)
	private Category category;

	@NotNull(message="CreatedAt must not be null")
	@Column(name="created_at", nullable=false, updatable=false)
	private LocalDateTime createdAt;
	
	@NotNull(message="UpdatedAt must not be null")
	@Column(name="update_at", nullable=false)
	private LocalDateTime updateAt;

	public Item() {
	}

	public Item(Long id, String name, BigDecimal price,
	 Category category) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
	}


	@PrePersist
	public void onCreate() {
		LocalDateTime time = LocalDateTime.now();
		this.createdAt =time;
		this.updateAt = time;
	}

	@PreUpdate
	public void onUpdate() {
		this.updateAt = LocalDateTime.now();
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
		return this.price;
	}

	public void setCurrentPrice(BigDecimal price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCaregory(Category category) {
		this.category = category;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price" + price + "]";
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	
}
