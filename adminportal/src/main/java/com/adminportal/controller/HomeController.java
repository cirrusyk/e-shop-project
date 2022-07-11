package com.adminportal.controller;

import java.security.Principal;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adminportal.clients.ClientRepository;
import com.adminportal.clients.Clients;


@Controller
public class HomeController {
	
	@Autowired
	private ClientRepository clientRepository;
	

	@RequestMapping("/")
	public String index(){
		return "redirect:/manageUsers";
	}
	
	@RequestMapping("/manageUsers")
	public String home(Model model){
		 List<Clients> listClients = clientRepository.findAll();
		    model.addAttribute("listClients", listClients);
		return "manageUsers";
	}
	
	
	
	
	@GetMapping("/deleteClients")
	public String deleteClients(@RequestParam Long clientsId) {
		clientRepository.deleteById(clientsId);
		return "redirect:/manageUsers";
	}
	
	@RequestMapping("/manageOrders")
	public String manageOrders(){
		return "manageOrders";
	}
	
	
	
	@RequestMapping("/newReleases")
	public String newReleases(){
		return "newReleases";
	}
	
	
	
	@RequestMapping("/about")
	public String about(){
		return "about";
	}
	
	
	
	
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
}
