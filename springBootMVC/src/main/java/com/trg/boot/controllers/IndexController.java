package com.trg.boot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Controller class for implementing save method in application and access index.jsp
@Controller
@RequestMapping("/getForm")
public class IndexController {

	@RequestMapping
	public String getEmpForm() {
		return "index";
	}
}
