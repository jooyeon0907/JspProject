package com.jooyeon.dto;

public class JYP_Project_DTO {
/*
 mysql> desc jyp_project;
+------------+--------------+------+-----+-------------------+----------------+
| Field      | Type         | Null | Key | Default           | Extra          |
+------------+--------------+------+-----+-------------------+----------------+
| p_no       | int(11)      | NO   | PRI | NULL              | auto_increment |
| ws_no      | int(11)      | NO   |     | NULL              |                |
| p_name     | varchar(50)  | NO   |     | NULL              |                |
| p_info     | text         | YES  |     | NULL              |                |
| p_status   | int(11)      | NO   |     | 1                 |                |
| p_start    | datetime     | YES  |     | NULL              |                |
| p_end      | datetime     | YES  |     | NULL              |                |
| p_complete | datetime     | YES  |     | NULL              |                |
| p_public   | char(5)      | NO   |     | NULL              |                |
| p_date     | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
| p_ip       | varchar(100) | NO   |     | NULL              |                |
+------------+--------------+------+-----+-------------------+----------------+
11 rows in set (0.01 sec)

 */
	private int p_no, ws_no;
	private String p_name, p_info;
	private int p_status;
	private String p_start,p_end , p_complete;
	private String p_public;
	private String p_date,p_ip;
	private int jyp_no;
	///////////////////////
	private String start_dday;
	private String end_dday;
	
	
	public String getStart_dday() {
		return start_dday;
	}
	public void setStart_dday(String start_dday) {
		this.start_dday = start_dday;
	}
	public String getEnd_dday() {
		return end_dday;
	}
	public void setEnd_dday(String end_dday) {
		this.end_dday = end_dday;
	}
	////
	public int getJyp_no() { return jyp_no; }
	public void setJyp_no(int jyp_no) { this.jyp_no = jyp_no; }
	
	public int getP_no() { return p_no; } 
	public void setP_no(int p_no) { this.p_no = p_no; } 
	
	public int getWs_no() { return ws_no; } 
	public void setWs_no(int ws_no) { this.ws_no = ws_no; } 
	
	public String getP_name() { return p_name; }
	public void setP_name(String p_name) { this.p_name = p_name; } 
	
	public String getP_info() { return p_info; }
	public void setP_info(String p_info) { this.p_info = p_info; } 
	
	public int getP_status() { return p_status; } 
	public void setP_status(int p_status) { this.p_status = p_status; }
	
	public String getP_start() { return p_start; }
	public void setP_start(String p_start) { this.p_start = p_start; }
	
	public String getP_end() { return p_end; } 
	public void setP_end(String p_end) { this.p_end = p_end; }
	
	public String getP_complete() { return p_complete; }
	public void setP_complete(String p_complete) { this.p_complete = p_complete; }
	
	public String getP_public() { return p_public; }
	public void setP_public(String p_public) { this.p_public = p_public; }
	
	public String getP_date() { return p_date; }
	public void setP_date(String p_date) { this.p_date = p_date; } 
	
	public String getP_ip() { return p_ip; }
	public void setP_ip(String p_ip) { this.p_ip = p_ip; }
	
	@Override
	public String toString() {
		return "JYP_Project_DTO [p_no=" + p_no + ", ws_no=" + ws_no + ", p_name=" + p_name + ", p_info=" + p_info
				+ ", p_status=" + p_status + ", p_start=" + p_start + ", p_end=" + p_end + ", p_complete=" + p_complete
				+ ", p_public=" + p_public + ", p_date=" + p_date + ", p_ip=" + p_ip + ", jyp_no=" + jyp_no
				+ ", start_dday=" + start_dday + ", end_dday=" + end_dday + "]";
	}
	public JYP_Project_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JYP_Project_DTO(int p_no, int ws_no, String p_name, String p_info, int p_status, String p_start,
			String p_end, String p_complete, String p_public, String p_date, String p_ip,int jyp_no) {
		super();
		this.p_no = p_no;
		this.ws_no = ws_no;
		this.p_name = p_name;
		this.p_info = p_info;
		this.p_status = p_status;
		this.p_start = p_start;
		this.p_end = p_end;
		this.p_complete = p_complete;
		this.p_public = p_public;
		this.p_date = p_date;
		this.p_ip = p_ip;
		this.jyp_no = jyp_no;
	}
	
	
	
	// 프로젝트 시작일/마감일 dao에 사용
	public JYP_Project_DTO(int p_no, int ws_no, String p_name, String p_info, int p_status, String p_start,
			String p_end, String p_complete, String p_public, String p_date, String p_ip, int jyp_no, String start_dday,
			String end_dday) {
		super();
		this.p_no = p_no;
		this.ws_no = ws_no;
		this.p_name = p_name;
		this.p_info = p_info;
		this.p_status = p_status;
		this.p_start = p_start;
		this.p_end = p_end;
		this.p_complete = p_complete;
		this.p_public = p_public;
		this.p_date = p_date;
		this.p_ip = p_ip;
		this.jyp_no = jyp_no;
		this.start_dday = start_dday;
		this.end_dday = end_dday;
	}
	
	
	
	
	
	
	
	
	
	
}//end class
