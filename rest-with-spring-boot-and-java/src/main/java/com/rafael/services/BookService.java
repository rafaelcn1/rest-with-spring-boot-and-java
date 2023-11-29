package com.rafael.services;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.dtos.BookDTO;
import com.rafael.exceptions.ResourceNotFoundException;
import com.rafael.model.Book;
import com.rafael.repositorys.BookRepository;

@Service
public class BookService {

	private Logger logger = Logger.getLogger(BookService.class.getName());

	@Autowired
	private BookRepository BookRepository;

	public BookDTO findById(Long id) {
		logger.info("Finding Book with id: " + id);
		Book book = this.BookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id: " + id));
		return new BookDTO(book);
	}

	public List<BookDTO> listAll() {
		logger.info("Finding All Book!");
		List<Book> findAll = this.BookRepository.findAll();
		// ArrayList<BookDTO> listAll = new ArrayList<BookDTO>();

		List<BookDTO> listAll = findAll.stream().map(book -> {
			BookDTO BookDTO = new BookDTO(book);
			// Adicione outros atributos conforme necessário
			return BookDTO;
		}).collect(Collectors.toList());

		return listAll;
	}

	public BookDTO create(BookDTO bookDTO) {
		Book book = new Book(bookDTO);
		logger.info("Creating one Book!");
		bookDTO = new BookDTO(this.BookRepository.save(book));
		return bookDTO;
	}

	public BookDTO update(BookDTO bookDTO) {
		logger.info("Updating Book with id: " + bookDTO.getId());
		BookDTO findById = this.findById(bookDTO.getId());

		findById.setAuthor(bookDTO.getAuthor());
		findById.setPrice(bookDTO.getPrice());
		findById.setTitle(bookDTO.getTitle());
		findById.setLauchDate(bookDTO.getLauchDate());
		Book Book = new Book(findById);

		bookDTO = new BookDTO(this.BookRepository.save(Book));
		return bookDTO;
	}

	public void delete(Long id) {
		BookDTO BookDTO = this.findById(id);
		Book book = new Book(BookDTO);
		logger.info("Deleting Book with id: " + id);
		this.BookRepository.delete(book);
	}

}
