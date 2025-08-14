package com.join.tab.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.join.tab.domain.Role;
import com.join.tab.domain.User;
import com.join.tab.dto.UserDto;
import com.join.tab.repository.RoleRepository;
import com.join.tab.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserService(
		UserRepository userRepository,
		RoleRepository roleRepository,
		BCryptPasswordEncoder bCryptPasswordEncoder) {
		 
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Transactional
	public void save(UserDto userDto) {
		userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findRoleById(1L));
		User user  = mapToEntity(userDto);
		user.setRoles(roles);
		userRepository.save(user);
		
	}
	
	@Transactional(readOnly=true)
	public User findByUserName(String username) {
		return userRepository.findByUserName(username);
	}

	@Transactional(readOnly=true)
	public User findByUserEmail(String username) {
		return userRepository.findByUserEmail(username);
	}

	private User mapToEntity(UserDto user2) {
		User user = new User();
		user.setName(user2.getName());
		user.setEmail(user2.getEmail());
		user.setPassword(user2.getPassword());

		return user;
}
}
