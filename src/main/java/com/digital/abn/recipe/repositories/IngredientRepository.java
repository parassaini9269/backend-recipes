package com.digital.abn.recipe.repositories;

import com.digital.abn.recipe.models.Ingredient;
import com.digital.abn.recipe.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    List<Ingredient> findAllByRecipe(Recipe recipe);
}
