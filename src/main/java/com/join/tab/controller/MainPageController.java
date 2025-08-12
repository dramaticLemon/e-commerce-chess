package com.join.tab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class MainPageController {
	
    @GetMapping("/dashboard")
	public String index(Model model) {
		model.addAttribute("content", "/index");
		return "_layout";
	}
	
	@GetMapping("/about-us")
	public String aboutUs() {
		return "about";
	}

	@GetMapping("/storage")
	public String getStorage(Model model) {
		model.addAttribute("content", "/storage");
		return "_layout";
	}

}
