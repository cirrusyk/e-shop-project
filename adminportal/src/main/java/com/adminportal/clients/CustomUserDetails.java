package com.adminportal.clients;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;



public class CustomUserDetails implements org.springframework.security.core.userdetails.UserDetails {
	
	private Clients clients;
	
	
	public CustomUserDetails(Clients clients) {
		 
		this.clients = clients;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return null;
	}

	@Override
	public String getPassword() {
		
		return clients.getPassword();
	}

	@Override
	public String getUsername() {
	
		return clients.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
