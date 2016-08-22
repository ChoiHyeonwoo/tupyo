package com.ex.sessionlistener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ex.dao.MemberDAO;

public class MemberSessionListener implements HttpSessionListener {

	public MemberSessionListener() {
		// TODO Auto-generated constructor stub
	}
	public void sessionCreated(HttpSessionEvent arg0) {
		// ���� ������ ȣ��
		 
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
	// ���� ����� ȣ��
		MemberDAO mdao = new MemberDAO();
		HttpSession session = arg0.getSession();
		String loglid = (String)session.getAttribute("id");
		System.out.println("session destroy id : "+loglid);
		mdao.update_log(loglid, "session expire");
	}

}
