package com.adminportal.clients;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;




public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	@Autowired
	private ClientRepository clientRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Clients clients  = clientRepository.findByUsername(username);
		
		
		if (clients == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new CustomUserDetails(clients);
	} 

}
