package com.vitrine.livraria.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitrine.livraria.converter.AuthorConverter;
import com.vitrine.livraria.dto.AuthorDto;
import com.vitrine.livraria.exception.ResourceNotFoundException;
import com.vitrine.livraria.models.Author;
import com.vitrine.livraria.present.AuthorPresent;
import com.vitrine.livraria.repository.AuthorRepository;
import com.vitrine.livraria.services.author.AuthorStoreService;
import com.vitrine.livraria.services.author.AuthorUpdateService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/livraria/")
public class AuthorController {
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private AuthorStoreService store;
	
	@Autowired
	private AuthorUpdateService update;
	
	@Autowired
	private AuthorConverter authorConverter;
	
	@GetMapping("/author")
	public List<AuthorPresent> getAllBooks() {
		return authorConverter.toCollection(authorRepository.findAll());
	}
	
	@PostMapping("/author")
	public ResponseEntity<AuthorPresent> createSensor(@RequestBody AuthorDto authorDto) {
		return store.createAuthor(authorDto);
	}
	
	@GetMapping("/author/{id}")
	public ResponseEntity<Author> getBookById(@PathVariable Long id) {
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author not exist with id :" + id));
		return ResponseEntity.ok(author);
	}

	@PutMapping("/author/{id}")
	public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
		return update.updateAuthor(id, authorDto);
	}
	
	@DeleteMapping("/author/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteAuthor(@PathVariable Long id) {
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author not exist with id :" + id));
		authorRepository.delete(author);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
