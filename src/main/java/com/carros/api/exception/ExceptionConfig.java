package com.carros.api.exception;


import java.io.Serializable;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.carros.domain.dto.CarroDTO;

@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler{
	
	// EmptyResultDataAccessException method delete.
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<CarroDTO> errorNotFound(Exception ex) {
		return ResponseEntity.notFound().build();
	}
	
	// EmptyResultDataAccessException method delete.
	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<CarroDTO> errorBadRequest(Exception ex) {
		return ResponseEntity.badRequest().build();
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		return new ResponseEntity<Object>(new ExceptionError("Método não suportado"), HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	public class ExceptionError implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private String error;
		
		ExceptionError(String string) {
			this.error = string;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}
	
	}
	
	
}