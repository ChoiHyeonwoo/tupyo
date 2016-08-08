package com.ex.tupyo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		BaseDAO dao =  new BaseDAO();
		ArrayList<TupyoDTO> title = dao.titleView();
		
		model.addAttribute("title", title);

		return "home";
	}
	@RequestMapping(value = "/result", method=RequestMethod.GET)
	public void result(HttpServletResponse response,Model model, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String member_id = (String)session.getAttribute("id");
		
		model.addAttribute("request", request);
		String result = request.getParameter("survay");
		String t_id = request.getParameter("id");
		
		BaseDAO dao = new BaseDAO();
		String is_tupyo=dao.had_tupyo(t_id, member_id);
		String is_duplicated = dao.is_duplicated(t_id);
		
		if(is_tupyo.equals("available") || is_duplicated.equals("yes")){
					
			dao.tupyo_log(t_id, member_id, result);
			int result_arr[] = dao.getTupyoResult(t_id); 
			dao.upHit(result, t_id, result_arr[0], result_arr[1]);
			
			model.addAttribute("result", result);
			System.out.println("result : " + result);
			System.out.println("id : " + result);	
		}
		try {
			response.getWriter().print(is_tupyo+",");
			response.getWriter().print(is_duplicated);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		
	}
	@RequestMapping(value = "/execute", method=RequestMethod.GET)
	public String execute(HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		String id = request.getParameter("id");
		
		model.addAttribute("id", id);
		
		return "execute";
	}
	
	@RequestMapping(value = "/register")
	public String register(HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		return "register";
	}
	@RequestMapping(value = "/register_confirm")
	public String register_confirm(HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		String poll_title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String is_duplicated = request.getParameter("duplicated");
		
		BaseDAO dao = new BaseDAO();
		dao.reg_poll(poll_title, writer, is_duplicated);
		
		return "register";
	}
	
}
