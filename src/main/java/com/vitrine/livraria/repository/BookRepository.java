package com.vitrine.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitrine.livraria.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	List<Book> findAllByOrderByTitleAsc();
	
	List<Book> findAllByOrderByTitleDesc();
	
	List<Book> findByTitleLike(String title);
}
