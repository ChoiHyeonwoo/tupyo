package com.ex.member;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

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
		mdao.update_log(loglid, "session expire");
	} 

}
