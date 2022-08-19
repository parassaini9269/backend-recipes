package com.digital.abn.recipe.controllers;

import com.digital.abn.recipe.exceptions.ApiError;
import com.digital.abn.recipe.models.Ingredient;
import com.digital.abn.recipe.models.IngredientDto;
import com.digital.abn.recipe.models.IngredientResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RequestMapping(value = "/v1")
@Api(value = "Ingredient Service")
public interface IIngredientController {
  @ApiOperation(value = "Post Data",
      notes = "See the data model parameter for more information about data types.")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Sample Data Object",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = Ingredient.class),
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
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/ingredient")
  @ResponseStatus(value = HttpStatus.CREATED)
  ResponseEntity<IngredientDto> addIngredient(@Valid @RequestBody IngredientDto ingredientDto);


  @ApiOperation(value = "GET Data",
      notes = "See the data model parameter for more information about data types.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Sample Data Object",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = IngredientResponse.class),
      @ApiResponse(code = 500,
          message = "Invalid or malformed request.",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = ApiError.class)})
  /**
   * Retrieve {@link Data}
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/ingredients")
  @ResponseStatus(value = HttpStatus.OK)
  ResponseEntity<IngredientResponse> getAllIngredients();

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
  @DeleteMapping(value = "/ingredient/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  ResponseEntity<String> deleteIngredient(@Valid @PathVariable Long id);

}
