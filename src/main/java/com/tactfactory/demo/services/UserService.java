package com.tactfactory.demo.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tactfactory.demo.entities.Role;
import com.tactfactory.demo.entities.User;
import com.tactfactory.demo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleService roleService;

	public List<User> findAll() {
		return this.repository.findAll();
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
}
