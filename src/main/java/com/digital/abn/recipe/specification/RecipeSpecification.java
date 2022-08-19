package com.digital.abn.recipe.specification;

import com.digital.abn.recipe.models.Ingredient;
import com.digital.abn.recipe.models.Recipe;
import com.digital.abn.recipe.models.Recipe_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

import static com.digital.abn.recipe.constants.ApplicationConstants.INSIDE_METHOD;

@Component
public class RecipeSpecification {

  private static final Logger LOG = LoggerFactory.getLogger(RecipeSpecification.class);

  /**
   * hasVegetarian, specification for vegetarian which are included in criteria.
   */
  public static Specification<Recipe> hasVegetarian(Boolean vegetarian) {
    LOG.info("[hasVegetarian]" + INSIDE_METHOD);
    if (Optional.ofNullable(vegetarian).isPresent()) {
      return (root, criteriaQuery, criteriaBuilder) -> {
        return criteriaBuilder.equal(root.get(Recipe_.VEGETARIAN), vegetarian);
      };
    }
    return (root, criteriaQuery, criteriaBuilder) -> {
      return criteriaBuilder.conjunction();
    };
  }

  /**
   * hasNoOfServings, specification for numberOfServings which are included in criteria.
   */
  public static Specification<Recipe> hasNoOfServings(Integer noOfServings) {
    LOG.info("[hasNoOfServings]" + INSIDE_METHOD);
    if (Optional.ofNullable(noOfServings).isPresent()) {
      return (root, criteriaQuery, criteriaBuilder) -> {
        return criteriaBuilder.equal(root.get(Recipe_.NUMBER_OF_SERVINGS), noOfServings);
      };
    }
    return (root, criteriaQuery, criteriaBuilder) -> {
      return criteriaBuilder.conjunction();
    };
  }

  /**
   * containsInstructions, specification to search part of text in instructions to be included in criteria.
   */
  public static Specification<Recipe> containsInstructions(String instructions) {
    LOG.info("[containsInstructions]" + INSIDE_METHOD);
    if (Optional.ofNullable(instructions).isPresent()) {
      return (root, criteriaQuery, criteriaBuilder) -> {
        return criteriaBuilder.like(criteriaBuilder.upper(root.get(Recipe_.INSTRUCTIONS)), "%" + instructions.toUpperCase() + "%");
      };
    }
    return (root, criteriaQuery, criteriaBuilder) -> {
      return criteriaBuilder.conjunction();
    };
  }

  /**
   * containsIngredients, specification for ingredients which are included in criteria.
   */
  public static Specification<Recipe> containsIngredients(String includeIngredients) {
    LOG.info("[containsIngredients]" + INSIDE_METHOD);
    if (Optional.ofNullable(includeIngredients).isPresent()) {
      return (root, criteriaQuery, criteriaBuilder) -> {
        criteriaQuery.distinct(true);
        Join<Ingredient, Recipe> recipeIngredients = root.join("ingredient");
        return criteriaBuilder.equal(criteriaBuilder.upper(recipeIngredients.get("ingredientName")), includeIngredients.toUpperCase());
      };
    }
    return (root, criteriaQuery, criteriaBuilder) -> {
      return criteriaBuilder.conjunction();
    };
  }

  /**
   * doesNotContainIngredients, specification for ingredients which are excluded from criteria.
   */
  public static Specification<Recipe> doesNotContainIngredients(List<Long> excludeIds) {
    LOG.info("[doesNotContainIngredients]" + INSIDE_METHOD);
    if (excludeIds.size() > 0) {
      return (root, criteriaQuery, criteriaBuilder) -> {
        return criteriaBuilder.in(root.get(Recipe_.ID)).value(excludeIds).not();
      };
    }
    return (root, criteriaQuery, criteriaBuilder) -> {
      return criteriaBuilder.conjunction();
    };
  }

}
