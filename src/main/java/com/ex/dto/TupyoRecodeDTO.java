package com.ex.dto;

import java.util.Date;

public class TupyoRecodeDTO {
	
	int pk_tid;
	int t_id;
	String t_member;
	String t_content;
	Date t_date;
	
	
	public Date getT_date() {
		return t_date;
	}
	public void setT_date(Date t_date) {
		this.t_date = t_date;
	}
	public TupyoRecodeDTO(int _pk_tid, int _t_id, String _t_member, String _t_content, Date _t_date) {
		// TODO Auto-generated constructor stub
		this.pk_tid = _pk_tid;
		this.t_id = _t_id;
		this.t_member = _t_member;
		this.t_content = _t_content;
		this.t_date = _t_date;
			
	}
	public int getPk_tid() {
		return pk_tid;
	}
	public void setPk_tid(int pk_tid) {
		this.pk_tid = pk_tid;
	}
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public String getT_member() {
		return t_member;
	}
	public void setT_member(String t_member) {
		this.t_member = t_member;
	}
	public String getT_content() {
		return t_content;
	}
	public void setT_content(String t_content) {
		this.t_content = t_content;
	}

}
