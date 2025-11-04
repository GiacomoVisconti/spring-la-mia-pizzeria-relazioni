package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "ingredients/index";
    }

    @GetMapping("/searchByName")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {

        model.addAttribute("ingredients", ingredientRepository.findByNameContaining(name));

        return "ingredients/index";
    }
    
        @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        return "ingredients/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create-edit";
    }
    
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            return "ingredients/create-edit";
        }
        
        ingredientRepository.save(formIngredient);
    
        return "redirect:/ingredients/";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        model.addAttribute("edit", true);

        return "ingredients/create-edit";
    }
    
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-edit";
        }
        ingredientRepository.save(formIngredient);
        return "redirect:/ingredients/";
    }
    
    @PostMapping("/delete/{id}")
    public String postMethodName(@PathVariable Integer id) {
        
        Ingredient ingredientToDelete = ingredientRepository.findById(id).get();

        for (Pizza linkedPizza : ingredientToDelete.getPizzas()) {
            linkedPizza.getIngredients().remove(ingredientToDelete);
        }
        
        ingredientRepository.delete(ingredientToDelete);

        return "redirect:/ingredients/";
    }
    


}
