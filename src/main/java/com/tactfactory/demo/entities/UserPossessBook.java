package com.tactfactory.demo.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class UserPossessBook extends BaseEntity {
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Book book;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
