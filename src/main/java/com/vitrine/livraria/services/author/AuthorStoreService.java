package com.vitrine.livraria.services.author;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vitrine.livraria.converter.AuthorConverter;
import com.vitrine.livraria.dto.AuthorDto;
import com.vitrine.livraria.models.Author;
import com.vitrine.livraria.present.AuthorPresent;
import com.vitrine.livraria.repository.AuthorRepository;

@Service
public class AuthorStoreService {
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private AuthorConverter authorConverter;

	public ResponseEntity<AuthorPresent> createAuthor(AuthorDto authorDto) {
		ModelMapper modelMapper = new ModelMapper();
		Author author = modelMapper.map(authorDto, Author.class);
		return ResponseEntity.ok(authorConverter.toModel(authorRepository.save(author)));
	}
}
