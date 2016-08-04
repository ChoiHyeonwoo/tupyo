package com.ex.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {

	@RequestMapping("/m_register")
	public String register(Model model){
		
		
		return "m_register";
	}
	@RequestMapping(value = "/m_confirm", method=RequestMethod.POST)
	public void confirm(HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		MemberDAO mdao = new MemberDAO();
		String confirm = mdao.register(id, password, name);
		
		model.addAttribute("confirm", confirm);
	
	}
	
}
