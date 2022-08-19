package com.digital.abn.recipe.models;

import java.util.List;

public class IngredientResponse {
  /**
   * List of all ingredients
   */
  private List<IngredientDto> ingredients;

  public List<IngredientDto> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<IngredientDto> ingredients) {
    this.ingredients = ingredients;
  }
}
