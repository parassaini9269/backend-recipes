package com.digital.abn.recipe.services;

import com.digital.abn.recipe.models.IngredientDto;
import com.digital.abn.recipe.repositories.IngredientRepository;
import com.digital.abn.recipe.models.Ingredient;
import com.digital.abn.recipe.services.impl.IngredientsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class IngredientsServiceTest {

  @InjectMocks
  IngredientsService ingredientsService;

  @Mock
  IngredientRepository ingredientRepository;

  @Test
  public void getIngredientsTest() {
    when(ingredientRepository.findAll()).thenReturn(createIngredientsRequest());
    Assert.assertEquals(1, ingredientsService.getIngredients().getIngredients().size());
  }

  @Test
  public void createIngredientTest() {
    when(ingredientRepository.findById(any())).thenReturn(Optional.of(createIngredientRequest()));
    when(ingredientRepository.save(any())).thenReturn(createIngredientRequest());
    Assert.assertEquals(createIngredientRequest().getIngredientName(), ingredientsService.createIngredient(createIngredientDtoRequest()).getName());
  }

  @Test
  public void deleteIngredientByIdTest() {
    when(ingredientRepository.findById(any())).thenReturn(Optional.of(createIngredientRequest()));
    ingredientsService.deleteIngredientById(1L);
    verify(ingredientRepository, times(1)).delete(any());
  }

  private List<Ingredient> createIngredientsRequest() {
    List<Ingredient> ingredientList = new ArrayList<>();
    Ingredient ingredient = createIngredientRequest();
    ingredientList.add(ingredient);
    return ingredientList;
  }

  private Ingredient createIngredientRequest() {
    Ingredient ingredient = new Ingredient();
    ingredient.setIngredientName("zuccini");
    return ingredient;
  }

  private IngredientDto createIngredientDtoRequest() {
    IngredientDto ingredientDto = new IngredientDto();
    ingredientDto.setName("zuccini");
    return ingredientDto;
  }

}
