package com.digital.abn.recipe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static com.digital.abn.recipe.constants.ApplicationConstants.ID_NULL_OR_MALFORMED;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Recipe")
public class Recipe {

	@JsonIgnore
  @Id
	@Digits(integer = 12, fraction = 0, message = ID_NULL_OR_MALFORMED)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

	@NotEmpty(message = "Recipe name may not be empty")
  private String recipeName;

  private Boolean vegetarian;

	@NotNull(message = "Number of Servings name may not be null")
	@Digits(integer = 1, fraction = 0, message = "Number of Servings can only have upto 9 servings")
  private Integer numberOfServings;

	@NotEmpty(message = "Instructions name may not be empty")
  private String instructions;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "recipe_ingredient",
			joinColumns = @JoinColumn(name = "recipe_id"),
			inverseJoinColumns = @JoinColumn(name = "ingredient_id")
	)
	private List<Ingredient> ingredient = new ArrayList<>();

}
