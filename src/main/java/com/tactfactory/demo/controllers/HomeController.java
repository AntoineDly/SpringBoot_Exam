package com.tactfactory.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tactfactory.demo.services.BookService;
import com.tactfactory.demo.services.RoleService;
import com.tactfactory.demo.services.UserPossessBookService;
import com.tactfactory.demo.services.UserService;

@Controller
public class HomeController{
	
	 @Autowired
	 private RoleService roleService;
	 
	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 private BookService bookService;
	 
	 @Autowired
	 private UserPossessBookService userPossessBookService;
	 
	 @GetMapping(value = {"/", "", "index"})
	public String index() {
		roleService.genenerateBasicRoles();
		userService.generateUsers(20);
		bookService.generateBooks(10);
		userPossessBookService.generateUsersPossessingBooks();
		return "/home/index";
	}
    
}
