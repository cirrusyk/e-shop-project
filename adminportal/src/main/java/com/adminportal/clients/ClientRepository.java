package com.adminportal.clients;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;





public interface ClientRepository  extends JpaRepository<Clients, Long> {

	@Query("SELECT u FROM User u WHERE u.username = ?1")
	public Clients findByUsername(String username);
	
	
}
