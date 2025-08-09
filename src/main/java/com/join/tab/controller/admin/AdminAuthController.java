package com.join.tab.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminAuthController {

	@GetMapping("/login")
    public String login() {
        return "admin/login";
    }

	@GetMapping("/register")
    public String register() {
        return "admin/register";
    }


	@GetMapping("/password")
    public String forgotPassword() {
        return "admin/password";
    }
	
}
