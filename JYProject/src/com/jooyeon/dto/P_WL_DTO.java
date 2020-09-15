package com.jooyeon.dto;

public class P_WL_DTO {
/*
mysql> desc p_worklist;
+---------+--------------+------+-----+-------------------+----------------+
| Field   | Type         | Null | Key | Default           | Extra          |
+---------+--------------+------+-----+-------------------+----------------+
| wl_no   | int(11)      | NO   | PRI | NULL              | auto_increment |
| ws_no   | int(11)      | NO   |     | NULL              |                |
| p_no    | int(11)      | NO   |     | NULL              |                |
| jyp_no  | int(11)      | NO   |     | NULL              |                |
| wl_name | varchar(100) | NO   |     | NULL              |                |
| wl_date | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
| wl_ip   | varchar(100) | NO   |     | NULL              |                |
+---------+--------------+------+-----+-------------------+----------------+
7 rows in set (0.01 sec)

 */
	
	private int wl_no;
	private int ws_no;
	private int p_no;
	private int jyp_no;
	private String wl_name;
	private String wl_date;
	private String wl_ip;
	
	
	
	public int getJyp_no() {
		return jyp_no;
	}
	public void setJyp_no(int jyp_no) {
		this.jyp_no = jyp_no;
	}
	public int getWl_no() {
		return wl_no;
	}
	public void setWl_no(int wl_no) {
		this.wl_no = wl_no;
	}
	public int getWs_no() {
		return ws_no;
	}
	public void setWs_no(int ws_no) {
		this.ws_no = ws_no;
	}
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public String getWl_name() {
		return wl_name;
	}
	public void setWl_name(String wl_name) {
		this.wl_name = wl_name;
	}
	public String getWl_date() {
		return wl_date;
	}
	public void setWl_date(String wl_date) {
		this.wl_date = wl_date;
	}
	public String getWl_ip() {
		return wl_ip;
	}
	public void setWl_ip(String wl_ip) {
		this.wl_ip = wl_ip;
	}
	@Override
	public String toString() {
		return "P_WL_DTO [wl_no=" + wl_no + ", ws_no=" + ws_no + ", p_no=" + p_no + ", jyp_no=" + jyp_no + ", wl_name="
				+ wl_name + ", wl_date=" + wl_date + ", wl_ip=" + wl_ip + "]";
	}
	public P_WL_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public P_WL_DTO(int wl_no, int ws_no, int p_no, String wl_name, String wl_date, String wl_ip,int jyp_no) {
		super();
		this.wl_no = wl_no;
		this.ws_no = ws_no;
		this.p_no = p_no;
		this.wl_name = wl_name;
		this.wl_date = wl_date;
		this.wl_ip = wl_ip;
		this.jyp_no = jyp_no;
	}
	
	
	
	
	
	
} //end class
