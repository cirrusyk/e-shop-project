package com.adminportal.service;

import java.util.List;


import com.adminportal.domain.Shoes;

public interface ShoesService {
	Shoes save(Shoes shoes);
	
	List<Shoes> findAll();
	
	Shoes findOne(Long id);
	
	List<Shoes> findByCategory(String category);
	
	

}
