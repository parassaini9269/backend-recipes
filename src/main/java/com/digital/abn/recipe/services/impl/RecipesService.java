package com.digital.abn.recipe.services.impl;

import com.digital.abn.recipe.specification.RecipeSpecification;
import com.digital.abn.recipe.models.RecipeDto;
import com.digital.abn.recipe.models.RecipeResponse;
import com.digital.abn.recipe.exceptions.ResourceNotFoundException;
import com.digital.abn.recipe.repositories.RecipeRepository;
import com.digital.abn.recipe.repositories.IngredientRepository;
import com.digital.abn.recipe.models.Ingredient;
import com.digital.abn.recipe.models.Recipe;
import com.digital.abn.recipe.services.IRecipesService;
import com.digital.abn.recipe.utilities.RecipeIngredientHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.digital.abn.recipe.constants.ApplicationConstants.*;

@Service
public class RecipesService implements IRecipesService{

		private static final Logger LOG = LoggerFactory.getLogger(RecipesService.class);

		@Autowired
		RecipeRepository recipeRepository;

		@Autowired
		IngredientRepository ingredientRepository;

	/**
	 * createRecipe, adds a new recipe to database.
	 */
		@Override
		public Recipe createRecipe(Recipe recipe) {
				LOG.info("[createRecipe]" + INSIDE_METHOD);

				List<Ingredient> ingredients = new ArrayList<>();
				for (Ingredient data : recipe.getIngredient()) {
						Ingredient ingredient = ingredientRepository.findById(data.getId())
								.orElseThrow(() -> new ResourceNotFoundException(INGREDIENT, "id", data.getId()));
								ingredients.add(data);
				}
				recipe.setIngredient(ingredients);
				recipeRepository.save(recipe);
				return recipe;
		}

	/**
	 * updateRecipe, updates an existing recipe to database.
	 */
		@Override
		public Recipe updateRecipe(Long id, Recipe recipe) {
				LOG.info("[updateRecipe]" + INSIDE_METHOD);
				Recipe foundRecipe = recipeRepository.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException(RECIPE, "id", id));
        foundRecipe.setRecipeName(recipe.getRecipeName());
        foundRecipe.setVegetarian(recipe.getVegetarian());
        foundRecipe.setNumberOfServings(recipe.getNumberOfServings());
        foundRecipe.setInstructions(recipe.getInstructions());
        if (recipe.getIngredient().size() > 0) {
				foundRecipe.setIngredient(recipe.getIngredient());
			}
				recipeRepository.save(foundRecipe);
				return foundRecipe;
		}

	/**
	 * deleteRecipeById, deletes an existing recipe from database.
	 */
		@Override
		public void deleteRecipeById(Long id) {
				LOG.info("[deleteRecipeById]" + INSIDE_METHOD);
				Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RECIPE, "id", id));
				recipeRepository.delete(recipe);
		}

	/**
	 * getRecipes, gets all existing recipe from database.
	 */
		@Override
		public RecipeResponse getRecipes(){
				LOG.info("[getRecipes]" + INSIDE_METHOD);
				RecipeResponse recipeResponse = new RecipeResponse();
				List<RecipeDto> recipeList = new ArrayList<>();
				recipeRepository.findAll().forEach((data)->{
						RecipeDto recipe = mapEntityToDto(data);
						recipe.setIngredients(ingredientRepository.findAllByRecipe(data).stream().map(RecipeIngredientHelper::mapToIngredientDto).collect(Collectors.toList()));
						recipeList.add(recipe);
				});
				recipeResponse.setRecipes(recipeList);
				return recipeResponse;
		}

	/**
	 * getRecipesBySearch, gets all existing recipe from database depending on input parameters.
	 */
		@Override
		public RecipeResponse getRecipesBySearch(Boolean isVegetarian, Integer noOfServings, String includeIngredients,
																								 String excludeIngredients, String searchInstructions) {
				LOG.info("[getRecipesBySearch]" + INSIDE_METHOD);
				List<Long> ids = new ArrayList<>();
				if(Optional.ofNullable(excludeIngredients).isPresent()) {
						Specification<Recipe> specificationsExcludeIngredient =
								Specification.where(RecipeSpecification.containsIngredients(excludeIngredients));
						for (Recipe data : recipeRepository.findAll(specificationsExcludeIngredient)) {
								ids.add(data.getId());
						}
				}
				// specifications
				Specification<Recipe> specifications = Specification.where(RecipeSpecification.hasVegetarian(isVegetarian)
						.and(RecipeSpecification.hasNoOfServings(noOfServings)
								.and(RecipeSpecification.doesNotContainIngredients(ids)
										.and(RecipeSpecification.containsIngredients(includeIngredients)
												.and(RecipeSpecification.containsInstructions(searchInstructions))))));

				// map response
				RecipeResponse recipeResponse = new RecipeResponse();
				List<RecipeDto> recipeList = new ArrayList<>();
				recipeRepository.findAll(specifications).forEach((data)->{
						RecipeDto recipe = mapEntityToDto(data);
						recipe.setIngredients(ingredientRepository.findAllByRecipe(data).stream().map(RecipeIngredientHelper::mapToIngredientDto).collect(Collectors.toList()));
						recipeList.add(recipe);
				});
				recipeResponse.setRecipes(recipeList);
				return recipeResponse;

		}

	/**
	 * mapEntityToDto, maps entity class to dto.
	 */
		private RecipeDto mapEntityToDto(Recipe recipe) {
				RecipeDto recipeDto = new RecipeDto();
				recipeDto.setId(recipe.getId());
				recipeDto.setRecipeName(recipe.getRecipeName());
				recipeDto.setVegetarian(recipe.getVegetarian());
				recipeDto.setInstructions(recipe.getInstructions());
				recipeDto.setNumberOfServings(recipe.getNumberOfServings());
				return recipeDto;
		}

}
