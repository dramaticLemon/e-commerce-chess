package com.join.tab.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "items")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message="Name must not be blank")
	@Size(max=255, message="Name must be at most 255 characters")
	@Column(name = "name", nullable = false, unique=true)
	private String name;

	@NotNull(message="Name must not be null")
	@DecimalMin(value="0.0", inclusive=false, message="Price must be greater then 0")
	@Column(name = "price", nullable = false)
	private BigDecimal price;
	
	@NotNull(message="Category must not be null")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable=false)
	private Category category;

	@NotBlank(message="File path cannot be blank")
	@Pattern(
        regexp = "^[a-z0-9.-]+/[\\w!\\-\\.\\*'\\(\\)]+(\\/[\\w!\\-\\.\\*'\\(\\)]+)*$",
        message = "Invalid MinIO object path format"
    )
	private String fileUrl;

	@NotNull(message="CreatedAt must not be null")
	@Column(name="created_at", nullable=false, updatable=false)
	private LocalDateTime createdAt;
	
	@NotNull(message="UpdatedAt must not be null")
	@Column(name="updated_at", nullable=false)
	private LocalDateTime updatedAt;

	@NotNull(message="Quantity must not be null")
	@Min(value=0, message="Quantity must be at least 0")
	@Column(name="quantity", nullable=false)
	@ColumnDefault("1")
	private Integer quantity;

	public Item() {
	}

	public Item(Long id, String name, BigDecimal price,
	 Category category, String fileUrl, Integer quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.fileUrl = fileUrl;
		this.quantity = quantity;
	}


	@PrePersist
	public void onCreate() {
		LocalDateTime time = LocalDateTime.now();
		this.createdAt =time;
		this.updatedAt = time;
	}

	@PreUpdate
	public void onUpdate() {
		this.updatedAt = LocalDateTime.now();
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

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
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

	public LocalDateTime getUpdatdeAt() {
		return updatedAt;
	}

	public void setUpdateAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
}
