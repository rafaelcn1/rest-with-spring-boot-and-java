package com.rafael.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.dtos.PersonDTO;
import com.rafael.services.PersonService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/person")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonDTO> listAll() {
		return this.personService.listAll();
	}

	@CrossOrigin(origins = {"http://localhost:8080"})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonDTO findById(@PathVariable(value = "id") Long id) {
		return this.personService.findById(id);
	}

	@CrossOrigin(origins = {"http://localhost:8080"})
	@PostMapping(
			produces = MediaType.APPLICATION_JSON_VALUE, //Vai produzir um JSON
			consumes = MediaType.APPLICATION_JSON_VALUE) //Vai consumir um JSON
	public PersonDTO create(@RequestBody PersonDTO personDTO) {
		return this.personService.create(personDTO);
	}

	@PutMapping(
			produces = MediaType.APPLICATION_JSON_VALUE, //Vai produzir um JSON
			consumes = MediaType.APPLICATION_JSON_VALUE) //Vai consumir um JSON)	
	public PersonDTO update(@RequestBody PersonDTO personDTO) {
		return this.personService.update(personDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		this.personService.delete(id);
		return ResponseEntity.noContent().build(); //Para retornar o status 204
	}

}
