package org.tacocloud.repository;

import org.tacocloud.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {
    List<Ingredient> findAll();

    Optional<Ingredient> findById(long id);

    Ingredient save(Ingredient ingredient);
}
