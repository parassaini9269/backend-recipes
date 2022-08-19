package com.digital.abn.recipe.repositories;

import com.digital.abn.recipe.models.Ingredient;
import com.digital.abn.recipe.models.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;


@RunWith(SpringRunner.class)
@DataJpaTest
public class IngredientRepositoryTest {

  @Autowired
  IngredientRepository ingredientRepository;

  @Before
  public void setUp() {}

  @Test
  public void findAllByRecipeTest() {
    List<Ingredient> ingredientList = ingredientRepository.findAllByRecipe(createRecipe());
    Assert.assertEquals(3, ingredientList.size());
  }

  private Recipe createRecipe() {
    Recipe recipe = new Recipe();
    recipe.setRecipeName("Pasta");
    recipe.setId(1L);
    recipe.setInstructions("Pasta with salad");
    recipe.setNumberOfServings(2);
    recipe.setVegetarian(TRUE);
    recipe.setIngredient(createIngredients());
    return recipe;
  }

  private List<Ingredient> createIngredients() {
    List<Ingredient> ingredientList = new ArrayList<>();
    Ingredient ingredient1 = new Ingredient();
    ingredient1.setIngredientName("pasta");
    ingredientList.add(ingredient1);

    Ingredient ingredient2 = new Ingredient();
    ingredient2.setIngredientName("lettuce");
    ingredientList.add(ingredient2);
    return ingredientList;
  }

}
