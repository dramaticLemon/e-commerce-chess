package com.join.tab.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.join.tab.dto.ItemFormDto;
import com.join.tab.services.admin.AdminCategoryService;
import com.join.tab.services.admin.AmdinItemService;



@Controller
@RequestMapping("/admin")
public class AdminItemContoller {

	private static final Logger log = LoggerFactory.getLogger(AdminItemContoller.class);
    private final AmdinItemService adminItemService;
	private final AdminCategoryService adminCategoryService;

	public AdminItemContoller(AmdinItemService adminItemService, AdminCategoryService adminCategoryService) {
		this.adminItemService = adminItemService;
		this.adminCategoryService = adminCategoryService;
	}

	@GetMapping("/item")
    public String listItems(Model model) {
		model.addAttribute("categories", adminCategoryService.getAllCategory());
        model.addAttribute("content", "admin/item");
		model.addAttribute("items", adminItemService.getAllItems());

		return "admin/base";

	}

	@PostMapping("/items/create")
	public String createItem(@ModelAttribute ItemFormDto item,
	@RequestParam("file") MultipartFile file) {
		try {
			item.setFile(file);
			System.out.println(item);
			System.out.println(item.getFile());
            adminItemService.createItem(item);
			

            return "redirect:/admin/item";
        } catch (Exception e) {
			log.info("{}",e);
            return "redirect:/admin/item?error=upload_failed";
        }
	}

	@PostMapping("/item/delete/{id}")
	public String deleteItem(@PathVariable("id") Long id, RedirectAttributes redirectAttr) {
		adminItemService.dropItem(id);
		redirectAttr.addFlashAttribute("deleteMessage", "Item is deleted");
		return "redirect: /admin/item";
	}

	@PostMapping("/item/update")
	public String updateItem(
		RedirectAttributes redirectAttr,
		@ModelAttribute ItemFormDto itemFormDto) {
		adminItemService.updateItem(itemFormDto);
		redirectAttr.addFlashAttribute(
			"updateMessage", "Category is updated");

		return "redirect:/admin/item";
	}


}
