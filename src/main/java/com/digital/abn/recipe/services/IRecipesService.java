package com.digital.abn.recipe.services;

import com.digital.abn.recipe.models.RecipeResponse;
import com.digital.abn.recipe.models.Recipe;

public interface IRecipesService {

  Recipe createRecipe(Recipe recipe);

  Recipe updateRecipe(Long id, Recipe recipe);

  void deleteRecipeById(Long id);

  RecipeResponse getRecipes();

  RecipeResponse getRecipesBySearch(Boolean isVegetarian, Integer noOfServings, String includeIngredients,
                                               String excludeIngredients, String searchInstructions);

}
