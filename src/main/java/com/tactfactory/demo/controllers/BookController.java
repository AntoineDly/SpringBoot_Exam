package com.tactfactory.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tactfactory.demo.dtos.BookDto;
import com.tactfactory.demo.entities.Book;

@Controller
@RequestMapping(BookController.BASE_ROUTE)
public class BookController extends BaseCrudController<Book, Book> {
    public static final String TEMPLATE_NAME = "book";
    public static final String BASE_ROUTE = "book";

    public BookController() {
        super(TEMPLATE_NAME);
    }
    
    protected Book preCreatePost(BookDto dto) throws Exception {
    	Book book = new Book();
    	book.setName(dto.getName());
    	book.setNbPage(dto.getNbPage());
    	book.setPrice(dto.getPrice());
    	
    	return book;
    }
    
    
}