package com.rafael.services;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.dtos.PersonDTO;
import com.rafael.exceptions.ResourceNotFoundException;
import com.rafael.model.Person;
import com.rafael.repositorys.PersonRepository;

@Service
public class PersonService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());

	@Autowired
	private PersonRepository personRepository;

	public PersonDTO findById(Long id) {
		logger.info("Finding Person with id: " + id);
		Person person = this.personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id: " + id));
		return new PersonDTO(person);
	}

	public List<PersonDTO> listAll() {
		logger.info("Finding All Person!");
		List<Person> findAll = this.personRepository.findAll();
		// ArrayList<PersonDTO> listAll = new ArrayList<PersonDTO>();

		List<PersonDTO> listAll = findAll.stream().map(person -> {
			PersonDTO personDTO = new PersonDTO(person);
			// Adicione outros atributos conforme necessário
			return personDTO;
		}).collect(Collectors.toList());

		return listAll;
	}

	public PersonDTO create(PersonDTO personDTO) {
		Person person = new Person(personDTO);
		logger.info("Creating one person!");
		personDTO = new PersonDTO(this.personRepository.save(person));
		return personDTO;
	}

	public PersonDTO update(PersonDTO personDTO) {
		logger.info("Updating person with id: " + personDTO.getId());
		PersonDTO findById = this.findById(personDTO.getId());

		findById.setFirstName(personDTO.getFirstName());
		findById.setLastName(personDTO.getLastName());
		findById.setAddress(personDTO.getAddress());
		findById.setGender(personDTO.getGender());

		Person person = new Person(findById);

		personDTO = new PersonDTO(this.personRepository.save(person));
		return personDTO;
	}

	public void delete(Long id) {
		PersonDTO personDTO = this.findById(id);
		Person person = new Person(personDTO);
		logger.info("Deleting person with id: " + id);
		this.personRepository.delete(person);
	}

}
