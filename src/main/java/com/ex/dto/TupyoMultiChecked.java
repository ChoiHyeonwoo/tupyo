package com.ex.dto;

public class TupyoMultiChecked {

	String t_item_selected;
	String t_content;

	public String getT_item_selected() {
		return t_item_selected;
	}

	public void setT_item_selected(String t_item_selected) {
		this.t_item_selected = t_item_selected;
	}

	public String getT_content() {
		return t_content;
	}

	public void setT_content(String t_content) {
		this.t_content = t_content;
	}

	public TupyoMultiChecked(String _t_item_selected, String _t_content) {
		this.t_item_selected = _t_item_selected;
		this.t_content = _t_content;
	}

}
