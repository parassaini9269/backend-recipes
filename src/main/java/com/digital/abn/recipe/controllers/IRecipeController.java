package com.digital.abn.recipe.controllers;

import com.digital.abn.recipe.exceptions.ApiError;
import com.digital.abn.recipe.models.*;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/v1")
@Api(value = "Recipe Service")
public interface IRecipeController {
  @ApiOperation(value = "Post Data",
      notes = "See the data model parameter for more information about data types.")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Sample Data Object",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = Recipe.class),
      @ApiResponse(code = 500,
          message = "Service Unavailable",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = ApiError.class),
      @ApiResponse(code = 400,
          message = "Invalid or malformed request.",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = ApiError.class)})
  /**
   * Post {@link Data}
   */
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/recipe")
  @ResponseStatus(value = HttpStatus.CREATED)
  ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe);


  @ApiOperation(value = "Put Data",
      notes = "See the data model parameter for more information about data types.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Sample Data Object",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = Recipe.class),
      @ApiResponse(code = 500,
          message = "Invalid or malformed request.",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = ApiError.class)})
  /**
   * Update {@link Data}
   */
  @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/updateRecipe/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  ResponseEntity<Recipe> updateRecipe(@Valid @PathVariable Long id,
                                      @Valid @RequestBody Recipe recipe);

  @ApiOperation(value = "Delete data",
      notes = "See the data model parameter for more information about data types.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Sample Data Object",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = String.class),
      @ApiResponse(code = 500,
          message = "Invalid or malformed request.",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = ApiError.class)})
  /**
   * Delete {@link Data}
   */
  @DeleteMapping(value = "/recipe/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  ResponseEntity<String> deleteRecipe(@Valid @PathVariable Long id);

  @ApiOperation(value = "GET Data",
      notes = "See the data model parameter for more information about data types.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Sample Data Object",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = RecipeResponse.class),
      @ApiResponse(code = 500,
          message = "Invalid or malformed request.",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = ApiError.class)})
  /**
   * Retrieve {@link Data}
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/recipes")
  @ResponseStatus(value = HttpStatus.OK)
  ResponseEntity<RecipeResponse> getAllRecipes();

  @ApiOperation(value = "GET Data",
      notes = "See the data model parameter for more information about data types.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Sample Data Object",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = RecipeResponse.class),
      @ApiResponse(code = 500,
          message = "Invalid or malformed request.",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = ApiError.class)})
  /**
   * Retrieve {@link Data}
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      value = "/recipes/{isVeg}/{servings}/{includeIngredients}/{excludeIngredients}/{searchInstructions}")
  @ResponseStatus(value = HttpStatus.OK)
  ResponseEntity<RecipeResponse> getRecipesBySearch(@Valid @RequestParam(required = false) Boolean isVeg,
                                                    @Valid @RequestParam(required = false) Integer servings,
                                                    @Valid @RequestParam(required = false) String includeIngredients,
                                                    @Valid @RequestParam(required = false) String excludeIngredients,
                                                    @Valid @RequestParam(required = false) String searchInstructions);

}
