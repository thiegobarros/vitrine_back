package com.vitrine.livraria.services.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vitrine.livraria.exception.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vitrine.livraria.dto.BookDto;
import com.vitrine.livraria.models.Book;
import com.vitrine.livraria.repository.AuthorRepository;
import com.vitrine.livraria.repository.BookRepository;

@Service
public class BookUpdateService {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	public ResponseEntity<Book> updateBook(Long id, BookDto bookDto) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not exist with id :" + id));
		book.setTitle(bookDto.getTitle());
		book.setIsbn(bookDto.getIsbn());
		book.setAuthor(authorRepository.findById(bookDto.getAuthorId()).get());
		Book updatedBook = bookRepository.save(book);
		return ResponseEntity.ok(updatedBook);
	}
}
