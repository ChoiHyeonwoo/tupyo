package com.ex.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

	@RequestMapping("/m_register")
	public String register(){
		
		return "m_register";
	}

}
