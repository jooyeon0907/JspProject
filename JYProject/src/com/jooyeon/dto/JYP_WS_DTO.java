package com.jooyeon.dto;

public class JYP_WS_DTO {
	/*
	 mysql> desc jyp_workspace;
+------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| ws_no      | int(11)      | NO   | PRI | NULL    | auto_increment |
| ws_name    | varchar(100) | NO   |     | NULL    |                |
| ws_admin   | int(11)      | NO   |     | NULL    |                |
| ws_ip      | varchar(100) | NO   |     | NULL    |                |
| ws_date    | char(1)      | NO   |     | n       |                |
| ws_del     | char(5)      | NO   |     | n       |                |
| ws_delDday | datetime     | YES  |     | NULL    |                |
+------------+--------------+------+-----+---------+----------------+
7 rows in set (0.01 sec)
	 */
	
	private int ws_no;
	private String ws_name;
//	private int ws_admin;
	private int jyp_no;
	private String ws_ip;
	private String ws_date;
	private String ws_del;
	private String ws_delDday;
	private String address;
	
	
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getWs_no() { return ws_no; } 
	public void setWs_no(int ws_no) { this.ws_no = ws_no; }
	
	public String getWs_name() { return ws_name; }
	public void setWs_name(String ws_name) { this.ws_name = ws_name; } 
	
	public int getJyp_no() { return jyp_no; }
	public void setJyp_no(int jyp_no) { this.jyp_no = jyp_no; }
	
	public String getWs_ip() { return ws_ip; }
	public void setWs_ip(String ws_ip) { this.ws_ip = ws_ip; }
	
	public String getWs_date() { return ws_date; }
	public void setWs_date(String ws_date) { this.ws_date = ws_date; } 
	
	public String getWs_del() { return ws_del; }
	public void setWs_del(String ws_del) { this.ws_del = ws_del; }
	
	public String getWs_delDday() { return ws_delDday; }
	public void setWs_delDday(String ws_delDday) { this.ws_delDday = ws_delDday; }
	
	@Override
	public String toString() {
		return "JYP_WS_DTO [ws_no=" + ws_no + ", ws_name=" + ws_name + ", jyp_no=" + jyp_no + ", ws_ip=" + ws_ip
				+ ", ws_date=" + ws_date + ", ws_del=" + ws_del + ", ws_delDday=" + ws_delDday + ", address=" + address
				+ "]";
	}
	public JYP_WS_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JYP_WS_DTO(int ws_no, String ws_name, int jyp_no, String ws_ip, String ws_date, String ws_del,
			String ws_delDday) {
		super();
		this.ws_no = ws_no;
		this.ws_name = ws_name;
		this.jyp_no = jyp_no;
		this.ws_ip = ws_ip;
		this.ws_date = ws_date;
		this.ws_del = ws_del;
		this.ws_delDday = ws_delDday;
	}
	
	
	//주소추가 
	public JYP_WS_DTO(int ws_no, String ws_name, int jyp_no, String ws_ip, String ws_date, String ws_del,
			String ws_delDday, String address) {
		super();
		this.ws_no = ws_no;
		this.ws_name = ws_name;
		this.jyp_no = jyp_no;
		this.ws_ip = ws_ip;
		this.ws_date = ws_date;
		this.ws_del = ws_del;
		this.ws_delDday = ws_delDday;
		this.address = address;
	}

	
	
	
	
	
	
}//end class
