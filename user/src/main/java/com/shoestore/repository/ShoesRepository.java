package com.shoestore.repository;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoestore.domain.Shoes;

public interface ShoesRepository extends JpaRepository<Shoes, Long>{

	
	 public List<Shoes> findAllByOrderByIdAsc();
	
	

}
