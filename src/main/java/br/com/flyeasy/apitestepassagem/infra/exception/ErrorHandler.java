package br.com.flyeasy.apitestepassagem.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	private ResponseEntity erro404(EntityNotFoundException ex) {
		System.out.println(ex.getStackTrace());

		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	private ResponseEntity erro400(MethodArgumentNotValidException ex) {
		System.out.println(ex.getStackTrace());

		return ResponseEntity.badRequest().body(ex.getFieldErrors().stream().map(ErroDTO::new).toList());
		//return ResponseEntity.badRequest().build();
	}
	@ExceptionHandler(ValidacaoException.class)
	private ResponseEntity<String> erroValidacao(ValidacaoException ex) {
		System.out.println(ex.getStackTrace());

		return ResponseEntity.badRequest().body(ex.getMessage() + "\n" + ex.getStackTrace());
		//return ResponseEntity.badRequest().build();
	}
	@ExceptionHandler(Exception.class)
	private ResponseEntity<String> erro(Exception ex) {
		ex.printStackTrace();
		return ResponseEntity.badRequest().body(ex.getMessage());
		//return ResponseEntity.badRequest().build();
	}
}