package com.rafael.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // permite a definição de métodos que serão executados quando ocorrerem exceções
@RestController
//Esta classe estende ResponseEntityExceptionHandler, uma classe fornecida pelo Spring para manipulação de respostas de entidades HTTP.
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	// Este método trata exceções do tipo Exception.
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handlerAllException(Exception ex, WebRequest request) {
		// Cria uma instância de ExceptionResponse com a data atual, a mensagem da
		// exceção e a descrição da solicitação.
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		// Retorna uma ResponseEntity contendo o objeto ExceptionResponse e o código de
		// status HTTP 500 (Internal Server Error).
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Este método trata exceções do tipo UnsupportedMathOperationException.
	@ExceptionHandler(UnsupportedMathOperationException.class)
	public final ResponseEntity<ExceptionResponse> handlerBadRequest(Exception ex, WebRequest request) {
		// Cria uma instância de ExceptionResponse com a data atual, a mensagem da
		// exceção e a descrição da solicitação.
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		// Retorna uma ResponseEntity contendo o objeto ExceptionResponse e o código de
		// status HTTP 400 (Bad Request).
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
}
