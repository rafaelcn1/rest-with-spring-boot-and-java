package com.rafael.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.exceptions.ResourceNotFoundException;
import com.rafael.model.Person;
import com.rafael.repositorys.PersonRepository;

@Service
public class PersonService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());

	@Autowired
	private PersonRepository personRepository;

	public Person findById(Long id) {
		logger.info("Finding Person with id: " + id);
		return this.personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id: " + id));
	}

	public List<Person> listAll() {
		logger.info("Finding All Person!");
		return this.personRepository.findAll();
	}

	public Person create(Person person) {
		logger.info("Creating one person!");
		return this.personRepository.save(person);
	}

	public Person update(Person person) {
		logger.info("Updating person with id: " + person.getId());
		Person findById = this.findById(person.getId());
		
		findById.setFirstName(person.getFirstName());
		findById.setLastName(person.getLastName());
		findById.setAddress(person.getAddress());
		findById.setGender(person.getGender());
		
		return this.personRepository.save(findById);
	}

	public void delete(Long id) {
		Person findById = this.findById(id);
		logger.info("Deleting person with id: " + id);
		this.personRepository.delete(findById);
	}

}
