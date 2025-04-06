package org.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import org.tacocloud.entity.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
