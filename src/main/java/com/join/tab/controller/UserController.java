package com.join.tab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.join.tab.domain.User;
import com.join.tab.dto.LoginDto;
import com.join.tab.dto.UserDto;
import com.join.tab.services.SecurityService;
import com.join.tab.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("app/auth")
public class UserController {
	
	private final SecurityService securityService;
	private final UserService userService;
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	public UserController(SecurityService securityService, UserService userService) {
		this.securityService = securityService;
		this.userService = userService;
	}

	/**
	 * User login form
	 * @param model
	 * @return
	 */
	@GetMapping("/sign-in")
	public String login(Model model) {
		model.addAttribute("loginForm", new LoginDto());
		model.addAttribute("content", "/sign-in");
		
		return "_layout";
	}

	@PostMapping("/sign-in")
	public String loginPostForm(
		@ModelAttribute("loginForm") @Valid LoginDto loginForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("loginForm", loginForm);
			model.addAttribute("content", "/sign-in");
			return "_layout";
		}
		try {
			securityService.autoLogin(loginForm.getUsername(), loginForm.getPassword());
			model.addAttribute("content", "/index");
			return "_layout";

		} catch (AuthenticationException e) {
			bindingResult.reject("login.error", "Invalid username or password");
			model.addAttribute("content", "/sign-in");
			return "_layout";
		}
	}


	/**
	 * User registration form
	 * @param model
	 * @return
	 */
	@GetMapping("/sign-up")
	public String register(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("content", "/sign-up");

		return "_layout";
	}

	@PostMapping("/sign-up")
	public String registerPostForm(
		Model model, @ModelAttribute("userForm") @Valid UserDto userForm, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			log.info("-- {}", bindingResult.getAllErrors());
			model.addAttribute("userForm", userForm);
			model.addAttribute("content", "/sign-up");
			return "_layout";
		}

		String rawPassword = userForm.getPassword();
		userService.save(userForm);
		securityService.autoLogin(userForm.getName(), rawPassword);
		model.addAttribute("content", "/index");
		
		return "_layout";
	}

}
