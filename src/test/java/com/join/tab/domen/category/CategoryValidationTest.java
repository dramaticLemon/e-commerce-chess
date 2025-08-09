package com.join.tab.domen.category;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.join.tab.domain.Category;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class CategoryValidationTest {
	
	private static Validator validator;

	@BeforeAll
	public static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void testValidCatogory() {
		Category category = new Category("Books", "BOOKS");
		Set<ConstraintViolation<Category>> violations = validator.validate(category);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testBlankNameEmptyName() {
		Category category = new Category(" ", "CODE");
		Set<ConstraintViolation<Category>> violations = validator.validate(category);
		assertFalse(violations.isEmpty());
		assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
	}

	@Test
	public void testBlankNameEmptyCode() {
		Category category = new Category("Some categoties", " ");
		Set<ConstraintViolation<Category>> violations = validator.validate(category);
		assertFalse(violations.isEmpty());
		assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("code")));
	}

	@Test
    public void testCodeTooLong() {
        Category category = new Category("Some categoties", "A".repeat(101));
        Set<ConstraintViolation<Category>> violations = validator.validate(category);
        assertFalse(violations.isEmpty());
	}

}
