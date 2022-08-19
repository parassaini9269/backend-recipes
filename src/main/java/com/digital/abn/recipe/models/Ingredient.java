package com.digital.abn.recipe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import static com.digital.abn.recipe.constants.ApplicationConstants.ID_NULL_OR_MALFORMED;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Ingredient")
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Digits(integer = 12, fraction = 0, message = ID_NULL_OR_MALFORMED)
	private Long id;

	@NotEmpty(message = "Ingredient name may not be empty")
	private String ingredientName;

	@JsonIgnore
	@ManyToMany(
			cascade = CascadeType.ALL,
			mappedBy = "ingredient")
	private List<Recipe> recipe = new ArrayList<>();

}
