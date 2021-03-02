package com.vitrine.livraria.present;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vitrine.livraria.models.Author;

public class BookPresent {
	
	private Long id;
	private String title;
	private String isbn;
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Author author;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}

}
