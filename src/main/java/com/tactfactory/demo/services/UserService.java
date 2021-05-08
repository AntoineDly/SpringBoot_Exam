package com.tactfactory.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tactfactory.demo.entities.Book;
import com.tactfactory.demo.entities.Role;
import com.tactfactory.demo.entities.User;
import com.tactfactory.demo.entities.UserPossessBook;
import com.tactfactory.demo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserPossessBookService userPossessBookService;

	public List<User> findAll() {
		return this.repository.findAll();
	}
	
	public Optional<User> findById(Long userId) {
		return this.repository.findById(userId);
    }
	
	public User findUser(final Long userId) {
        return this.repository.findById(userId).orElse(null);
    }
	
	public List<User> findByBook(Book book) {
		List<User> users = new ArrayList<User>();
		List<UserPossessBook> allRelations = userPossessBookService.findByBook(book);
		for(UserPossessBook relation : allRelations) {
			users.add(relation.getUser());
		}
		return users;
	}

//	public void save(User user) {
//		this.repository.save(user);
//	}

	public void generateUsers(final Integer nb) {
		
		List<Role> roles = roleService.findAll();
		Random random = new Random();
		for (int i = 0; i < nb; i++) {
			User user = new User();
			user.setFirstname("fname"+i);
			user.setLastname("Lname"+i);

			int index = random.nextInt(roles.size() - 1);
			user.setRole(roles.get(index));
			
			this.repository.save(user);
		}
	}
	
	public ArrayList<User> getSeller(){
		ArrayList<User> users = new ArrayList<User>();
		List<User> allUsers = this.findAll();
		for (User user : allUsers) {
			String roleName = user.getRole().getName();
			if(roleName == "Seller") {
				users.add(user);
			}
        }
		
		return users;
	}
	
	public Boolean isSeller(User user) {
		String roleName = user.getRole().getName();
		return roleName == "Seller";
	}
	
	public List<User> getUserListBook(Book book){
        List<User> result = new ArrayList<User>();
        List<User> users = this.findByBook(book);
        for (User user : users) {
        	if(this.isSeller(user)) {
        		String name = user.getFirstname() + user.getLastname();
        		System.out.println(name + " is a seller with the book");
    			result.add(user);
			}
        }

        return result;
        
    }
	
}
