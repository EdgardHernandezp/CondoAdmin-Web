package com.dreamseeker.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dreamseeker.models.entity.Dwelling;
import com.dreamseeker.models.services.DAOService;

@Controller
public class IndexController {
	
	@Autowired
	DAOService daoService;
		
	@GetMapping("/searchByName")
	public String getByName() {
		return "searchByName";
	}
	
	@PostMapping("/searchByName")
	public String redirectNameResults(String name, String lastName) { 
		return "redirect:/result?name="+name+"&lastName="+lastName;
	}
	
	
	@GetMapping("/searchByDni")
	public String getByApartmentId() {
		return "searchByDni";
	}
	
	@PostMapping("/searchByDni")
	public String getByApartment(String dni, RedirectAttributes redirectAttr) {
		List<Dwelling> dwellings = new ArrayList<Dwelling>();
		dwellings.add(daoService.getDwellingByDNI(dni));
		
		redirectAttr.addFlashAttribute("dwellings", dwellings);
		return "redirect:/result";
	}

	
	@GetMapping("/result")
	public String showResults(@RequestParam("name") String name, @RequestParam("lastName") String lastName, Model model) {
		model.addAttribute("dwellings", daoService.getDwellingByName(name, lastName));
		return "result";
	}
}
