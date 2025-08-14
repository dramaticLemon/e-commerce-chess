package com.join.tab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.join.tab.domain.User;
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

	// login
	@GetMapping("/sign-in")
	public String login(Model model) {
		model.addAttribute("content", "/sign-in");
		
		return "_layout";
	}

	@PostMapping("/sign-in")
	public String loginPostForm(Model model) {

		model.addAttribute("content", "/sign-in");
		
		return "_layout";
	}


	// register
	@GetMapping("/sign-up")
	public String register(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("content", "/sign-up");

		return "_layout";
	}


	// реализовать предупрежение направильный пароль почата нимя ( или оно занято )
	// придумать как сохранить все роли в базе даннх сразу и это будет Enum
	@PostMapping("/sign-up")
	public String registerPostForm(
		Model model, @ModelAttribute("userForm") @Valid UserDto userForm, BindingResult bindingResult) {
			
		log.info("-----------{}", userForm.getName());
		log.info("-----------{}", userForm.getEmail());
		log.info("-----------{}", userForm.getPassword());
		log.info("-----------{}", userForm.getConfirmPassword());
		
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
