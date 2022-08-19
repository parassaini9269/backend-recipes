package com.digital.abn.recipe.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class IngredientDto {
  /**
   * Ingredient ID
   */
  private Long id;

  /**
   * Ingredient Name
   */
  private String name;

}
