package com.join.tab.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.join.tab.dto.CategoryDto;
import com.join.tab.services.admin.AdminCategoryService;

@Controller
@RequestMapping("/admin")
public class AdminCategoryController {
	/*
	 * added
	 * edit
	 * get all
	 * remove
	*/

	private final AdminCategoryService adminCategoryService;

	public AdminCategoryController(AdminCategoryService adminCategoryService) {
		this.adminCategoryService = adminCategoryService;
	}

	/**
	 * Render main page-management-category and show create category in database
	 * @param model
	 * @return
	 */
	@GetMapping("/category")
	public String index(Model model) {
    	model.addAttribute("content", "admin/category");
		model.addAttribute("categories", adminCategoryService.getAllCategory());

		return "admin/base";
	}

	@PostMapping("/category")
	public String createCategory(@ModelAttribute CategoryDto categoryDto, Model model) {
		adminCategoryService.createCategory(categoryDto);
		return "redirect:/admin/category";
		
	}

	@PostMapping("/category/delete/{id}")
	public String deleteCategory(@PathVariable("id") Long id, RedirectAttributes redirectAttr) {
		adminCategoryService.delete(id);
		redirectAttr.addFlashAttribute("deleteMessage", "Category is deleted");
		return "redirect:/admin/category";
	}

	@PostMapping("/category/update")
	public String updateCategory(RedirectAttributes redirectAttr, @ModelAttribute CategoryDto categoryDto) {
		adminCategoryService.updateCategory(categoryDto);
		redirectAttr.addFlashAttribute("updateMessage", "Category is updated");
		return "redirect:/admin/category";
	}
}
