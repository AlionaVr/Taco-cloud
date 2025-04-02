package org.tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tacocloud.Ingredient;
import org.tacocloud.IngredientType;
import org.tacocloud.Taco;
import org.tacocloud.TacoOrder;

import java.util.Arrays;
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

    @ModelAttribute
    public void addIngredientToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP),
                new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP),
                new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN),
                new Ingredient("CARN", "Carnitas", IngredientType.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", IngredientType.VEGGIES),
                new Ingredient("LETC", "Lettuce", IngredientType.VEGGIES),
                new Ingredient("CHED", "Cheddar", IngredientType.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE),
                new Ingredient("SLSA", "Salsa", IngredientType.SAUCE),
                new Ingredient("SRCR", "Sour Cream", IngredientType.SAUCE)
        );
        IngredientType[] ingredientTypes = IngredientType.values();
        for (IngredientType type : ingredientTypes) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));

        }
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, IngredientType type) {
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
    public String processDesignForm(Taco taco, @ModelAttribute TacoOrder tacoOrder) {
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }
}
