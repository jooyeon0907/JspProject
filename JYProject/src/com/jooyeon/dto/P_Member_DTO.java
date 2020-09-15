package com.jooyeon.dto;

public class P_Member_DTO {
/*
 
mysql> desc p_member;
+----------+--------------+------+-----+-------------------+-------+
| Field    | Type         | Null | Key | Default           | Extra |
+----------+--------------+------+-----+-------------------+-------+
| ws_no    | int(11)      | NO   |     | NULL              |       |
| p_no     | int(11)      | NO   |     | NULL              |       |
| jyp_no   | int(11)      | NO   |     | NULL              |       |
| p_access | int(11)      | NO   |     | 2                 |       |
| p_Mdate  | datetime     | NO   |     | CURRENT_TIMESTAMP |       |
| p_Mip    | varchar(100) | NO   |     | NULL              |       |
+----------+--------------+------+-----+-------------------+-------+
6 rows in set (0.00 sec)
 */
	
	private int ws_no;
	private int p_no;
	private int jyp_no;
	private int p_access;
	private String p_Mdate;
	private String p_Mip;
	private String jyp_name;
	
	
	
	public String getJyp_name() {
		return jyp_name;
	}
	public void setJyp_name(String jyp_name) {
		this.jyp_name = jyp_name;
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
	public int getJyp_no() {
		return jyp_no;
	}
	public void setJyp_no(int jyp_no) {
		this.jyp_no = jyp_no;
	}
	public int getP_access() {
		return p_access;
	}
	public void setP_access(int p_access) {
		this.p_access = p_access;
	}
	public String getP_Mdate() {
		return p_Mdate;
	}
	public void setP_Mdate(String p_Mdate) {
		this.p_Mdate = p_Mdate;
	}
	public String getP_Mip() {
		return p_Mip;
	}
	public void setP_Mip(String p_Mip) {
		this.p_Mip = p_Mip;
	}
	@Override
	public String toString() {
		return "P_Member_DTO [ws_no=" + ws_no + ", p_no=" + p_no + ", jyp_no=" + jyp_no + ", p_access=" + p_access
				+ ", p_Mdate=" + p_Mdate + ", p_Mip=" + p_Mip + "]";
	}
	public P_Member_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public P_Member_DTO(int ws_no, int p_no, int jyp_no, int p_access, String p_Mdate, String p_Mip) {
		super();
		this.ws_no = ws_no;
		this.p_no = p_no;
		this.jyp_no = jyp_no;
		this.p_access = p_access;
		this.p_Mdate = p_Mdate;
		this.p_Mip = p_Mip;
	}
	public P_Member_DTO(int ws_no, int p_no, int jyp_no, int p_access, String p_Mdate, String p_Mip, String jyp_name) {
		super();
		this.ws_no = ws_no;
		this.p_no = p_no;
		this.jyp_no = jyp_no;
		this.p_access = p_access;
		this.p_Mdate = p_Mdate;
		this.p_Mip = p_Mip;
		this.jyp_name = jyp_name;
	}
	
	
	
	
	
	
} //end class
