package com.join.tab.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminBaseController {
	
	@GetMapping("/admin/index")
	public String index(Model model) {
		model.addAttribute("content", "admin/index");
		return "admin/base";
	}
}
