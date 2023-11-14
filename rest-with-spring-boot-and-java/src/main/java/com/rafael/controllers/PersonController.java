package com.rafael.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.model.Person;
import com.rafael.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> listAll() {
		return this.personService.listAll();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(@PathVariable(value = "id") Long id) {
		return this.personService.findById(id);
	}

	@PostMapping(
			produces = MediaType.APPLICATION_JSON_VALUE, //Vai produzir um JSON
			consumes = MediaType.APPLICATION_JSON_VALUE) //Vai consumir um JSON
	public Person create(@RequestBody Person person) {
		return this.personService.create(person);
	}

	@PutMapping(
			produces = MediaType.APPLICATION_JSON_VALUE, //Vai produzir um JSON
			consumes = MediaType.APPLICATION_JSON_VALUE) //Vai consumir um JSON)	
	public Person update(@RequestBody Person person) {
		return this.personService.update(person);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		this.personService.delete(id);
		return ResponseEntity.noContent().build(); //Para retornar o status 204
	}

}
