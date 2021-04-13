package com.tactfactory.demo.controllers;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {

	@GetMapping(value = {"/", "", "index"})
	public String index() {
		return "/testcontroller/index";
	}

	@GetMapping("test1")
	public String test1() {
		return "/testcontroller/test";
	}

	@GetMapping("test2/{id}")
	public String test2(@PathVariable Integer id) {
		System.out.println(id);
		return "/testcontroller/test";
	}

	@GetMapping("test3")
	public String test3(
			@PathParam("id") Integer id,
			@PathParam("isOk") Boolean isOk) {
		System.out.println(id);
		System.out.println(isOk);
		return "/testcontroller/test";
	}

	@GetMapping("test4")
	public String test4(final Model model) {

		model.addAttribute("model1", "coucou");
		model.addAttribute("model2", "bonjour");
		model.addAttribute("model2", "hey");

		return "/testcontroller/test4";
	}
}