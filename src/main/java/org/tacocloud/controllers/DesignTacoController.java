package org.tacocloud.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.tacocloud.Ingredient;
import org.tacocloud.Taco;
import org.tacocloud.TacoOrder;
import org.tacocloud.Type;
import org.tacocloud.repository.IngredientRepository;

import java.util.List;
import java.util.stream.Collectors;

// @Slf4j - Simple Logging Facade for Java, generate Logger  static  property
//    private static final org.slf4j.Logger log =
//    org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder") //  save the order in the session so that it can span multiple requests
public class DesignTacoController {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    @ModelAttribute
    public void addIngredientToModel(Model model) {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        Type[] ingredientTypes = Type.values();
        for (Type type : ingredientTypes) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processDesignForm(@Valid Taco taco,
                                    Errors errors,
                                    @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }
}
