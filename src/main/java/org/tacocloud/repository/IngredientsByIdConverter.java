package org.tacocloud.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.tacocloud.Ingredient;

@Component
public class IngredientsByIdConverter implements Converter<Long, Ingredient> {
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientsByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }
}
