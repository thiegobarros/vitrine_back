package com.vitrine.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitrine.livraria.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>  {

}
