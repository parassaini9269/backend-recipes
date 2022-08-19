package com.digital.abn.recipe.models;

import java.util.List;

public class RecipeResponse {
  /**
   * List of all recipes
   */
  private List<RecipeDto> recipes;

  public List<RecipeDto> getRecipes() {
    return recipes;
  }

  public void setRecipes(List<RecipeDto> recipes) {
    this.recipes = recipes;
  }
}
