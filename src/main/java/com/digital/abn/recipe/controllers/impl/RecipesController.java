package com.digital.abn.recipe.controllers.impl;

import com.digital.abn.recipe.controllers.IRecipeController;
import com.digital.abn.recipe.models.RecipeResponse;
import com.digital.abn.recipe.models.Recipe;
import com.digital.abn.recipe.services.impl.RecipesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.digital.abn.recipe.constants.ApplicationConstants.INSIDE_METHOD;
import static com.digital.abn.recipe.constants.ApplicationConstants.RECIPE_DELETED;

@RestController
public class RecipesController implements IRecipeController {

  private static final Logger LOG = LoggerFactory.getLogger(RecipesController.class);

  private final RecipesService recipesService;

  public RecipesController(RecipesService recipesService) {
    this.recipesService = recipesService;
  }

  @Override
  public ResponseEntity<Recipe> addRecipe(@Valid Recipe recipe) {
    LOG.info("[addRecipe]" + INSIDE_METHOD);
    recipesService.createRecipe(recipe);
    return new ResponseEntity<>(recipe, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Recipe> updateRecipe(@Valid Long id, @Valid Recipe recipe) {
    LOG.info("[updateRecipe]" + INSIDE_METHOD);
    recipesService.updateRecipe(id, recipe);
    return new ResponseEntity<>(recipe, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<String> deleteRecipe(@Valid Long id) {
    LOG.info("[deleteRecipe]" + INSIDE_METHOD);
    recipesService.deleteRecipeById(id);
    return new ResponseEntity<>(RECIPE_DELETED, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<RecipeResponse> getAllRecipes() {
    LOG.info("[getAllRecipes]" + INSIDE_METHOD);
    RecipeResponse recipeResponse = recipesService.getRecipes();
    return new ResponseEntity<>(recipeResponse, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<RecipeResponse> getRecipesBySearch(@Valid Boolean isVeg,
                                               @Valid Integer servings,
                                               @Valid String includeIngredients,
                                               @Valid String excludeIngredients,
                                               @Valid String searchInstructions) {
    LOG.info("[getRecipesBySearch]" + INSIDE_METHOD);
    RecipeResponse recipeResponse = recipesService.getRecipesBySearch(isVeg, servings, includeIngredients, excludeIngredients, searchInstructions);
    return new ResponseEntity<>(recipeResponse, HttpStatus.OK);
  }

}