package com.digital.abn.recipe.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception){
		 return buildResponseEntity(new ApiError(new Date(), HttpStatus.NOT_FOUND, exception.getMessage()));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
		return buildResponseEntity(new ApiError(new Date(), HttpStatus.BAD_REQUEST, exception.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleResourceNotFoundException(Exception exception){
		return buildResponseEntity(new ApiError(new Date(), HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()));
	}

	/**
	 * Build the response for Exception.
	 * <p>
	 * The implementation that creates the response object.
	 *
	 * @param apiError the model object
	 * @return a {@code ResponseEntity} instance
	 */
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
