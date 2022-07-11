package com.adminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.adminportal.domain.Shoes;
import com.adminportal.repository.ShoesRepository;
import com.adminportal.service.ShoesService;

@Controller
public class ShoesController {
	
	@Autowired
	private ShoesService shoesService;
	
	@Autowired
	private ShoesRepository shoesRepository;
	
	@RequestMapping(value="/addProduct", method=RequestMethod.GET)
	public String addShoes(Model model) {
		Shoes shoes = new Shoes();
		model.addAttribute("shoes", shoes);
		return "addProduct";
	}
	
	@RequestMapping(value="/addProduct", method=RequestMethod.POST)
	public String addShoesPost(
			@ModelAttribute("shoes") Shoes shoes, HttpServletRequest request
			) {
		shoesService.save(shoes);
		
		MultipartFile shoesImage = shoes.getShoesImage();
		
		try {
			byte[] bytes = shoesImage.getBytes();
			String name = shoes.getId() + ".png";
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/shoes/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:manageProducts";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	@RequestMapping("/basketBallShoes")
	public String basketBallShoes(Model model){
		 List<Shoes> subshoesList = shoesService.findByCategory("basketball");  
	    model.addAttribute("shoesList", subshoesList);
		return "basketBallShoes";
	}
	
	@RequestMapping("/runningShoes")
	public String runningShoes(Model model){
		 List<Shoes> subshoesList = shoesService.findByCategory("running");  
		    model.addAttribute("shoesList", subshoesList);
			return "runningShoes";
	}
	
	@RequestMapping("/skateShoes")
	public String skateShoes(Model model){
		 List<Shoes> subshoesList = shoesService.findByCategory("skate");  
		    model.addAttribute("shoesList", subshoesList);
			return "skateShoes";
		
	}
	
	
	@GetMapping("/deleteProducts")
	public String deleteProducts(@RequestParam Long shoesId) {
		shoesRepository.deleteById(shoesId);
		return "redirect:/manageProducts";
	}
	
	
	
	
	@RequestMapping("/shoesInfo")
	public String shoesInfo(@RequestParam("id") Long id, Model model){
		Shoes shoes = shoesService.findOne(id);
		model.addAttribute("shoes", shoes);
		
		return "shoesInfo";
	}
	
	
	@RequestMapping(value="/updateShoes", method=RequestMethod.POST)
	public String updateShoesPost(@ModelAttribute("shoes") Shoes shoes, HttpServletRequest request){
		shoesService.save(shoes);
		
		MultipartFile shoesImage = shoes.getShoesImage();
		
		if(!shoesImage.isEmpty()) {
			try {
				byte[] bytes = shoesImage.getBytes();
				String name = shoes.getId() + ".png"; 
				
				Files.delete(Paths.get("src/main/resources/static/image/shoes/"+name)); 
				
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/image/shoes/" + name)));
				stream.write(bytes);
				stream.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/shoesInfo?id="+shoes.getId();
		
	}
	
	
	@RequestMapping("/updateShoes")
	public String updateShoes(@RequestParam("id") Long id, Model model){
		Shoes shoes = shoesService.findOne(id);
		model.addAttribute("shoes", shoes);
		
		return "updateShoes";
	}
	
	
	
	/* @RequestMapping(value = "/manageProducts", method = RequestMethod.GET)
    public String manageProductsGet(Model uiModel, Pageable pageable) {
        
        PageWrapper<Shoes> page = new PageWrapper<Shoes>
            (shoesService.getAllPublishedPosts(pageable), "/manageProducts");
        uiModel.addAttribute("page", page);
        return "manageProducts";
    }
	
	*/
	

	@RequestMapping("/manageProducts")
    public String manageProducts(Model model) {
       List<Shoes> shoesList = shoesService.findAll();
       model.addAttribute("shoesList", shoesList);
       return "manageProducts";
       
    }
	
	

}
