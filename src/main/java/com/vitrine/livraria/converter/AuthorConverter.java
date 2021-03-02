package com.vitrine.livraria.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitrine.livraria.models.Author;
import com.vitrine.livraria.present.AuthorPresent;

@Service
public class AuthorConverter {
	@Autowired
	ModelMapper modelMapper;
	
	public List<AuthorPresent> toCollection(List<Author> authors) {
		return authors.stream().map(author -> toModel(author)).collect(Collectors.toList());
	}

	public AuthorPresent toModel(Author author) {
		return modelMapper.map(author, AuthorPresent.class);
	}
}
