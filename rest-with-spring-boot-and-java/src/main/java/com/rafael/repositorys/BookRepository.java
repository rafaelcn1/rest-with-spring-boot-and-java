package com.rafael.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
