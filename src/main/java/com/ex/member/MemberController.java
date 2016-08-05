package com.ex.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public String confirm(HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		MemberDAO mdao = new MemberDAO();
		String confirm = mdao.register(id, password, name);
		
		model.addAttribute("confirm", confirm);
		
		return "m_confirm";
	
	}
	@RequestMapping("/m_login")
	public String login(Model model){
		
		
		return "m_login";
	}
	@RequestMapping("/m_check")
	public String check(HttpServletRequest request, Model model){
		
		ArrayList<MemberDTO> mdtos = new ArrayList<MemberDTO>();
		model.addAttribute("request", request);
		
		String id = request.getParameter("logid");
		String password = request.getParameter("logpw");
		
		MemberDAO mdao = new MemberDAO();
		mdtos = mdao.login_check(id, password);
		
	
		request.getSession().setAttribute("pk_id", ""+mdtos.get(0).pk_mid);
		request.getSession().setAttribute("id", mdtos.get(0).id);
		request.getSession().setAttribute("name", mdtos.get(0).name);
		request.getSession().setMaxInactiveInterval(300);
		model.addAttribute("mdtos", mdtos);

		return "redirect:/";
	}
	@RequestMapping("/m_logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		
		String loglid = (String)request.getSession().getAttribute("id");
		MemberDAO mdao = new MemberDAO();
		mdao.logout(loglid);
		
		request.getSession().invalidate();
			
		
		return "redirect:/";
	}
	@RequestMapping("/m_check_update")
	public String check_update(HttpServletRequest request, HttpServletResponse response, Model model){
		
		model.addAttribute("request", request);
		
		return "/m_check_update";
	}
	@RequestMapping("/m_update")
	public String update(HttpServletRequest request, HttpServletResponse response, Model model){

		model.addAttribute("request", request);
				
		return "/m_update";
	}
	@RequestMapping(value = "/m_update_complete", method=RequestMethod.POST)
	public String update_complete(HttpServletRequest request, Model model){
		
		
		model.addAttribute("request", request);
		
		String pk_id = (String)request.getSession().getAttribute("pk_id");
		String id = request.getParameter("logid");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		MemberDAO mdao = new MemberDAO();
		mdao.update(pk_id, id, password, name);

		
		request.getSession().setAttribute("id", id);
		request.getSession().setAttribute("name", name);
		request.getSession().setMaxInactiveInterval(300);

		
		return "redirect:/";
	}
	
}
