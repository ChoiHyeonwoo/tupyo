package com.ex.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	@RequestMapping(value= "/m_check_id", method=RequestMethod.POST)
	public void check_id(HttpServletResponse response, HttpServletRequest request, Model model){
		String id = request.getParameter("id");
		
		MemberDAO mdao = new MemberDAO();
		String result = mdao.check_id(id);
		System.out.println(result);
		
		try {
			response.getWriter().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		

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
		if(mdtos.isEmpty()){
			model.addAttribute("error", "아이디 비밀번호를 확인해 주세요.");
			return "/m_login";
		}
		
	
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
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		MemberDAO mdao = new MemberDAO();
		mdao.update(pk_id, id, password, name);
		mdao.update_log(id, "info_update");
		
		request.getSession().setAttribute("id", id);
		request.getSession().setAttribute("name", name);
		request.getSession().setMaxInactiveInterval(300);

		
		return "redirect:/";
	}
	
	@RequestMapping(value="/m_password_check", method=RequestMethod.POST)
	public void password_check_update(HttpServletResponse response, HttpServletRequest request, Model model)
	{
		model.addAttribute("request", request);
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String password = request.getParameter("password");
		
		MemberDAO mdao = new MemberDAO();
		String check_password = mdao.check_password(id, password); 
		
		System.out.println("패스워드 확인 결과: " +check_password);
		model.addAttribute("result",check_password);
		
		try {
			response.getWriter().print(check_password);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		
	}
	@RequestMapping("/m_check_log")
	public String check_log(HttpServletRequest request, HttpServletResponse response, Model model){
		
		model.addAttribute("request", request);
		
		
		return "/m_check_log";
		
	}
	@RequestMapping("/m_log")
	public String log_view(HttpServletRequest request, HttpServletResponse response, Model model){
		
		model.addAttribute("request", request);
		HttpSession session = request.getSession();
		
		String _mlogid = (String)session.getAttribute("id");
			
		MemberDAO mdao = new MemberDAO();
		ArrayList<MemberLogDTO> mldtos = mdao.member_log(_mlogid);

		model.addAttribute("mldtos", mldtos);
		
		return "/m_log";
		
	}
	
	@RequestMapping("/m_check_destroy")
	public String check_destroy(HttpServletRequest request, HttpServletResponse response, Model model){
		
		model.addAttribute("request", request);
		
		
		return "/m_check_destroy";
		
	}
	@RequestMapping(value = "/m_destroy")
	public String destroy(HttpServletRequest request, HttpServletResponse response, Model model){
		
		model.addAttribute("request", request);
		
		String pk_id = (String)request.getSession().getAttribute("pk_id");
		String loglid = (String)request.getSession().getAttribute("id");
		
		MemberDAO mdao = new MemberDAO();
		mdao.update_log(loglid, "destroy");
		mdao.destroy(pk_id);
		
		request.getSession().invalidate();

		return "/m_destroy";
	}
	
	
}
