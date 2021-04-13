package com.tactfactory.demo.dtos;

import javax.persistence.Column;

public class UserPossessBookDto {
	
	@Column(nullable = false)
	private Long userId;
	
	@Column(nullable = false)
	private Long bookId;
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

}
