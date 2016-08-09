package com.ex.tupyo;

import java.util.Date;

public class TupyoDTO {

	int id;
	String title;
	String writer;
	String is_duplicated;
	Date reg_date;
	
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getIs_duplicated() {
		return is_duplicated;
	}
	public void setIs_duplicated(String is_duplicated) {
		this.is_duplicated = is_duplicated;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}

	public TupyoDTO(int _id, String _title, String _writer, String _is_duplicated, Date _reg_date) {
		// TODO Auto-generated constructor stub
		this.id = _id;
		this.title = _title;
		this.writer = _writer;
		this.is_duplicated = _is_duplicated;
		this.reg_date = _reg_date;
	}
	
	

}
