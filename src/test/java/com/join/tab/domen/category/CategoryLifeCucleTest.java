package com.join.tab.domen.category;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.join.tab.domain.Category;

public class CategoryLifeCucleTest {
	
	 @Test
    public void testPrePersist() {
        Category category = new Category("Test", "TEST");
        category.onCreate();

        assertNotNull(category.getCreatedAt());
        assertNotNull(category.getUpdatedAt());
        assertTrue(category.getCreatedAt().isEqual(category.getUpdatedAt()));
    }

    @Test
    public void testPreUpdate() throws InterruptedException {
        Category category = new Category("Test", "TEST");
        category.onCreate();
        LocalDateTime created = category.getCreatedAt();

        Thread.sleep(1000);
        category.onUpdate();

        assertTrue(category.getUpdatedAt().isAfter(created));
    }
	
}
