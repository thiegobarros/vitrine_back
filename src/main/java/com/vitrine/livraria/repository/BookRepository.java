package com.vitrine.livraria.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vitrine.livraria.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	List<Book> findAllByOrderByTitleAsc();
	
	List<Book> findAllByOrderByTitleDesc();
	
	List<Book> findByTitleLike(String title);
	
	@Query("FROM Book emp " + "WHERE LOWER(emp.title) like %:searchTerm% ")
    Page<Book> search(@Param("searchTerm") String searchTerm, Pageable pageable);
}
