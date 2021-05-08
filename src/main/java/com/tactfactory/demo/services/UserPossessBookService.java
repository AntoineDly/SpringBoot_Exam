package com.tactfactory.demo.services;

import java.util.ArrayList;
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
	
	public List<UserPossessBook> findAll() {
		return this.repository.findAll();
	}
	
	public List<UserPossessBook> findByBookId (final Iterable<Long> bookId) {
		return this.repository.findAllById(bookId);
	}
	
	public List<UserPossessBook> findByUserId (final Iterable<Long> userId) {
		return this.repository.findAllById(userId);
	}
	
	public List<UserPossessBook> findByBook (Book book) {
		List<UserPossessBook> relationsWithBook = new ArrayList<UserPossessBook>();
		List<UserPossessBook> allRelations = this.findAll();
		
		for(UserPossessBook relation : allRelations) {
			if(relation.getBook() == book) {
				relationsWithBook.add(relation);
			}
		}
		
		return relationsWithBook;
		
	}
	
	public List<UserPossessBook> findByUser (User user) {
		List<UserPossessBook> relationsWithBook = new ArrayList<UserPossessBook>();
		List<UserPossessBook> allRelations = this.findAll();
		
		for(UserPossessBook relation : allRelations) {
			if(relation.getUser() == user) {
				relationsWithBook.add(relation);
			}
		}
		
		return relationsWithBook;
	}
	
	public void generateUsersPossessingBooks() {
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
	
	public UserPossessBook findByBookAndUser(Book book, User user) {
		List<UserPossessBook> allRelations = this.findAll();
		UserPossessBook result = null;
		for(UserPossessBook relation : allRelations) {
			Book relationBook = relation.getBook();
			User relationUser = relation.getUser();
			if(relationBook.equals(book) && relationUser.equals(user)) {
				result = relation;
			}
		}
		
		return result;
	}
	
	public void buyAbook (Book book, User userSeller, User userBuyer) {
		UserPossessBook relation = this.findByBookAndUser(book, userSeller);
		relation.setUser(userBuyer);
		
		this.repository.save(relation);
	}
	
	public void linkABookToUser (Book book, User user) {
		UserPossessBook link = new UserPossessBook();
		link.setBook(book);
		link.setUser(user);
		this.repository.save(link);
	}

}
