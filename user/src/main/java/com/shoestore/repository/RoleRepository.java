package com.shoestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.shoestore.domain.security.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByname(String name);
}
