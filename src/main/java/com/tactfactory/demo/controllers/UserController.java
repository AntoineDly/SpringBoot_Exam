package com.tactfactory.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tactfactory.demo.dtos.UserDto;
import com.tactfactory.demo.entities.Role;
import com.tactfactory.demo.entities.User;
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

    @GetMapping("testgen")
    public void testGen() {
        this.service.generateUsers(20);
    }

    @Override
    protected void preCreateGet(final Model model) {
        model.addAttribute("roles", this.roleService.getTemplateList());
    }

    @Override
    protected User preCreatePost(UserDto dto) throws Exception {
        User user = new User();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());

        if (dto.getRoleId() != null) {
            Role role = this.roleService.findRole(dto.getRoleId());
            if (role == null) {
                throw new Exception("Cannot find whished role");
            }
            user.setRole(role);
        }

        return user;
    }
}