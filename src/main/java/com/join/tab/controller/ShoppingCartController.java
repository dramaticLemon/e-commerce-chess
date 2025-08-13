package com.join.tab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/app")
public class ShoppingCartController{


	// отобразить корзину товаров


	// добавить товар в корзину
	@PostMapping("/cart/add")
	public String addItemToShoppingCart(Model model) {
		return null;
	}

	// удалить товар из корзины


	// отредактировать количество товаров в коризне


	// 
}
