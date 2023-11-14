package com.rafael.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
