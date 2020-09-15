package com.jooyeon.dto;

public class WS_Invitation_DTO {
/*
mysql> desc ws_invitation;
+-------------+--------------+------+-----+-------------------+----------------+
| Field       | Type         | Null | Key | Default           | Extra          |
+-------------+--------------+------+-----+-------------------+----------------+
| invi_no     | int(11)      | NO   | PRI | NULL              | auto_increment |
| send_email  | varchar(100) | NO   |     | NULL              |                |
| send_ip     | varchar(100) | NO   |     | NULL              |                |
| get_email   | varchar(100) | NO   |     | NULL              |                |
| get_ip      | varchar(100) | NO   |     | NULL              |                |
| ws_no       | int(11)      | NO   |     | NULL              |                |
| ws_name     | varchar(100) | NO   |     | NULL              |                |
| acc_date    | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
| ass_satatus | char(5)      | NO   |     | n                 |                |
+-------------+--------------+------+-----+-------------------+----------------+
9 rows in set (0.01 sec)

 * */
	private int invi_no;
	private String send_email;
	private String send_ip;
	private String  get_email;
	private String get_ip;
	private int  ws_no;
	private String ws_name;
	private String acc_date;
	private String ass_status;
	

	
	
	public int getInvi_no() {
		return invi_no;
	}
	public void setInvi_no(int invi_no) {
		this.invi_no = invi_no;
	}
	public String getSend_email() {
		return send_email;
	}
	public void setSend_email(String send_email) {
		this.send_email = send_email;
	}
	public String getSend_ip() {
		return send_ip;
	}
	public void setSend_ip(String send_ip) {
		this.send_ip = send_ip;
	}
	public String getGet_email() {
		return get_email;
	}
	public void setGet_email(String get_email) {
		this.get_email = get_email;
	}
	public String getGet_ip() {
		return get_ip;
	}
	public void setGet_ip(String get_ip) {
		this.get_ip = get_ip;
	}
	public int getWs_no() {
		return ws_no;
	}
	public void setWs_no(int ws_no) {
		this.ws_no = ws_no;
	}
	public String getWs_name() {
		return ws_name;
	}
	public void setWs_name(String ws_name) {
		this.ws_name = ws_name;
	}
	public String getAcc_date() {
		return acc_date;
	}
	public void setAcc_date(String acc_date) {
		this.acc_date = acc_date;
	}
	public String getAss_status() {
		return ass_status;
	}
	public void setAss_status(String ass_status) {
		this.ass_status = ass_status;
	}
	@Override
	public String toString() {
		return "WS_Invitation_DTO [invi_no=" + invi_no + ", send_email=" + send_email + ", send_ip=" + send_ip
				+ ", get_email=" + get_email + ", get_ip=" + get_ip + ", ws_no=" + ws_no + ", ws_name=" + ws_name
				+ ", acc_date=" + acc_date + ", ass_satatus=" + ass_status + "]";
	}
	public WS_Invitation_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WS_Invitation_DTO(int invi_no, String send_email, String send_ip, String get_email, String get_ip, int ws_no,
			String ws_name, String acc_date, String ass_status) {
		super();
		this.invi_no = invi_no;
		this.send_email = send_email;
		this.send_ip = send_ip;
		this.get_email = get_email;
		this.get_ip = get_ip;
		this.ws_no = ws_no;
		this.ws_name = ws_name;
		this.acc_date = acc_date;
		this.ass_status = ass_status;
	}

	
	
	
	
	
	
	
} //end cladd
