package com.jooyeon.dto;

public class PW_Member_DTO {
	/*
mysql> desc pw_member;
+------------+--------------+------+-----+-------------------+-------+
| Field      | Type         | Null | Key | Default           | Extra |
+------------+--------------+------+-----+-------------------+-------+
| ws_no      | int(11)      | NO   |     | NULL              |       |
| p_no       | int(11)      | NO   |     | NULL              |       |
| wl_no      | int(11)      | NO   |     | NULL              |       |
| work_no    | int(11)      | NO   |     | NULL              |       |
| jyp_no     | int(11)      | NO   |     | NULL              |       |
| jyp_ip     | varchar(100) | NO   |     | NULL              |       |
| work_alarm | datetime     | YES  |     | NULL              |       |
| pw_Mdate   | datetime     | NO   |     | CURRENT_TIMESTAMP |       |
+------------+--------------+------+-----+-------------------+-------+
8 rows in set (0.01 sec)

	 */
	private int ws_no;
	private int p_no;
	private int wl_no;
	private int work_no;
	private int jyp_no;
	private String jyp_ip;
	private String work_alarm;
	private String pw_Mdate;
	private String jyp_name;
	private String work_check;
	
	
	
	public String getWork_check() {
		return work_check;
	}
	public void setWork_check(String work_check) {
		this.work_check = work_check;
	}
	public String getJyp_name() {
		return jyp_name;
	}
	public void setJyp_name(String jyp_name) {
		this.jyp_name = jyp_name;
	}
	public String getPw_Mdate() {
		return pw_Mdate;
	}
	public void setPw_Mdate(String pw_Mdate) {
		this.pw_Mdate = pw_Mdate;
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
	public int getWl_no() {
		return wl_no;
	}
	public void setWl_no(int wl_no) {
		this.wl_no = wl_no;
	}
	public int getWork_no() {
		return work_no;
	}
	public void setWork_no(int work_no) {
		this.work_no = work_no;
	}
	public int getJyp_no() {
		return jyp_no;
	}
	public void setJyp_no(int jyp_no) {
		this.jyp_no = jyp_no;
	}
	public String getJyp_ip() {
		return jyp_ip;
	}
	public void setJyp_ip(String jyp_ip) {
		this.jyp_ip = jyp_ip;
	}
	public String getWork_alarm() {
		return work_alarm;
	}
	public void setWork_alarm(String work_alarm) {
		this.work_alarm = work_alarm;
	}
	
	@Override
	public String toString() {
		return "PW_Member_DTO [ws_no=" + ws_no + ", p_no=" + p_no + ", wl_no=" + wl_no + ", work_no=" + work_no
				+ ", jyp_no=" + jyp_no + ", jyp_ip=" + jyp_ip + ", work_alarm=" + work_alarm + ", pw_Mdate=" + pw_Mdate
				+ ", jyp_name=" + jyp_name + ", work_check=" + work_check + "]";
	}
	
	public PW_Member_DTO() { super(); }

	public PW_Member_DTO(int ws_no, int p_no, int wl_no, int work_no, int jyp_no, String jyp_ip, String work_alarm,
			String pw_Mdate, String jyp_name) {
		super();
		this.ws_no = ws_no;
		this.p_no = p_no;
		this.wl_no = wl_no;
		this.work_no = work_no;
		this.jyp_no = jyp_no;
		this.jyp_ip = jyp_ip;
		this.work_alarm = work_alarm;
		this.pw_Mdate = pw_Mdate;
		this.jyp_name = jyp_name;
	}

	public PW_Member_DTO(int ws_no, int p_no, int wl_no, int work_no, int jyp_no, String jyp_ip, String work_alarm,
			String pw_Mdate, String jyp_name, String work_check) {
		super();
		this.ws_no = ws_no;
		this.p_no = p_no;
		this.wl_no = wl_no;
		this.work_no = work_no;
		this.jyp_no = jyp_no;
		this.jyp_ip = jyp_ip;
		this.work_alarm = work_alarm;
		this.pw_Mdate = pw_Mdate;
		this.jyp_name = jyp_name;
		this.work_check = work_check;
	}


	
	
	
	
	
	
}//end class
