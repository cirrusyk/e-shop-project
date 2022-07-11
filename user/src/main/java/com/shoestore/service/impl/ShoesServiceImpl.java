package com.shoestore.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoestore.domain.Shoes;
import com.shoestore.repository.ShoesRepository;
import com.shoestore.service.ShoesService;





@Service
public class ShoesServiceImpl implements ShoesService {
	
	
	@Autowired
	private ShoesRepository shoesRepository;
	


	public Shoes save(Shoes shoes) {
		return shoesRepository.save(shoes);
	}
	
	public List<Shoes> findAll() {
		return (List<Shoes>) shoesRepository.findAll();
	}


	

	@Override
	public Shoes findOne(Long id) {
		
		return shoesRepository.findById(id).orElse(null);
	}

	
	public List<Shoes> findByCategory(String category) {
		 List<Shoes> shoesList = (List<Shoes>)shoesRepository.findAll();
		List<Shoes> subShoesList = new ArrayList<Shoes>();
		 Iterator<Shoes> iter = shoesList.iterator();
			
		 while (iter.hasNext()) {
			 Shoes shoes = iter.next(); 
	            if(shoes.getCategory().contains(category)) {
	            	subShoesList.add(shoes);
	            }
	        }
		 	 
		
		return subShoesList;
	}




	

	
	
}
