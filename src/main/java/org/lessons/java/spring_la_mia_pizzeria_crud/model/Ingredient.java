package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The name field must be popolized!")
    @Size(min = 3, message = "The name must have at least 3 chars")
    private String name;

    @ManyToMany( mappedBy = "ingredients" )
    private List<Pizza> pizzas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Pizza> getPizzas() {
        return pizzas;
    }
    
    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
