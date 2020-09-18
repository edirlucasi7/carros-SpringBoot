package com.carros.api.exception;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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
	
//	class ExceptionError implements Serializable {
//		private String error;
//		public ExceptionError(String string) {
//			this.error = string;
//		}
//		void ExcpetionError(String error) {
//			this.error = error;
//		}
//		public String getError() {
//			return error;
//		}
//	}
//	
//	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers) {
//		return new ResponseEntity<>(new ExceptionError("Erro"), HttpStatus.METHOD_NOT_ALLOWED);
//	}
//	
	
	
}