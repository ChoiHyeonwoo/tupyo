package com.ex.dto;

public class MemberLogDTO {

	int pk_lid;
	String mlogId;
	String log_date;
	String log_content;
	String ip_address;
	
	public int getPk_lid() {
		return pk_lid;
	}

	public void setPk_lid(int pk_lid) {
		this.pk_lid = pk_lid;
	}

	public String getMlogId() {
		return mlogId;
	}

	public void setMlogId(String mlogId) {
		this.mlogId = mlogId;
	}

	public String getLog_date() {
		return log_date;
	}

	public void setLog_date(String log_date) {
		this.log_date = log_date;
	}

	public String getLog_content() {
		return log_content;
	}

	public void setLog_content(String log_content) {
		this.log_content = log_content;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public MemberLogDTO(int _pk_lid, String _mlogid, String _log_date, String _log_content, String _ip_address) {
		// TODO Auto-generated constructor stub
		this.pk_lid = _pk_lid;
		this.mlogId = _mlogid;
		this.log_date = _log_date;
		this.log_content = _log_content;
		this.ip_address= _ip_address;
	}

}
