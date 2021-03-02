package com.vitrine.livraria.services.book;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vitrine.livraria.converter.BookConverter;
import com.vitrine.livraria.dto.BookDto;
import com.vitrine.livraria.models.Book;
import com.vitrine.livraria.present.BookPresent;
import com.vitrine.livraria.repository.BookRepository;
import com.vitrine.livraria.repository.AuthorRepository;

@Service
public class BookStoreService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookConverter bookConverter;

	public ResponseEntity<BookPresent> createBook(BookDto bookDto) {
		ModelMapper modelMapper = new ModelMapper();
		Book book = modelMapper.map(bookDto, Book.class);
		book.setAuthor(authorRepository.getOne(bookDto.getAuthorId()));
		return ResponseEntity.ok(bookConverter.toModel(bookRepository.save(book)));
	}
}
