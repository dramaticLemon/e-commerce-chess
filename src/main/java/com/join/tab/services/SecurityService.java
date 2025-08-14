package com.join.tab.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityService{
	
	private final AuthenticationManager authManager;
	// private static final Logger log = LoggerFactory.getLogger(SecurityService.class);

	public SecurityService(
		AuthenticationManager authManager,
		UserDetailsService userDetailsService) {
		this.authManager = authManager;
	}

	public String findLoggedInUsername() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();

		if(userDetails  instanceof UserDetails userDetails1) {
			return userDetails1.getUsername();
		}
		return null;

	}
	
	public void autoLogin(String username, String rawPassword) {
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(username, rawPassword);
		
		var auth = authManager.authenticate(authenticationToken);
		
		if (auth.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
	}
}
