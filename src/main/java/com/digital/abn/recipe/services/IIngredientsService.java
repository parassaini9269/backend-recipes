package com.digital.abn.recipe.services;

import com.digital.abn.recipe.models.IngredientDto;
import com.digital.abn.recipe.models.IngredientResponse;

public interface IIngredientsService {

  IngredientDto createIngredient(IngredientDto ingredient);

  void deleteIngredientById(Long id);

  IngredientResponse getIngredients();

}
