package com.tactfactory.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tactfactory.demo.dtos.RoleDto;
import com.tactfactory.demo.entities.Role;
import com.tactfactory.demo.services.RoleService;

@Controller
@RequestMapping(RoleController.BASE_ROUTE)
public class RoleController extends BaseCrudController<Role, Role> {
    public static final String TEMPLATE_NAME = "role";
    public static final String BASE_ROUTE = "role";
    
    @Autowired
    private RoleService service;
    
    public RoleController() {
        super(TEMPLATE_NAME);
    }
    
    protected Role preCreatePost(RoleDto dto) throws Exception {
    	Role role = new Role();
    	role.setName(dto.getName());
    	
    	return role;
    }
    
    @GetMapping("testgen")
    public void testGen() {
        this.service.genenerateBasicRoles();
    }
}