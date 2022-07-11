package com.shoestore.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shoestore.domain.CustomUserDetails;
import com.shoestore.domain.User;
import com.shoestore.repository.UserRepository;



public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user  = repository.findByUsername(username);
		
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new CustomUserDetails(user);
	} 
	
	public User findOne(Long id) {
		return repository.findById(id).orElse(null);
	}

	public User save(User user) {
		return repository.save(user);
		
	}
}
