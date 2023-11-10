package com.rafael.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.exceptions.UnsupportedMathOperationException;
import com.rafael.util.Util;

@RestController
public class MathController {
	
	Util util = new Util();

	//private final AtomicLong counter = new AtomicLong();
	// alocar um id para a cada chamada

	@RequestMapping(
			value = "/sum/{numberOne}/{numberTwo}", // Colocando variaveis na url req 
			method = RequestMethod.GET) // Especificando o tipo do metodo
	public Double sum(
			@PathVariable(value =  "numberOne") String numberOne, //Recuperar os valores da variavel numberOne da url
			@PathVariable(value =  "numberTwo") String numberTwo //Recuperar os valores da variavel numberTwo da url
			) throws Exception {
		
		if(!this.util.isNumeric(numberOne) || !this.util.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		} 
		
		return this.util.convertToDouble(numberOne) + this.util.convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sub(
			@PathVariable(value = "numberOne") String numberOne, 
			@PathVariable(value = "numberTwo") String numberTwo) {
		if (!this.util.isNumeric(numberOne) || !this.util.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}

		return this.util.convertToDouble(numberOne) - this.util.convertToDouble(numberTwo);

	}
	
	@RequestMapping(value = "/mult/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mult(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) {

		if (!this.util.isNumeric(numberOne) || !this.util.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}

		return this.util.convertToDouble(numberOne) * this.util.convertToDouble(numberTwo);

	}
	
	@RequestMapping(value = "/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double div(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) {

		if (!this.util.isNumeric(numberOne) || !this.util.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}

		return this.util.convertToDouble(numberOne) / this.util.convertToDouble(numberTwo);

	}
	
	
	@RequestMapping(value = "/square/{numberOne}", method = RequestMethod.GET)
	public Double square(@PathVariable(value = "numberOne") String numberOne) {
		
		
		if (!this.util.isNumeric(numberOne)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}

		return Math.sqrt(this.util.convertToDouble(numberOne));

	}
}
