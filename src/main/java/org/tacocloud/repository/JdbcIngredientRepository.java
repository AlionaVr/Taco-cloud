package org.tacocloud.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.tacocloud.Ingredient;
import org.tacocloud.Type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ingredient> findAll() {
        return jdbcTemplate.query("select id, name, type from Ingredient",
                this::mapRowToIngredient);
    }

    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
        return new Ingredient(
                row.getString("id"),
                row.getString("name"),
                Type.valueOf(row.getString("type")));
    }

    @Override
    public Optional<Ingredient> findById(long id) {
        List<Ingredient> ingredients = jdbcTemplate.query(
                "select id, name, type from Ingredient where id = ?",
                this::mapRowToIngredient,
                id);
        return ingredients.isEmpty() ? Optional.empty() : Optional.of(ingredients.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }
}
