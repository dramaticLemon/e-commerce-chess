package com.join.tab.validator;

import com.join.tab.dto.UserDto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMaches, Object> {

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		if (obj instanceof UserDto dto) {
			String password = dto.getPassword();
			String confirmPassword = dto.getConfirmPassword();

			if (password == null || confirmPassword == null || password.equals(confirmPassword)) {
				return true;
			}

			context.disableDefaultConstraintViolation();

			context.buildConstraintViolationWithTemplate("Passwords do not match")
				.addPropertyNode("confirmPassword")
				.addConstraintViolation();

			return false;
		}
		return true;
	}
	
}
