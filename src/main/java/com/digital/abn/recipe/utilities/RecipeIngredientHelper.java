package com.digital.abn.recipe.utilities;

import com.digital.abn.recipe.models.IngredientDto;
import com.digital.abn.recipe.models.Ingredient;

public class RecipeIngredientHelper {

  /**
   * mapToIngredientDto, maps entity to Dto.
   */
  public static IngredientDto mapToIngredientDto(Ingredient ingredient){
    IngredientDto ingredientDto = new IngredientDto();
    ingredientDto.setId(ingredient.getId());
    ingredientDto.setName(ingredient.getIngredientName());
    return ingredientDto;
  }

  /**
   * mapToIngredientEntity, maps Dto to entıty.
   */
  public static Ingredient mapToIngredientEntity(IngredientDto ingredientDto){
    Ingredient ingredient = new Ingredient();
    ingredient.setIngredientName(ingredientDto.getName());
    return ingredient;
  }
}
