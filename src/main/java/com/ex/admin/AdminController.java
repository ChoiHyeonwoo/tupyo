package com.ex.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class AdminController {
	
	public AdminController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/admin_login")
	public String admin_login(HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		return "admin_login";
	
	}

}
