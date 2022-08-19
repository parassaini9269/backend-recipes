package com.digital.abn.recipe.controllers.impl;

import com.digital.abn.recipe.controllers.IIngredientController;
import com.digital.abn.recipe.models.IngredientDto;
import com.digital.abn.recipe.models.IngredientResponse;
import com.digital.abn.recipe.services.IIngredientsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.digital.abn.recipe.constants.ApplicationConstants.INGREDIENT_DELETED;
import static com.digital.abn.recipe.constants.ApplicationConstants.INSIDE_METHOD;

@RestController
public class IngredientsController implements IIngredientController {

  private static final Logger LOG = LoggerFactory.getLogger(IngredientsController.class);

  private final IIngredientsService ingredientsService;

  public IngredientsController(IIngredientsService ingredientsService) {
    this.ingredientsService = ingredientsService;
  }

  @Override
  public ResponseEntity<IngredientDto> addIngredient(@Valid IngredientDto ingredient) {
    LOG.info("[addIngredient]" + INSIDE_METHOD);
    ingredientsService.createIngredient(ingredient);
    return new ResponseEntity<>(ingredient, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<String> deleteIngredient(@Valid Long id) {
    LOG.info("[deleteIngredient]" + INSIDE_METHOD);
    ingredientsService.deleteIngredientById(id);
    return new ResponseEntity<>(INGREDIENT_DELETED, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<IngredientResponse> getAllIngredients() {
    LOG.info("[getAllIngredients]" + INSIDE_METHOD);
    IngredientResponse ingredientResponse = ingredientsService.getIngredients();
    return new ResponseEntity<>(ingredientResponse, HttpStatus.OK);
  }

}