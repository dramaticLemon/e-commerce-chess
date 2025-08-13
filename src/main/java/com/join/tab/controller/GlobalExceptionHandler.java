package com.join.tab.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.join.tab.exception.DuplicateFieldException;
import com.join.tab.exception.DuplicateItemNameException;
import com.join.tab.services.admin.AdminCategoryService;

@ControllerAdvice
public class GlobalExceptionHandler {

	// private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private final AdminCategoryService adminCategoryService;

    public GlobalExceptionHandler(AdminCategoryService adminCategoryService) {
        this.adminCategoryService = adminCategoryService;
    }

	@ExceptionHandler(DuplicateFieldException.class)
	public String handleDuplicateFieldException(DuplicateFieldException ex, Model model) {
		model.addAttribute("error", ex.getMessage());
		model.addAttribute("categories", adminCategoryService.getAllCategory());
		model.addAttribute("content", "admin/category");
		return "admin/base";
	}

	@ExceptionHandler(DuplicateItemNameException.class) // Пример нового исключения для товара
    public String handleItemCreationException(DuplicateItemNameException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("categories", adminCategoryService.getAllCategory());
        model.addAttribute("content", "admin/items");
        return "admin/base";
    }

	// @ExceptionHandler(Exception.class)
    // public String handleAllExceptions(Exception ex, Model model, HttpServletRequest request) {
    //     log.error("Unhandled exception occurred at " + request.getRequestURI(), ex);
    //     model.addAttribute("errorMessage", "внутренняя ошибка. Пожалуйста, попробуйте позже.");
    //     model.addAttribute("categories", adminCategoryService.getAllCategory());
    //     return "error/500";
    // }


}
