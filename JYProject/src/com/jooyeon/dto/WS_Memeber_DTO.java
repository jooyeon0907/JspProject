package com.jooyeon.dto;

public class WS_Memeber_DTO {
/*
  mysql> desc ws_member;
+----------+--------------+------+-----+-------------------+-------+
| Field    | Type         | Null | Key | Default           | Extra |
+----------+--------------+------+-----+-------------------+-------+
| ws_no    | int(11)      | NO   |     | NULL              |       |
| jyp_no   | int(11)      | NO   |     | NULL              |       |
| ws_Mdate | datetime     | NO   |     | CURRENT_TIMESTAMP |       |
| ws_Mip   | varchar(100) | NO   |     | NULL              |       |
+----------+--------------+------+-----+-------------------+-------+
4 rows in set (0.01 sec)
 
 */
	private int ws_no;
	private int jyp_no;
	private String ws_Mdate;
	private String ws_Mip;
	public int getWs_no() {
		return ws_no;
	}
	public void setWs_no(int ws_no) {
		this.ws_no = ws_no;
	}
	public int getJyp_no() {
		return jyp_no;
	}
	public void setJyp_no(int jyp_no) {
		this.jyp_no = jyp_no;
	}
	public String getWs_Mdate() {
		return ws_Mdate;
	}
	public void setWs_Mdate(String ws_Mdate) {
		this.ws_Mdate = ws_Mdate;
	}
	public String getWs_Mip() {
		return ws_Mip;
	}
	public void setWs_Mip(String ws_Mip) {
		this.ws_Mip = ws_Mip;
	}
	@Override
	public String toString() {
		return "WS_Memeber_DTO [ws_no=" + ws_no + ", jyp_no=" + jyp_no + ", ws_Mdate=" + ws_Mdate + ", ws_Mip=" + ws_Mip
				+ "]";
	}
	public WS_Memeber_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WS_Memeber_DTO(int ws_no, int jyp_no, String ws_Mdate, String ws_Mip) {
		super();
		this.ws_no = ws_no;
		this.jyp_no = jyp_no;
		this.ws_Mdate = ws_Mdate;
		this.ws_Mip = ws_Mip;
	}
	
	
	
	
	
}//end class
