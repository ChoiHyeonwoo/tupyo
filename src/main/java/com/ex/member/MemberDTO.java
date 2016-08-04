package com.ex.member;

import java.sql.Date;

public class MemberDTO {
	
	String id;
	String password;
	String name;
	char curr_user;
	Date reg_date;
	Date drop_date;
	int pk_id;
	
	public MemberDTO(String _id, String _password, String _name, char _curr_user, Date _reg_date, Date _drop_date, int _pk_id) {
		this.id = _id;
		this.password = _password;
		this.name = _name;
		this.curr_user = _curr_user;
		this.reg_date = _reg_date;
		this.drop_date = _drop_date;
		this.pk_id = _pk_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getCurr_user() {
		return curr_user;
	}

	public void setCurr_user(char curr_user) {
		this.curr_user = curr_user;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Date getDrop_date() {
		return drop_date;
	}

	public void setDrop_date(Date drop_date) {
		this.drop_date = drop_date;
	}

	public int getPk_id() {
		return pk_id;
	}

	public void setPk_id(int pk_id) {
		this.pk_id = pk_id;
	}

}
