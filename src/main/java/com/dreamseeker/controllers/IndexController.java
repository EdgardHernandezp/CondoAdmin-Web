package com.dreamseeker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dreamseeker.models.dao.DwellingRepository;
import com.dreamseeker.models.entity.Dwelling;
import com.dreamseeker.models.services.DAOService;

@Controller
public class IndexController {
	
	@Autowired
	DAOService daoService;
		
	@GetMapping("/searchByName")
	public String greeting() {
		return "searchByName";
	}
	
	@PostMapping("/searchByName")
	public String getSearchResults(String name, String lastName) { 
		return "redirect:/result?name="+name+"&lastName="+lastName;
	}
	
	@GetMapping("/result")
	public String showResults(@RequestParam("name") String name, @RequestParam("lastName") String lastName, Model model) {
		model.addAttribute("dwellings", daoService.getDwellingByName(name, lastName));
		return "result";
	}
}
