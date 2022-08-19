package com.digital.abn.recipe.controllers;

import com.digital.abn.recipe.controllers.impl.RecipesController;
import com.digital.abn.recipe.models.RecipeResponse;
import com.digital.abn.recipe.exceptions.ResourceNotFoundException;
import com.digital.abn.recipe.models.Recipe;
import com.digital.abn.recipe.services.impl.RecipesService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class RecipeControllerTest {

  @Mock
  RecipesService recipesService;

  @InjectMocks
  RecipesController recipesController;

  @Test
  public void getAllRecipes_shouldCallService() {
    when(recipesService.getRecipes()).thenReturn(new RecipeResponse());
    recipesController.getAllRecipes();
    verify(recipesService, times(1)).getRecipes();
  }

  @Test
  public void getRecipesBySearch_shouldCallService() {
    when(recipesService.getRecipesBySearch(any(), any(), any(), any(), any())).thenReturn(new RecipeResponse());
    recipesController.getRecipesBySearch(Boolean.TRUE, 4, "cheese", "zuccini", "oven");
    verify(recipesService, times(1)).getRecipesBySearch(any(), any(), any(), any(), any());
  }

  @Test
  public void addRecipe_shouldCallService() {
    when(recipesService.createRecipe(any())).thenReturn(new Recipe());
    recipesController.addRecipe(new Recipe());
    verify(recipesService, times(1)).createRecipe(any());
  }

  @Test
  public void addRecipe_ok() {
    when(recipesService.createRecipe(any())).thenReturn(new Recipe());
    ResponseEntity<Recipe> responseString = recipesController.addRecipe(new Recipe());
    Assert.assertEquals(HttpStatus.CREATED, responseString.getStatusCode());
  }

  @Test
  public void updateRecipe_shouldCallService() {
    when(recipesService.updateRecipe(any(), any())).thenReturn(new Recipe());
    recipesController.updateRecipe(1L, new Recipe());
    verify(recipesService, times(1)).updateRecipe(any(), any());
  }

  @Test
  public void updateRecipe_ok() {
    when(recipesService.updateRecipe(any(), any())).thenReturn(new Recipe());
    ResponseEntity<Recipe> responseString = recipesController.updateRecipe(1L, new Recipe());
    Assert.assertEquals(HttpStatus.OK, responseString.getStatusCode());
  }

  @Test
  public void updateRecipe_throwJsonParseException() {
    doThrow(JsonParseException.class).when(recipesService).updateRecipe(any(), any());
    Assert.assertThrows(JsonParseException.class, () -> recipesService.updateRecipe(any(), any()));
  }

  @Test
  public void deleteRecipe_ok() {
    doNothing().when(recipesService).deleteRecipeById(any());
    ResponseEntity<String> responseString = recipesController.deleteRecipe(1L);
    Assert.assertEquals(HttpStatus.OK, responseString.getStatusCode());
  }

  @Test
  public void deleteRecipe_throwResourceNotFoundException() {
    doThrow(ResourceNotFoundException.class).when(recipesService).deleteRecipeById(any());
    Assert.assertThrows(ResourceNotFoundException.class, () -> recipesService.deleteRecipeById(any()));
  }
}
