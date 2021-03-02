package com.vitrine.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitrine.livraria.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
