package com.join.tab.dto;

import com.join.tab.validator.PasswordMaches;
import com.join.tab.validator.UniqueUser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@PasswordMaches
@UniqueUser
public class UserDto {
	@NotBlank
	@Size(min=2, max=50, message="Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message="Email cannot be empty")
    @Email(message="Email must be a valid format")
    private String email;

    @NotBlank(message="Passwors cannot be empty")
	@Size(min=8, message="Password must be at least 8 characters")
    private String password;

    @NotBlank
    private String confirmPassword;

	public UserDto() {
	}

	public UserDto(@NotBlank String name, @NotBlank @Email String email, @NotBlank String password,
			@NotBlank String confirmPassword) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	
}
