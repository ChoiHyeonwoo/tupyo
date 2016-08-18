package com.ex.member;

import java.sql.Date;

public class MemberDTO {
	
	String id;
	String password;
	String name;
	String curr_user;
	Date reg_date;
	Date drop_date;
	int pk_mid;
	int grade;
	String reg_person;
	
	public MemberDTO(String _id, String _password, String _name, String _curr_user, Date _reg_date, Date _drop_date, int _pk_mid, int _grade, String _reg_person) {
		this.id = _id;
		this.password = _password;
		this.name = _name;
		this.curr_user = _curr_user;
		this.reg_date = _reg_date;
		this.drop_date = _drop_date;
		this.pk_mid = _pk_mid;
		this.grade = _grade;
		this.reg_person = _reg_person;
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

	public String getCurr_user() {
		return curr_user;
	}

	public void setCurr_user(String curr_user) {
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

	public int getPk_mid() {
		return pk_mid;
	}

	public void setPk_mid(int pk_mid) {
		this.pk_mid = pk_mid;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getReg_person() {
		return reg_person;
	}

	public void setReg_person(String reg_person) {
		this.reg_person = reg_person;
	}

}
