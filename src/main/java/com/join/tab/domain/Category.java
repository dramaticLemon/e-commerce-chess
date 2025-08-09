package com.join.tab.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@NotBlank(message = "Name must not be blank")
	@Size(max = 255, message = "Value '${validatedValue}' must be at most 255 characters")
	@Column(name = "category_name", nullable = false, unique=true)
    private String name;

	@NotBlank(message = "Code must not be blank")
	@Size(max = 100, message = "Value '${validatedValue}' must be at most 100 characters")
	@Column(name="category_code", nullable=false, unique=true)
    private String code;

	@Column(name="created_at", nullable=false, updatable=false)
	private LocalDateTime createdAt;
	
	@Column(name="update_at", nullable=false)
	private LocalDateTime updateAt;

	@Valid
	@OneToMany(mappedBy="category", fetch=FetchType.LAZY)
	private Set<Item> items = new HashSet<>();

	public Category() { }

	public Category(String name, String code) {
		this.name = name;
		this.code = code;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updateAt;
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", code=" + code + "]";
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
}
