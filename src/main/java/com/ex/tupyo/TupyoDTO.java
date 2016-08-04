package com.ex.tupyo;

public class TupyoDTO {

	int id;
	String title;
	int agree;
	int disagree;
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
	public int getAgree() {
		return agree;
	}
	public void setAgree(int agree) {
		this.agree = agree;
	}
	public int getDisagree() {
		return disagree;
	}
	public void setDisagree(int disagree) {
		this.disagree = disagree;
	}
	public TupyoDTO(int _id, String _title, int _agree, int _disagree) {
		// TODO Auto-generated constructor stub
		this.id = _id;
		this.title = _title;
		this.agree = _agree;
		this.disagree = _disagree;
		
	}
	
	

}
