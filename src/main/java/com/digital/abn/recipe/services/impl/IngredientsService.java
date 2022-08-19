package com.digital.abn.recipe.services.impl;

import com.digital.abn.recipe.models.IngredientDto;
import com.digital.abn.recipe.models.IngredientResponse;
import com.digital.abn.recipe.exceptions.ResourceNotFoundException;
import com.digital.abn.recipe.repositories.IngredientRepository;
import com.digital.abn.recipe.repositories.RecipeRepository;
import com.digital.abn.recipe.models.Ingredient;
import com.digital.abn.recipe.services.IIngredientsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.digital.abn.recipe.constants.ApplicationConstants.*;
import static com.digital.abn.recipe.utilities.RecipeIngredientHelper.mapToIngredientDto;
import static com.digital.abn.recipe.utilities.RecipeIngredientHelper.mapToIngredientEntity;

@Service
public class IngredientsService implements IIngredientsService {

    private static final Logger LOG = LoggerFactory.getLogger(IngredientsService.class);

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    /**
     * createIngredient, adds a new ingredient to database.
     */
    @Override
    public IngredientDto createIngredient(IngredientDto ingredientDto) {
        LOG.info("[createIngredient]" + INSIDE_METHOD);
        Ingredient ingredient = mapToIngredientEntity(ingredientDto);
        ingredientRepository.save(ingredient);

        IngredientDto ingredientResponse = mapToIngredientDto(ingredient);
        return ingredientResponse;
    }

    /**
     * deleteIngredientById, deletes an ingredient from database.
     */
    @Override
    public void deleteIngredientById(Long id) {
        LOG.info("[deleteIngredientById]" + INSIDE_METHOD);
        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(INGREDIENT, "id", id));
        ingredientRepository.delete(ingredient);
    }

    /**
     * getIngredients, gets all ingredients from database.
     */
    @Override
    public IngredientResponse getIngredients(){
        LOG.info("[getIngredients]" + INSIDE_METHOD);
        IngredientResponse ingredientResponse = new IngredientResponse();
        List<IngredientDto> ingredientDtoList = new ArrayList<>();
        ingredientRepository.findAll().forEach((data)->{
            IngredientDto ingredient = mapToIngredientDto(data);
            ingredientDtoList.add(ingredient);
        });
        ingredientResponse.setIngredients(ingredientDtoList);
        return ingredientResponse;
    }


}
