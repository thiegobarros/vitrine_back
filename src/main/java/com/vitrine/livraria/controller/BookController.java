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
import org.springframework.data.domain.Page;

import com.vitrine.livraria.exception.ResourceNotFoundException;
import com.vitrine.livraria.models.Book;
import com.vitrine.livraria.repository.BookRepository;
import com.vitrine.livraria.converter.BookConverter;
import com.vitrine.livraria.dto.BookDto;
import com.vitrine.livraria.dto.VitrineDto;
import com.vitrine.livraria.present.BookPresent;
import com.vitrine.livraria.services.book.BookStoreService;
import com.vitrine.livraria.services.book.BookUpdateService;
import com.vitrine.livraria.services.book.BookVitrineService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/livraria/")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookStoreService store;
	
	@Autowired
	private BookUpdateService update;
	
	@Autowired
	private BookVitrineService vitrine;
	
	@Autowired
	private BookConverter bookConverter;
	
	@GetMapping("/book")
	public List<BookPresent> getAllBooks() {
		return bookConverter.toCollection(bookRepository.findAll());
	}
	
	@PostMapping("/book")
	public ResponseEntity<BookPresent> createSensor(@RequestBody BookDto bookDto) {
		return store.createBook(bookDto);
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not exist with id :" + id));
		return ResponseEntity.ok(book);
	}

	@PutMapping("/book/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
		return update.updateBook(id, bookDto);
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not exist with id :" + id));
		bookRepository.delete(book);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/book/search/{param}")
	public List<BookPresent> getBooksBySearch(@PathVariable String param) {
		return bookConverter.toCollection(bookRepository.findByTitleLike("%"+param+"%"));
	}
	
	@PostMapping("/book/vitrine")
	public Page<Book> getBooksPages(@RequestBody VitrineDto vitrineDto) {
		return vitrine.vitrinePagination(vitrineDto);
	}
}
