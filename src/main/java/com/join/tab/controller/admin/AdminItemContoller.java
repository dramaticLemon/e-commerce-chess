package com.join.tab.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminItemContoller {

	@GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("content", "admin/index");
		return "admin/base";

	}

	/** show all item  */
	@GetMapping("/items")
	public String fetchItems() {
		return "admin/some-page";
	}

	@GetMapping("/items/create")
	public String createItem() {
		return "admin/create-item";
	}

	@PostMapping("/items/create")
	public String createItems() {
		return "admin/some-page";
	}

	@GetMapping("/admin/items/edit/{id}")
	public String editItem() {
		return "admin/edit-some-page";
	}

	@PostMapping("/admin/items/edit/{id}")
	public String editItems() {
		return "admin/some-page";
	}

	@DeleteMapping("/admin/items/{id}/delete")
	public String deleteItem() {
		return "admin/some-page";
	}
}
