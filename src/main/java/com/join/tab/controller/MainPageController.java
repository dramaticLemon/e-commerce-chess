package com.join.tab.controller;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

import com.join.tab.dto.ItemUserViewDto;
import com.join.tab.services.admin.AmdinItemService;
import com.join.tab.services.admin.MinioService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/app")
public class MainPageController {
	
	private final AmdinItemService adminItemService;
	private final MinioService minioService;

    public MainPageController(
		AmdinItemService adminItemService,
		MinioService minioService
		) {
		this.adminItemService = adminItemService;
		this.minioService = minioService;
	}

	@GetMapping("/test-error")
	public String testError() {
		throw new RuntimeException("Искусственная ошибка 500 для теста");
	}

	@GetMapping("/dashboard")
	public String index(Model model) {

		model.addAttribute("content", "/index");

		return "_layout";
	}
	
	@GetMapping("/about-us")
	public String aboutUs(Model model) {
		model.addAttribute("content", "/about");	

		return "_layout";
	}

	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("content", "/contact");

		return "_layout";
	}

	@GetMapping("/storage")
	public String getStorage(Model model) {
		List<ItemUserViewDto> items = adminItemService.getAllItemsForUser();
		model.addAttribute("items", items);
		model.addAttribute("content", "/storage");

		return "_layout";
	}


	@GetMapping("/images/**")
	public ResponseEntity<Resource> getImage(HttpServletRequest request) {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		String filename = new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
		Resource file = minioService.loadFileAsResource(filename);
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(file);
	}

}

