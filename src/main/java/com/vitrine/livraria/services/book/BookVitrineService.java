package com.vitrine.livraria.services.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vitrine.livraria.dto.VitrineDto;
import com.vitrine.livraria.models.Book;
import com.vitrine.livraria.repository.BookRepository;

@Service
public class BookVitrineService {
	@Autowired
	private BookRepository bookRepository;

	public Page<Book> vitrinePagination(VitrineDto vitrineDto) {
		Pageable paging = PageRequest.of(
				vitrineDto.getCurrentPage()-1,
				vitrineDto.getPerPage(),
				vitrineDto.getOrderBy().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
				"title");

        Page<Book> pagedResult = bookRepository.findAll(paging);

        return pagedResult;
	}
}
