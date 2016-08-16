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
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method ={RequestMethod.POST, RequestMethod.GET})
	public String home(/*@RequestBody MultiValueMap<String, String> params,*/ HttpServletResponse response, HttpServletRequest request, Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		model.addAttribute("request", request);
				
		String option = request.getParameter("option");
		String content = request.getParameter("content");

		String arr[] ={option, content};
				
		BaseDAO dao =  new BaseDAO();
		ArrayList<TupyoDTO> tupyo_list = dao.titleView(arr);
		if(tupyo_list.size()==0){
			try {
				response.getWriter().print("none");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		model.addAttribute("search_option", option);
		model.addAttribute("search_content", content);
		model.addAttribute("tupyo_list", tupyo_list);
		
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
			String result_arr[] = dao.getTupyoResult(t_id, result); 
			dao.upHit(result, t_id, result_arr[0], result_arr[1]);
			
			model.addAttribute("result", result);

		}
		try {
			response.getWriter().print(is_tupyo+",");
			response.getWriter().print(is_duplicated);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
		
	@RequestMapping(value = "/result_multi", method=RequestMethod.POST)
	public void result_multi(@RequestParam(value = "item_arr[]", required = true) String[] t_item_content, HttpServletResponse response,Model model, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String member_id = (String)session.getAttribute("id");
		
		model.addAttribute("request", request);

		String t_id = request.getParameter("id");
		
		BaseDAO dao = new BaseDAO();
		String is_tupyo=dao.had_tupyo(t_id, member_id);
		String is_duplicated = dao.is_duplicated(t_id);
		
		if(is_tupyo.equals("available") || is_duplicated.equals("yes")){
					
			dao.tupyo_log_multi(t_id, member_id, t_item_content);
			
			ArrayList<TupyoMultiChecked> result_list = dao.getTupyoResult_multi(t_id, t_item_content); 
			dao.upHit_multi(t_id, result_list);
			
			model.addAttribute("result_list", result_list);

		}
		try {
			response.getWriter().print(is_tupyo+",");
			response.getWriter().print(is_duplicated);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		
	}
	@RequestMapping(value = "/execute", method={RequestMethod.POST, RequestMethod.GET})
	public String execute(HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		String t_id = request.getParameter("t_id");
		
		model.addAttribute("id", t_id);
		BaseDAO dao = new BaseDAO();
		
		ArrayList<TupyoItemDTO> tidtos =  dao.tupyo_detail_view(t_id);
		TupyoDTO tdto =  dao.result(t_id);

		model.addAttribute("tidtos", tidtos);

		model.addAttribute("tdto", tdto);	
		return "execute";
	}
	
	@RequestMapping(value = "/register")
	public String register(HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		return "register";
	}
	@RequestMapping(value = "/register_confirm", method=RequestMethod.POST)
	public String register_confirm(@RequestParam(value = "item_arr[]", required = true) String[] t_item_content,HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		String poll_title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String is_duplicated = request.getParameter("duplicated");
		String is_multi_check = request.getParameter("multi");
		String item_number = request.getParameter("item_number");
		String writer_id = request.getParameter("writer_id");
		
		BaseDAO dao = new BaseDAO();
		dao.reg_poll(poll_title, writer, is_duplicated, item_number,is_multi_check, t_item_content, writer_id);
			
		
		return "register";
	}
	@RequestMapping(value = "/t_result", method=RequestMethod.GET)
	public String t_result(HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		HttpSession session = request.getSession();
		String t_member = (String)session.getAttribute("id");
		
		String t_id = request.getParameter("t_id");
		model.addAttribute("t_id", t_id);
		
		BaseDAO dao = new BaseDAO();
		
		String writer_id = dao.get_t_reg_writer(t_id);
		ArrayList<TupyoItemDTO> tidtos =  dao.tupyo_detail_view(t_id);
		ArrayList<MyTupyoContentNumberDTO> mtcns = dao.tupyo_log_view(t_id, t_member);
		
		
		model.addAttribute("t_title", tidtos.get(0).getT_title());
		
		model.addAttribute("writer_id", writer_id);		
		model.addAttribute("tidtos", tidtos);

		model.addAttribute("mtcns", mtcns);	
		
		return "t_result";
	}
	@RequestMapping(value = "/t_delete", method=RequestMethod.POST)
	public void t_delete(HttpServletResponse response, HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		HttpSession session = request.getSession();

		String t_id = request.getParameter("t_id");

		BaseDAO dao = new BaseDAO();
		
		String delete_result = dao.tupyo_delete(t_id);

		try {
			response.getWriter().print(delete_result);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	@RequestMapping(value = "/t_update")
	public String t_update(HttpServletResponse response, HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		HttpSession session = request.getSession();

		String t_id = request.getParameter("t_id");

		BaseDAO dao = new BaseDAO();
	
		ArrayList<TupyoItemDTO> tidtos = dao.tupyo_detail_view(t_id);
		TupyoDTO tdto = dao.get_tupyo_info(t_id);
		
		model.addAttribute("t_id", t_id);
		model.addAttribute("tidtos", tidtos);
		model.addAttribute("tdto", tdto);
		
		return "t_update";
		
	}
// update_confirm controller
	@RequestMapping(value = "/update_confirm", method=RequestMethod.POST)
	public String update_confirm(@RequestParam(value = "item_arr[]", required = true) String[] t_item_content,HttpServletRequest request, Model model){
		
		model.addAttribute("request", request);
		
		String t_id = request.getParameter("t_id");
		String poll_title = request.getParameter("title");
		String is_duplicated = request.getParameter("duplicated");
		String is_multi_check = request.getParameter("multi");
		String item_number = request.getParameter("item_number");

		
		BaseDAO dao = new BaseDAO();
		dao.update_poll(t_id, poll_title, is_duplicated, item_number, is_multi_check, t_item_content);
			
		return "redirect:/";
	}
}
