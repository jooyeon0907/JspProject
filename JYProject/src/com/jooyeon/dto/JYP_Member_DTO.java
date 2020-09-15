package com.jooyeon.dto;

import java.sql.Date;

public class JYP_Member_DTO {
/**
mysql> desc jyp_member;
+-------------+--------------+------+-----+-------------------+----------------+
| Field       | Type         | Null | Key | Default           | Extra          |
+-------------+--------------+------+-----+-------------------+----------------+
| jyp_no      | int(11)      | NO   | PRI | NULL              | auto_increment |
| jyp_email   | varchar(200) | NO   |     | NULL              |                |
| jyp_name    | varchar(100) | NO   |     | NULL              |                |
| jyp_pass    | varchar(50)  | NO   |     | NULL              |                |
| jyp_tel     | varchar(100) | NO   |     | NULL              |                |
| gender      | char(5)      | NO   |     | NULL              |                |
| birth       | date         | YES  |     | NULL              |                |
| jyp_date    | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
| jyp_ip      | varchar(100) | NO   |     | NULL              |                |
| dept        | varchar(50)  | YES  |     | -                 |                |
| position    | varchar(50)  | YES  |     | -                 |                |
| jyp_profile | varchar(200) | NO   |     | noImg.png         |                |
+-------------+--------------+------+-----+-------------------+----------------+
12 rows in set (0.01 sec)
 */
	private int jyp_no;
	private String jyp_email, jyp_name, jyp_pass, jyp_tel , gender,birth, jyp_date, jyp_ip,dept,position;
	private String ws_Mdate;
	private String jyp_profile;
	
	
	public String getJyp_profile() {
		return jyp_profile;
	}
	public void setJyp_profile(String jyp_profile) {
		this.jyp_profile = jyp_profile;
	}
	public String getWs_Mdate() {
		return ws_Mdate;
	}
	public void setWs_Mdate(String ws_Mdate) {
		this.ws_Mdate = ws_Mdate;
	}
	public int getJyp_no() { return jyp_no; } 
	public void setJyp_no(int jyp_no) { this.jyp_no = jyp_no; }
	
	public String getJyp_email() { return jyp_email; }
	public void setJyp_email(String jyp_email) { this.jyp_email = jyp_email; }
	
	public String getJyp_name() { return jyp_name; } 
	public void setJyp_name(String jyp_name) { this.jyp_name = jyp_name; } 
	
	public String getJyp_pass() { return jyp_pass; }
	public void setJyp_pass(String jyp_pass) { this.jyp_pass = jyp_pass; }
	
	public String getJyp_tel() { return jyp_tel; }
	public void setJyp_tel(String jyp_tel) { this.jyp_tel = jyp_tel; }
	
	public String getGender() { return gender; }
	public void setGender(String gender) { this.gender = gender; } 
	
	public String getBirth() { return birth; } 
	public void setBirth(String birth) { this.birth = birth; }
	
	public String getJyp_date() { return jyp_date; }
	public void setJyp_date(String jyp_date) { this.jyp_date = jyp_date; }
	
	public String getJyp_ip() { return jyp_ip; } 
	public void setJyp_ip(String jyp_ip) { this.jyp_ip = jyp_ip; } 

	public String getDept() { return dept; } 
	public void setDept(String dept) { this.dept = dept; }
	
	public String getPosition() { return position; } 
	public void setPosition(String position) { this.position = position; }

	
	
	@Override
	public String toString() {
		return "JYP_Member_DTO [jyp_no=" + jyp_no + ", jyp_email=" + jyp_email + ", jyp_name=" + jyp_name
				+ ", jyp_pass=" + jyp_pass + ", jyp_tel=" + jyp_tel + ", gender=" + gender + ", birth=" + birth
				+ ", jyp_date=" + jyp_date + ", jyp_ip=" + jyp_ip + ", dept=" + dept + ", position=" + position
				+ ", ws_Mdate=" + ws_Mdate + ", jyp_profile=" + jyp_profile + "]";
	}
	public JYP_Member_DTO() { super(); }


	public JYP_Member_DTO(int jyp_no, String jyp_email, String jyp_name, String jyp_pass, String jyp_tel, String gender,
			String birth, String jyp_date, String jyp_ip, String dept, String position, String jyp_profile) {
		super();
		this.jyp_no = jyp_no;
		this.jyp_email = jyp_email;
		this.jyp_name = jyp_name;
		this.jyp_pass = jyp_pass;
		this.jyp_tel = jyp_tel;
		this.gender = gender;
		this.birth = birth;
		this.jyp_date = jyp_date;
		this.jyp_ip = jyp_ip;
		this.dept = dept;
		this.position = position;
		this.jyp_profile = jyp_profile;
	}
	
	public JYP_Member_DTO(int jyp_no, String jyp_email, String jyp_name, String jyp_pass, String jyp_tel, String gender,
			String birth, String jyp_date, String jyp_ip, String dept, String position, String ws_Mdate,
			String jyp_profile) {
		super();
		this.jyp_no = jyp_no;
		this.jyp_email = jyp_email;
		this.jyp_name = jyp_name;
		this.jyp_pass = jyp_pass;
		this.jyp_tel = jyp_tel;
		this.gender = gender;
		this.birth = birth;
		this.jyp_date = jyp_date;
		this.jyp_ip = jyp_ip;
		this.dept = dept;
		this.position = position;
		this.ws_Mdate = ws_Mdate;
		this.jyp_profile = jyp_profile;
	}

	
	
	
} //end class
