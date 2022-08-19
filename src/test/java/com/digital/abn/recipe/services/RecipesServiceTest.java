package com.digital.abn.recipe.services;

import com.digital.abn.recipe.repositories.IngredientRepository;
import com.digital.abn.recipe.repositories.RecipeRepository;
import com.digital.abn.recipe.models.Ingredient;
import com.digital.abn.recipe.models.Recipe;
import com.digital.abn.recipe.services.impl.RecipesService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.TRUE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class RecipesServiceTest {

  @InjectMocks
  RecipesService recipesService;

  @Mock
  RecipeRepository recipeRepository;

  @Mock
  IngredientRepository ingredientRepository;

  @Test
  public void updateRecipeTest() {
    when(recipeRepository.findById(any())).thenReturn(Optional.of(createRecipe()));
    when(recipeRepository.save(any())).thenReturn(createRecipe());
    Assert.assertEquals(createRecipe().getId(), recipesService.updateRecipe(1L, createRecipe()).getId());
  }

  @Test
  public void createRecipeTest() {
    when(ingredientRepository.findById(any())).thenReturn(Optional.of(createIngredients().get(0)));
    when(recipeRepository.save(any())).thenReturn(createRecipe());
    Assert.assertEquals(createRecipe().getId(), recipesService.createRecipe(createRecipe()).getId());
  }

  @Test
  public void deleteRecipeByIdTest() {
    when(recipeRepository.findById(any())).thenReturn(Optional.of(createRecipe()));
    recipesService.deleteRecipeById(createRecipe().getId());
    verify(recipeRepository, times(1)).delete(any());
  }

  @Test
  public void getRecipesTest() {
    when(recipeRepository.findAll()).thenReturn(createRecipes());
    when(ingredientRepository.findAllByRecipe(createRecipe())).thenReturn(createIngredients());
    Assert.assertEquals(1, recipesService.getRecipes().getRecipes().size());
  }

  @Test
  public void getRecipesBySearchTest() {
    Specification<Recipe> specificationRecipe = Specification.where(any());
    when(recipeRepository.findAll(specificationRecipe)).thenReturn(createRecipes());
    when(ingredientRepository.findAllByRecipe(createRecipe())).thenReturn(createIngredients());
    Assert.assertEquals(1, recipesService.getRecipesBySearch(TRUE, 2, null, null, "curry").getRecipes().size());
  }

  private List<Recipe> createRecipes() {
    List<Recipe> recipeList = new ArrayList<>();
    Recipe recipe = createRecipe();
    recipeList.add(recipe);
    return recipeList;
  }

  private Recipe createRecipe() {
    Recipe recipe = new Recipe();
    recipe.setRecipeName("Palak Paneer");
    recipe.setId(1L);
    recipe.setInstructions("Spinach and cheese curry");
    recipe.setNumberOfServings(2);
    recipe.setVegetarian(TRUE);
    recipe.setIngredient(createIngredients());

    return recipe;
  }

  private List<Ingredient> createIngredients() {
    List<Ingredient> ingredientList = new ArrayList<>();
    Ingredient ingredient1 = new Ingredient();
    ingredient1.setIngredientName("cheese");
    ingredientList.add(ingredient1);

    Ingredient ingredient2 = new Ingredient();
    ingredient2.setIngredientName("spinach");
    ingredientList.add(ingredient2);
    return ingredientList;
  }
}
