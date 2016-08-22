package com.ex.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ex.dao.AdminDAO;
import com.ex.dao.MemberDAO;
import com.ex.dto.MemberDTO;
import com.ex.dto.MemberLogDTO;



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
			
	@RequestMapping(value = "/admin_login_confirm")
	public void admin_login_confirm(HttpServletRequest request, HttpServletResponse response, Model model){
		
		model.addAttribute("request", request);
		
		String id = request.getParameter("logid");
		String password = request.getParameter("logpw");
		
		AdminDAO adao = new AdminDAO();
		MemberDTO mdtos = adao.admin_login(id, password);
	
		if(mdtos == null){
			try {
				response.getWriter().print("fail");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}else if(mdtos.getPk_mid() == -1){
			try {
				response.getWriter().print("drop");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			request.getSession().setAttribute("pk_id", ""+mdtos.getPk_mid());
			request.getSession().setAttribute("id", mdtos.getId());
			request.getSession().setAttribute("name", mdtos.getName());
			request.getSession().setAttribute("grade", mdtos.getGrade());
			request.getSession().setMaxInactiveInterval(1200);
			model.addAttribute("mdtos", mdtos);
	
			try {
				response.getWriter().print("success");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@RequestMapping(value = "/admin_main")
	public String admin_main(HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		AdminDAO adao = new AdminDAO();
		ArrayList<MemberDTO> mdtos = adao.member_list_view();
		
		model.addAttribute("mdtos", mdtos);
		
		return "admin_main";
	}
	@RequestMapping(value = "/admin_user_detail")
	public String admin_user_detail(HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		String pk_id = request.getParameter("pk_id");
		AdminDAO adao = new AdminDAO();
		MemberDTO mdto = adao.user_detail_view(pk_id);
		
		model.addAttribute("mdto", mdto);
		
		return "admin_user_detail";
	
	}
	@RequestMapping(value = "/admin_user_drop")
	public void admin_user_drop(HttpServletResponse response, HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		String pk_id = request.getParameter("pk_id");
		
		AdminDAO adao = new AdminDAO();
		String result = adao.user_drop(pk_id);
		
		try {
			response.getWriter().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	@RequestMapping(value = "/admin_info_update")
	public void admin_info_update(HttpServletResponse response, HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		String pk_id = request.getParameter("pk_id");
		String id = request.getParameter("id");
		String name = request.getParameter("name");

		String grade = request.getParameter("grade");
		
		AdminDAO adao = new AdminDAO();
		String result = adao.user_info_update(pk_id, id, name,  grade);
		
		try {
			response.getWriter().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	@RequestMapping(value = "/admin_log_view")
	public String admin_log_view(HttpServletResponse response, HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		String id = request.getParameter("id");

		AdminDAO adao = new AdminDAO();
		ArrayList<MemberLogDTO> mldtos = adao.user_log_view(id);
		
		model.addAttribute("mldtos", mldtos);
		
		return "admin_log_view";
	}
	@RequestMapping(value = "/admin_member_register")
	public String admin_member_register(HttpServletResponse response, HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		return "admin_member_register";
	}
	@RequestMapping(value = "/admin_logout")
	public void admin_logout(HttpServletResponse response, HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		String logmid = (String)request.getSession().getAttribute("id");
		MemberDAO mdao = new MemberDAO();
		mdao.logout(logmid);
		
		request.getSession().invalidate();

	}
	@RequestMapping(value = "/admin_password_update")
	public void admin_password_update(HttpServletResponse response, HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		String pk_id = request.getParameter("pk_id");
		String password = request.getParameter("password");
		AdminDAO adao = new AdminDAO();
		String result = adao.user_password_update(pk_id,  password);
		
		try {
			response.getWriter().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/admin_password_modify")
	public String admin_password_modify(HttpServletResponse response, HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		
		return "admin_password_modify";

	}
}
