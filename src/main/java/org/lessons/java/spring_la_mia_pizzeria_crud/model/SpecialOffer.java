package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "special_offer")
public class SpecialOffer {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "The start date must be inizialized")
    private LocalDate startOfferDate;

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "The end date must be inizialized")
    private LocalDate endOfferDate;

    @NotBlank(message = "The title must not be null, nor empty or blank")
    private String title;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    public LocalDate getEndOfferDate() {
        return endOfferDate;
    }

    public void setEndOfferDate(LocalDate endOfferDate) {
        this.endOfferDate = endOfferDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public LocalDate getStartOfferDate() {
        return startOfferDate;
    }

    public void setStartOfferDate(LocalDate startOfferDate) {
        this.startOfferDate = startOfferDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
