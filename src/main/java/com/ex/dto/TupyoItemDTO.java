package com.ex.dto;

public class TupyoItemDTO {
	
	int pk_id;
	int t_id;
	String t_item_content;
	int t_item_selected;
	String t_title;
	
	public String getT_title() {
		return t_title;
	}

	public void setT_title(String t_title) {
		this.t_title = t_title;
	}


	public int getPk_id() {
		return pk_id;
	}


	public void setPk_id(int pk_id) {
		this.pk_id = pk_id;
	}


	public int getT_id() {
		return t_id;
	}


	public void setT_id(int t_id) {
		this.t_id = t_id;
	}


	public String getT_item_content() {
		return t_item_content;
	}


	public void setT_item_content(String t_item_content) {
		this.t_item_content = t_item_content;
	}


	public int getT_item_selected() {
		return t_item_selected;
	}


	public void setT_item_selected(int t_item_selected) {
		this.t_item_selected = t_item_selected;
	}


	public TupyoItemDTO(int _pk_id, int _t_id, String _t_item_content, int _t_item_selected, String _t_title) {
		// TODO Auto-generated constructor stub
		this.pk_id = _pk_id;
		this.t_id = _t_id;
		this.t_item_content = _t_item_content;
		this.t_item_selected = _t_item_selected;
		this.t_title = _t_title;	
	}

}
