package com.vitrine.livraria.services.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vitrine.livraria.dto.AuthorDto;
import com.vitrine.livraria.exception.ResourceNotFoundException;
import com.vitrine.livraria.models.Author;
import com.vitrine.livraria.repository.AuthorRepository;

@Service
public class AuthorUpdateService {
	@Autowired
	private AuthorRepository authorRepository;
	
	public ResponseEntity<Author> updateAuthor(Long id, AuthorDto authorDto) {
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author not exist with id :" + id));
		author.setFirstName(authorDto.getFirstName());
		author.setLastName(authorDto.getLastName());
		Author updatedBook = authorRepository.save(author);
		return ResponseEntity.ok(updatedBook);
	}

}
