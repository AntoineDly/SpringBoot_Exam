package com.tactfactory.demo.controllers;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tactfactory.demo.dtos.BookDto;
import com.tactfactory.demo.entities.Book;
import com.tactfactory.demo.entities.User;
import com.tactfactory.demo.services.BookService;
import com.tactfactory.demo.services.UserPossessBookService;
import com.tactfactory.demo.services.UserService;

@Controller
@RequestMapping(BookController.BASE_ROUTE)
public class BookController extends BaseCrudController<Book, BookDto> {
    public static final String TEMPLATE_NAME = "book";
    public static final String BASE_ROUTE = "book";

    public BookController() {
        super(TEMPLATE_NAME);
    }
    
    @Autowired
    private BookService service; 
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserPossessBookService userPossessBookService;
    
    @Override
	public String details(@PathVariable final Long id, final Model model) {
		System.out.println(id);
		Book book = service.findById(id).get();
		model.addAttribute("users",userService.getUserListBook(book));

		return super.details(id, model);
	}
    
    @Override
	protected Book preCreatePost(BookDto dto, HttpServletRequest req) throws Exception {
		Book book = new Book();
		book.setName(dto.getName());
		book.setNbPage(dto.getNbPage());
		book.setPrice(dto.getPrice());
		if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals("idUser")) {
                	User user = userService.findById(Long.parseLong(cookie.getValue())).get();
                	userPossessBookService.linkABookToUser(book, user);
                }
            }
        }	
		
		return super.preCreatePost(dto, req);
	}
	
	
	@GetMapping("buy/{idBook}/{idUser}")
	protected void buyAbook(@CookieValue(value= "idUser", defaultValue = "0") String strIdUser, @PathVariable final Long idBook, @PathVariable final Long idUser, final HttpServletResponse response, HttpServletRequest request) throws IOException {
		Book book = service.findById(idBook).get();
		User seller = userService.findById(idUser).get();
		User customer = userService.findById(Long.parseLong(strIdUser)).get();
		System.out.println("Controller");
		userPossessBookService.buyAbook(book, seller, customer);
		response.sendRedirect("/book/index");
	}
	
	
	@SuppressWarnings("static-access")
	@PostMapping("search")
	protected String searchByName(final Model model, @RequestParam("inputName") String name, @RequestParam("inputnbPageMin") String nbPageMin, @RequestParam("inputnbPageMax") String nbPageMax , @RequestParam("inputPriceMin") String priceMin , @RequestParam("inputPriceMax") String priceMax) {
		int myNbPageMin = 0;
		if(nbPageMin != "") {
			myNbPageMin = Integer.parseInt(nbPageMin);
		}
		int myNbPageMax = Integer.MAX_VALUE;
		if(nbPageMax != "") {
			myNbPageMax = Integer.parseInt(nbPageMax);
		}
		
		Float myPriceMin = 0F;
		if(nbPageMin != "") {
			myPriceMin = Float.parseFloat(priceMin);
		}
		Float myPriceMax = Float.MAX_VALUE;
		if(priceMax != "") {
			myPriceMax = Float.parseFloat(priceMax);
		}
		
		
		model.addAttribute("items", service.search(name, myPriceMin, myPriceMax, myNbPageMin, myNbPageMax));

		return "/" + this.TEMPLATE_NAME + "/index";
	}
    
    
}