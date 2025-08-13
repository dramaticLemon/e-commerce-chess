package com.join.tab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/app")
public class ShoppingCartController{


	// отобразить корзину товаров
	@GetMapping("/cart")
	public String showCart(Model model, HttpSession session) {
		String sessionId = session.getId();
		model.addAttribute("content", "/cart-item");
		model.addAttribute("sessionId", sessionId);
		return "_layout";
	}

	// добавить товар в корзину
	@PostMapping("/cart/add")
	public String addItemToShoppingCart(Model model, HttpSession session) {
		String sessionId = session.getId();
		model.addAttribute("sessionId", sessionId);
		// получить обьект товара
		// получить session id пользователя
		// cохранить товар в коризину
		return null;
	}

	// удалить товар из корзины


	// отредактировать количество товаров в коризне


	// 
}
