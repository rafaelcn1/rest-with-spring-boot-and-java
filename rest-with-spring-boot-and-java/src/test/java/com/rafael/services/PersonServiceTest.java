package com.rafael.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import com.rafael.dtos.PersonDTO;
import com.rafael.model.Person;
import com.rafael.repositorys.PersonRepository;

@TestInstance(Lifecycle.PER_CLASS) // Definino o tempo do ciclo de vida
@ExtendWith(MockitoExtension.class) // Porque estamos usando o mockito
class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;

	@InjectMocks
	private PersonService personService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Inicializa os mocks
	}

	@Test
	void testFindById() {
		// Configuração do mock
		Long personId = 3L;
		Person person = new Person();
		person.setId(personId);
		when(personRepository.findById(personId)) // Quando o metodo for chamado, passamos um id
				.thenReturn(Optional.of(person)); // Então o retorno vem um optional de person

		// chamar o service
		PersonDTO result = personService.findById(personId);

		assertNotNull(result); // primeiro teste, certificar que não está nulo
		assertNotNull(result.getId()); // tem que ter um id sem ser null

	}

	@Test
	void testCreate() {
		// Configuração do mock
		PersonDTO personDTO = new PersonDTO();
		personDTO.setFirstName("John");
		personDTO.setLastName("Doe");
		personDTO.setAddress("Recife");
		personDTO.setGender("male");

		PersonDTO personPersisted = personDTO;
		personPersisted.setId(1L);

		Person person = new Person(personDTO);
		Person person2 = new Person(personPersisted);

		when(personRepository.save(person)).thenReturn(person2);

		// Adicione outros atributos conforme necessário
		PersonDTO result = personService.create(personDTO);

		// Verificações
		assertNotNull(result);
		System.out.println(result.getId());
		assertNotNull(result.getId()); // Certifique-se de que o ID não seja nulo

	}

	@Test
	void testUpdate() {
		// Configuração do mock
		PersonDTO personDTO = new PersonDTO();
		personDTO.setFirstName("John");
		personDTO.setLastName("Doe");
		personDTO.setAddress("Recife");
		personDTO.setGender("male");
		personDTO.setId(1L);

		PersonDTO personPersisted = personDTO;
		personPersisted.setId(1L);

		Person person = new Person(personDTO);
		Person person2 = new Person(personPersisted);

		when(personRepository.findById(personDTO.getId())).thenReturn(Optional.of(person)); // Então o retorno vem um
																							// optional de person

		when(personRepository.save(person)).thenReturn(person2);

		// Adicione outros atributos conforme necessário
		PersonDTO result = personService.update(personDTO);

		// Verificações
		assertNotNull(result);
		System.out.println(result.getId());
		assertNotNull(result.getId()); // Certifique-se de que o ID não seja nulo

	}

	@Test
	void testDelete() {
		// Configuração do mock
		PersonDTO personDTO = new PersonDTO();
		personDTO.setId(1L);

		PersonDTO personPersisted = personDTO;
		personPersisted.setId(1L);

		Person person = new Person(personDTO);
		Person person2 = new Person(personPersisted);

		when(personRepository.findById(personDTO.getId())).thenReturn(Optional.of(person)); // Então o retorno vem um

		// Adicione outros atributos conforme necessário
		personService.delete(personDTO.getId());

	}

	@Test
	void testListAll() {
		List<Person> list = new ArrayList<Person>();
		
		when(personRepository.findAll()).thenReturn(list);
		
		List<PersonDTO> listAll = personService.listAll();
		
		assertNotNull(listAll);
	}

}
