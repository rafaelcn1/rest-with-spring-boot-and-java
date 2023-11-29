package com.rafael.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rafael.dtos.BookDTO;
import com.rafael.dtos.PersonDTO;
import com.rafael.model.Book;
import com.rafael.model.Person;
import com.rafael.repositorys.BookRepository;
import com.rafael.repositorys.PersonRepository;

@TestInstance(Lifecycle.PER_CLASS) // Definino o tempo do ciclo de vida
@ExtendWith(MockitoExtension.class) // Porque estamos usando o mockito
class BookServiceTest {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookService bookService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Inicializa os mocks
	}

	@Test
	void testFindById() {
		// Configuração do mock
		Long bookId = 3L;
		Book book = new Book();
		book.setId(bookId);
		when(bookRepository.findById(bookId)) // Quando o metodo for chamado, passamos um id
				.thenReturn(Optional.of(book)); // Então o retorno vem um optional de book

		// chamar o service
		BookDTO result = bookService.findById(bookId);

		assertNotNull(result); // primeiro teste, certificar que não está nulo
		assertNotNull(result.getId()); // tem que ter um id sem ser null

	}

	@Test
	void testCreate() {
		// Configuração do mock
		BookDTO bookDTO = new BookDTO();
		bookDTO.setTitle("A biblia");
		bookDTO.setAuthor("Os Apostolos");
		bookDTO.setLauchDate(new Date());
		bookDTO.setPrice(10.0);

		BookDTO bookPersisted = bookDTO;
		bookPersisted.setId(1L);

		Book book = new Book(bookDTO);
		Book book2 = new Book(bookPersisted);

		when(bookRepository.save(book)).thenReturn(book2);

		// Adicione outros atributos conforme necessário
		BookDTO result = bookService.create(bookDTO);

		// Verificações
		assertNotNull(result);
		System.out.println(result.getId());
		assertNotNull(result.getId()); // Certifique-se de que o ID não seja nulo

	}

	@Test
	void testUpdate() {
		// Configuração do mock
		BookDTO bookDTO = new BookDTO();
		bookDTO.setTitle("A biblia Editado");
		bookDTO.setAuthor("Os Apostolos Editado");
		bookDTO.setLauchDate(new Date());
		bookDTO.setPrice(10.0);
		bookDTO.setId(1L);

		BookDTO bookPersisted = bookDTO;
		bookPersisted.setId(1L);

		Book book = new Book(bookDTO);
		Book book2 = new Book(bookPersisted);

		when(bookRepository.findById(bookDTO.getId())).thenReturn(Optional.of(book)); // Então o retorno vem um
																							// optional de book

		when(bookRepository.save(book)).thenReturn(book2);

		// Adicione outros atributos conforme necessário
		BookDTO result = bookService.update(bookDTO);

		// Verificações
		assertNotNull(result);
		System.out.println(result.getId());
		assertNotNull(result.getId()); // Certifique-se de que o ID não seja nulo

	}

	@Test
	void testDelete() {
		// Configuração do mock
		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(1L);

		BookDTO bookPersisted = bookDTO;
		bookPersisted.setId(1L);

		Book book = new Book(bookDTO);
		Book book2 = new Book(bookPersisted);

		when(bookRepository.findById(bookDTO.getId())).thenReturn(Optional.of(book)); // Então o retorno vem um

		// Adicione outros atributos conforme necessário
		bookService.delete(bookDTO.getId());

	}

	@Test
	void testListAll() {
		List<Book> list = new ArrayList<Book>();
		
		when(bookRepository.findAll()).thenReturn(list);
		
		List<BookDTO> listAll = bookService.listAll();
		
		assertNotNull(listAll);
	}

}
