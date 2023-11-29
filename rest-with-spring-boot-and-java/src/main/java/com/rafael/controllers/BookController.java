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

import com.rafael.dtos.BookDTO;
import com.rafael.services.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/book")
@Tag(name = "Book", description = "Endpoints for Managing Book")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BookDTO> listAll() {
		return this.bookService.listAll();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BookDTO findById(@PathVariable(value = "id") Long id) {
		return this.bookService.findById(id);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, // Vai produzir um JSON
			consumes = MediaType.APPLICATION_JSON_VALUE) // Vai consumir um JSON
	public BookDTO create(@RequestBody BookDTO bookDTO) {
		return this.bookService.create(bookDTO);
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, // Vai produzir um JSON
			consumes = MediaType.APPLICATION_JSON_VALUE) // Vai consumir um JSON)
	public BookDTO update(@RequestBody BookDTO bookDTO) {
		return this.bookService.update(bookDTO);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete a Book", description = "Deletes a Book Passion", tags = { "Book" }, responses = {
			@ApiResponse(responseCode = "500", content = @Content),
			@ApiResponse(responseCode = "404", content = @Content),
			@ApiResponse(responseCode = "401", content = @Content),
			@ApiResponse(responseCode = "400", content = @Content),
			@ApiResponse(responseCode = "200", content = @Content),
	})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		this.bookService.delete(id);
		return ResponseEntity.noContent().build(); // Para retornar o status 204
	}

}
