package com.ex.dto;

public class MyTupyoContentNumberDTO {
	
	int select_num;
	String select_content;
	
	
	public int getSelect_num() {
		return select_num;
	}


	public void setSelect_num(int select_num) {
		this.select_num = select_num;
	}


	public String getSelect_content() {
		return select_content;
	}


	public void setSelect_content(String select_content) {
		this.select_content = select_content;
	}


	public MyTupyoContentNumberDTO(int _select_num, String _select_content) {
		// TODO Auto-generated constructor stub
		this.select_num = _select_num;
		this.select_content = _select_content;
	}

}
