package com.vitrine.livraria.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitrine.livraria.models.Book;
import com.vitrine.livraria.present.BookPresent;

@Service
public class BookConverter {
	@Autowired
	ModelMapper modelMapper;
	
	public List<BookPresent> toCollection(List<Book> books) {
		return books.stream().map(book -> toModel(book)).collect(Collectors.toList());
	}

	public BookPresent toModel(Book book) {
		return modelMapper.map(book, BookPresent.class);
	}
}
