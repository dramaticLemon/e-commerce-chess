package com.join.tab.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.join.tab.domain.User;
import com.join.tab.dto.OrderItemDto;
import com.join.tab.services.CartService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/app")
public class ShoppingCartController{

	private static final Logger log = LoggerFactory.getLogger(ShoppingCartController.class);

	private final CartService cartService;

	public ShoppingCartController(CartService cartService) {
		this.cartService = cartService;
	}

	// отобразить корзину товаров
	@GetMapping("/cart")
	public String showCart(Model model, HttpSession session) {
		String sessionId = session.getId();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("username", currentPrincipalName);
		model.addAttribute("content", "/cart-item");
		model.addAttribute("sessionId", sessionId);
		return "_layout";
	}

	// добавить товар в корзину
	@PostMapping("/cart/add")
	public String addItemToShoppingCart(
		@ModelAttribute OrderItemDto orderItemDto,
		Model model,
		@AuthenticationPrincipal User user
		) {
		
		log.info("currecnt user name id {}", user.getName());
		
		Long id = orderItemDto.getId();
		String itemName = orderItemDto.getName();
		BigDecimal price = orderItemDto.getPrice();

		log.info("id {}, item-name is {}, price is {}", id ,itemName, price);


		cartService.addItemToCart(user, orderItemDto);
		
		model.addAttribute("content", "/cart-item");
		return "_layout";
	}

}
