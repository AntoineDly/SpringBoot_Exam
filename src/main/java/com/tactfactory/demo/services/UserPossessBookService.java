package com.tactfactory.demo.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tactfactory.demo.entities.User;
import com.tactfactory.demo.entities.Book;
import com.tactfactory.demo.entities.UserPossessBook;
import com.tactfactory.demo.repositories.UserPossessBookRepository;

@Service
public class UserPossessBookService {
	
	@Autowired
	private UserPossessBookRepository repository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	public List<UserPossessBook> findByBookId (final Iterable<Long> bookId) {
		return this.repository.findAllById(bookId);
	}
	
	public List<UserPossessBook> findByUserId (final Iterable<Long> userId) {
		return this.repository.findAllById(userId);
	}
	
	public void generateUsersPossessingBooks () {
		List<User> users = userService.findAll();
		List<Book> books = bookService.findAll();
		
		Random random = new Random();
		for(User user : users) {
			int index = random.nextInt(books.size() - 1);
			UserPossessBook userPossessBook = new UserPossessBook();
			userPossessBook.setUser(user);
			userPossessBook.setBook(books.get(index));
			
			this.repository.save(userPossessBook);
		}
	}

}
