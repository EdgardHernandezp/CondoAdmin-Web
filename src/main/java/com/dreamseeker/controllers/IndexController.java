package com.dreamseeker.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dreamseeker.models.entity.Debt;
import com.dreamseeker.models.entity.Dwelling;
import com.dreamseeker.models.services.DAODebtService;
import com.dreamseeker.models.services.DAOService;

@Controller
public class IndexController {
	
	@Autowired
	DAOService daoService;
	
	@Autowired
	DAODebtService daoDebtService;
		
	@GetMapping("/searchByName")
	public String getByName() {
		return "searchByName";
	}
	
	@PostMapping("/searchByName")
	public String redirectNameResults(String name, String lastName, RedirectAttributes redirectAttr) { 
		List<Dwelling> dwellings = daoService.getDwellingByName(name, lastName);
		
		redirectAttr.addFlashAttribute("dwellings", dwellings);
		return "redirect:/result";
	}
	
	
	@GetMapping("/searchByDni")
	public String getByDni() {
		return "searchByDni";
	}
	
	@PostMapping("/searchByDni")
	public String redirectDniResult(String dni, RedirectAttributes redirectAttr) {
		List<Dwelling> dwellings = new ArrayList<Dwelling>();
		dwellings.add(daoService.getDwellingByDNI(dni));
		
		redirectAttr.addFlashAttribute("dwellings", dwellings);
		return "redirect:/result";
	}

	
	
	@GetMapping("/searchByApartmentId")
	public String getByApartmentId() {
		return "searchByApartmentId";
	}
	
	@PostMapping("/searchByApartmentId")
	public String redirectApartmentIdResults(String apartmentId, RedirectAttributes redirectAttr) {
		List<Dwelling> dwellings = new ArrayList<Dwelling>();
		dwellings.add(daoService.getDwellingByApartmentId(apartmentId)); //TODO cambiar nombre de metodos ('find' en lugar de 'get')
		
		redirectAttr.addFlashAttribute("dwellings", dwellings);
		return "redirect:/result";
	}
	
	
	@GetMapping("/result")
	public String showResults(@ModelAttribute("dwellings") List<Dwelling> dwellings , Model model) {
		model.addAttribute("dwellings", dwellings); //TODO probar si es necesario guardar en el modelo nuevamente ¿Sigue el modelAtributte en el modelo?
		return "result";
	}
	
	@GetMapping("/debtOverview/{id}")
	public String showDebtOverview(@PathVariable("id") String apartmentId, Model model) {
		List<Debt> debts = daoDebtService.findDebtsByApartmentId(apartmentId);
		model.addAttribute("debts", debts);
		return "debtOverview";
	}
}
