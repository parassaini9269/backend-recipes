package com.digital.abn.recipe.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class RecipeDto {

  /**
   * Recipe ID
   */
  private Long id;

  /**
   * Recipe recipeName
   */
  private String recipeName;

  /**
   * Recipe vegitarian
   */
  private Boolean vegetarian;

  /**
   * Recipe numberOfServings
   */
  private int numberOfServings;

  /**
   * Recipe instructions
   */
  private String instructions;

  /**
   * List of all ingredients in this Recipe
   */
  private List<IngredientDto> ingredients;

}
