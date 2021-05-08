package com.tactfactory.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.tactfactory.demo.entities.Book;
import com.tactfactory.demo.entities.User;
import com.tactfactory.demo.entities.UserPossessBook;
import com.tactfactory.demo.repositories.BookRepository;

public class BookService {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private UserPossessBookService userPossessBookService;
	
	public List<Book> findAll() {
		return this.repository.findAll();
	}
	
	public Optional<Book> findById(Long bookId) {
		return this.repository.findById(bookId);
    }
	
	public Book findBook(final Long bookId) {
        return this.repository.findById(bookId).orElse(null);
    }
	
	public void generateBooks(final Integer nb) {
		for (int i = 0; i < nb; i++) {
			Book book = new Book();
			book.setName("bname"+i);
			book.setNbPage(100+i);
			book.setPrice(10F+i);

			this.repository.save(book);
		}
	}
	
	public List<Book> findByUser(User user) {
		List<Book> books = new ArrayList<Book>();
		List<UserPossessBook> allRelations = userPossessBookService.findByUser(user);
		for(UserPossessBook relation : allRelations) {
			books.add(relation.getBook());
		}
		return books;
	}
	
	public List<Book> search(String name, Float priceMin ,Float priceMax, int nbPageMin, int nbPageMax){
		return repository.findByNameLikeAndPriceBetweenAndNbPageBetween("%" + name + "%", priceMin, priceMax, nbPageMin, nbPageMax);
	}

}
