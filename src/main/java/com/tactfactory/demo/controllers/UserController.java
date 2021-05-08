package com.tactfactory.demo.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tactfactory.demo.dtos.UserDto;
import com.tactfactory.demo.entities.Book;
import com.tactfactory.demo.entities.Role;
import com.tactfactory.demo.entities.User;
import com.tactfactory.demo.services.BookService;
import com.tactfactory.demo.services.RoleService;
import com.tactfactory.demo.services.UserService;

@Controller
@RequestMapping(UserController.BASE_ROUTE)
public class UserController extends BaseCrudController<User, UserDto> {

    public static final String TEMPLATE_NAME = "user";
    public static final String BASE_ROUTE = "user";

    public UserController() {
        super(TEMPLATE_NAME);
    }

    @Autowired
    private UserService service;

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private BookService bookService;

    @GetMapping("testgen")
    public void testGen() {
        this.service.generateUsers(20);
    }

    @Override
    protected void preCreateGet(final Model model) {
        model.addAttribute("roles", this.roleService.getTemplateList());
    }

    @Override
    protected User preCreatePost(UserDto dto, HttpServletRequest request) throws Exception {
        User user = new User();
        String firstname = dto.getFirstname();
        String lastname = dto.getLastname();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        Long roleId = dto.getRoleId();
        if (roleId != null) {
            Role role = this.roleService.findRole(roleId);
            if (role == null) {
                throw new Exception("Role voulu introuvable");
            }
            user.setRole(role);
        }

        return user;
    }
    
    @GetMapping("connect/{id}")
    public void connect(@PathVariable final Long userId, final HttpServletResponse response) throws IOException {
    	Cookie cookie = new Cookie("userId", Long.toString(userId));
    	cookie.setPath("/");
    	response.addCookie(cookie);
    	User user = service.findById(userId).get();
    	String roleName = user.getRole().getName();
		if(roleName == "Seller") {
			response.sendRedirect("/book/create");
		}
		else if (roleName == "Customer") {
			response.sendRedirect("/book/index");
		}
		
    }
    
    @SuppressWarnings("static-access")
	@GetMapping("myBook")
	protected String buyAbook(@CookieValue(value= "userId", defaultValue = "0") String strUserId, final Model model, final HttpServletResponse response, HttpServletRequest request) {
    	User user = service.findById(Long.parseLong(strUserId)).get();
    	List<Book> books = bookService.findByUser(user);
    	model.addAttribute("items", books);

		return "/" + this.TEMPLATE_NAME + "/myBook";
	}
}