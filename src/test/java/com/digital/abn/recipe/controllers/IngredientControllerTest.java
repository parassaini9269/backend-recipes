package com.digital.abn.recipe.controllers;

import com.digital.abn.recipe.controllers.impl.IngredientsController;
import com.digital.abn.recipe.models.IngredientDto;
import com.digital.abn.recipe.models.IngredientResponse;
import com.digital.abn.recipe.exceptions.ResourceNotFoundException;
import com.digital.abn.recipe.services.impl.IngredientsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IngredientControllerTest {

  @Mock
  IngredientsService ingredientsService;

  @InjectMocks
  IngredientsController ingredientsController;

  @Test
  public void getAllIngredients_shouldCallService() {
    when(ingredientsService.getIngredients()).thenReturn(new IngredientResponse());
    ingredientsController.getAllIngredients();
    verify(ingredientsService, times(1)).getIngredients();
  }

  @Test
  public void addIngredient_shouldCallService() {
    when(ingredientsService.createIngredient(any())).thenReturn(new IngredientDto());
    ingredientsController.addIngredient(new IngredientDto());
    verify(ingredientsService, times(1)).createIngredient(any());
  }

  @Test
  public void deleteIngredient_ok() {
    doNothing().when(ingredientsService).deleteIngredientById(any());
    ResponseEntity<String> responseString = ingredientsController.deleteIngredient(1L);
    Assert.assertEquals(HttpStatus.OK, responseString.getStatusCode());
  }

  @Test
  public void deleteIngredient_throwResourceNotFoundException() {
    doThrow(ResourceNotFoundException.class).when(ingredientsService).deleteIngredientById(any());
    Assert.assertThrows(ResourceNotFoundException.class, () -> ingredientsService.deleteIngredientById(any()));
  }
}
