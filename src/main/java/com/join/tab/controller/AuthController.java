package com.join.tab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	@GetMapping("/sign-in")
	public String login(Model model) {
		model.addAttribute("content", "/sign-in");
		return "_layout";
	}

	@GetMapping("/sign-up")
	public String register(Model model) {
		model.addAttribute("content", "/sign-up");
		return "_layout";
	}
}
