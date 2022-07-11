package com.adminportal.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import com.adminportal.domain.Shoes;

public interface ShoesRepository extends JpaRepository<Shoes, Long>{

	
	
}
