package com.tactfactory.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tactfactory.demo.entities.Role;
import com.tactfactory.demo.repositories.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public Map<Long, String> getTemplateList(){
        Map<Long, String> result = new HashMap<>();

        for (Role item : this.repository.findAll()) {
            result.put(item.getId(), item.getName());
        }

        return result;
    }

    public Role findRole(final Long roleId) {
        return this.repository.findById(roleId).orElse(null);
    }
    
    public List<Role> findAll() {
        return this.repository.findAll();
    }
    
    public void genenerateBasicRoles() {
    	ArrayList<String> roles = new ArrayList<>();
    	roles.add("Customer");
    	roles.add("Seller");
    	
    	for(String role : roles) {
    		Role tempRole = new Role();
    		tempRole.setName(role);
    		
    		this.repository.save(tempRole);
    	}
    }
}