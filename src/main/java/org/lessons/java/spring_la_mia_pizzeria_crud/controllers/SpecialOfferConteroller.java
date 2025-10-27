package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/specialoffer")
public class SpecialOfferConteroller {

    @Autowired
    private SpecialOfferRepository repository;
    
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            return "specialoffers/create";
        }
        
        repository.save(formSpecialOffer);

        return "redirect:/pizzas/";
    }
    
    
}
