package com.ex.tupyo;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
	public String result(Model model, HttpServletRequest request){
		
		model.addAttribute("request", request);
		String result = request.getParameter("survay");
		String id = request.getParameter("id");
		
		BaseDAO dao = new BaseDAO();
		dao.upHit(result, id);
	
		
		model.addAttribute("result", result);
		System.out.println("result : " + result);
		System.out.println("id : " + result);
		return "result";
		
	}
	@RequestMapping(value = "/execute", method=RequestMethod.GET)
	public String execute(HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		String id = request.getParameter("id");
		
		model.addAttribute("id", id);
		
		return "execute";
	}
	
}
