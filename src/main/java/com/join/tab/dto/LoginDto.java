package com.join.tab.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginDto {
	
	@NotBlank(message="Username cannot be empty")
	private String username;

	@NotBlank(message="Password coannot by empty")
	private String password;

	public LoginDto() {
	}

	public LoginDto(@NotBlank String username, @NotBlank(message = "Password coannot by empty") String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
