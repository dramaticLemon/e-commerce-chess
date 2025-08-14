package com.join.tab.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.join.tab.dto.UserDto;
import com.join.tab.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


@Component
public class UniqueUserValidator implements ConstraintValidator<UniqueUser, UserDto>{

    private static final Logger log = LoggerFactory.getLogger(UniqueUserValidator.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(UserDto user, ConstraintValidatorContext context) {

        try {
            if (user == null) return true;
            if (user.getName() == null || user.getEmail() == null) return true;

            boolean usernameExists = userService.findByUserName(user.getName()) != null;
            boolean emailExists = userService.findByUserEmail(user.getEmail()) != null;

            if (usernameExists || emailExists) {
                context.disableDefaultConstraintViolation();
                if (usernameExists) {
                    context.buildConstraintViolationWithTemplate("Name already exists")
                        .addPropertyNode("name")
                        .addConstraintViolation();
                }
                if (emailExists) {
                    context.buildConstraintViolationWithTemplate("Email already exists")
                        .addPropertyNode("email")
                        .addConstraintViolation();
                }
                return false;
            }

            return true;
        } catch (Exception e) {
            log.debug("Erroe in UniqueUserValidator {}",e);
            throw e;
        }
    }

		
}
