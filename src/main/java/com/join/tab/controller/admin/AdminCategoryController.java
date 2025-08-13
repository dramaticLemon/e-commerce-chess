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

/**
 * All endpoints require administrator rights.
 */
@Controller
@RequestMapping("/admin")
public class AdminCategoryController {

	private final AdminCategoryService adminCategoryService;

	public AdminCategoryController(AdminCategoryService adminCategoryService) {
		this.adminCategoryService = adminCategoryService;
	}

	/**
	 * Render main page management category.
	 * @param model object Model for data transfer.
	 * @return name template for rendering.
	 */
	@GetMapping("/category")
	public String listCategories(Model model) {
    	model.addAttribute("content", "admin/category");
		model.addAttribute("categories", adminCategoryService.getAllCategory());

		return "admin/base";
	}

	/**
	 * Handlers a POST request to create a new category
	 * <p>
	 * Receives category data from a form, passes it to the service for datebase persistence,
	 * and redirect the user to the category management page upon successful creation.
	 * 
	 * @param categoryDto The CategoryDto object containing the new category's data submitted from the form.
	 * @param model       The Model object for interacting with the view.
	 * @return			  A redirect string to the "/admin/category" URL to display the updated category list.
	 */
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
