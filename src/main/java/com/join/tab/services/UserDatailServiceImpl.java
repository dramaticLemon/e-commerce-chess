package com.join.tab.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.join.tab.domain.User;
import com.join.tab.repository.UserRepository;

@Service
public class UserDatailServiceImpl implements UserDetailsService{
	
	private final UserRepository userRepository;

	public UserDatailServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUserName(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found : " + username);
		}
		return user;
	}


}
