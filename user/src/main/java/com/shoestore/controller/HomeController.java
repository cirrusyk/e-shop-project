package com.shoestore.controller;


import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.websocket.server.PathParam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.shoestore.domain.Shoes;
import com.shoestore.domain.User;
import com.shoestore.repository.UserRepository;

import com.shoestore.service.ShoesService;




@Controller
public class HomeController {
	
	@Autowired
	private UserRepository repository;
	
	
	
	@Autowired
	private ShoesService shoesService;
	
	
	
	@GetMapping("/")
	public String index(Model model) {
		List<Shoes> shoesList = shoesService.findAll();
		
	
		
		model.addAttribute("shoesList", shoesList);
		return "index";
	}
	
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
	    model.addAttribute("user", new User());
	     
	    return "myAccount";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user, RedirectAttributes redirectAttributes) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	     
	    repository.save(user);
	     
	    return "myAccount";
	}
	
	@GetMapping("/login")
	 public String showLoginForm() {
		 return "login";
	 }
	
	
	
		
		
	@RequestMapping("/profile")
	public String updateProfile(Model model){
		
		return "profile";
	}
	
		
	
	
	
	
	
	
	
	
	@RequestMapping("/newReleases")
	public String newReleases(Model model) {
		
		List<Shoes> shoesList = shoesService.findAll();
		
	
		
		model.addAttribute("shoesList", shoesList);
		
		
		return "newReleases";
	}
	
	
	
	@RequestMapping("/productPage")
	public String productPage(
			@PathParam("id") Long id, Model model, Principal principal
			
			) {
		
		
		Shoes shoes = shoesService.findOne(id);
		
		model.addAttribute("shoes", shoes);
		
		List<Integer> qtyList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		model.addAttribute("qtyList", qtyList);
		model.addAttribute("qty", 1);
		
		
		return "productPage";
		
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
	
	
	
	
	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	
	@RequestMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	
	@RequestMapping("/trackOrdersUser")
	public String trackOrdersUser() {
		return "trackOrdersUser";
	}
	
	
	
	
	
	@RequestMapping("/payment")
	public String payment() {
		return "payment";
	}
	
	@RequestMapping("/confirmPayment")
	public String  confirmPayment() {
		return "confirmPayment";
	}
	
	@RequestMapping("/cart")
	public String  cart() {
		return "cart";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
