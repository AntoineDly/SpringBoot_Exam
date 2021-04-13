package com.tactfactory.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tactfactory.demo.entities.Book;
import com.tactfactory.demo.repositories.BookRepository;

public class BookService {
	
	@Autowired
	private BookRepository repository;
	
	public List<Book> findAll() {
		return this.repository.findAll();
	}
	
	public Book findBook(final Long bookId) {
        return this.repository.findById(bookId).orElse(null);
    }
	
	public void generateBooks(final Integer nb) {
		for (int i = 0; i < nb; i++) {
			Book book = new Book();
			book.setName("bname"+i);
			book.setNbPage(100+i);
			book.setPrice(10+i);

			this.repository.save(book);
		}
	}

}
