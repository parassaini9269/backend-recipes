package com.digital.abn.recipe.exceptions;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Error item representation contract.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ApiModel
public class ApiError {

	@ApiModelProperty(notes = "Timestamp when error occurred")
	private Date timestamp;

	@ApiModelProperty(notes = "HTTP status code.")
	private HttpStatus status;

	@ApiModelProperty(notes = "message")
	private String message;

}