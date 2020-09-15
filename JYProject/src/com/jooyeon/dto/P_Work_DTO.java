package com.jooyeon.dto;

import java.util.ArrayList;

public class P_Work_DTO {
/*

mysql> desc p_work;
+--------------+--------------+------+-----+-------------------+----------------+
| Field        | Type         | Null | Key | Default           | Extra          |
+--------------+--------------+------+-----+-------------------+----------------+
| work_no      | int(11)      | NO   | PRI | NULL              | auto_increment |
| ws_no        | int(11)      | NO   |     | NULL              |                |
| p_no         | int(11)      | NO   |     | NULL              |                |
| wl_no        | int(11)      | NO   |     | NULL              |                |
| jyp_no       | int(11)      | NO   |     | NULL              |                |
| work_content | text         | NO   |     | NULL              |                |
| work_start   | date         | YES  |     | NULL              |                |
| work_end     | date         | YES  |     | NULL              |                |
| work_finish  | int(11)      | NO   |     | 1                 |                |
| work_date    | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
| work_ip      | varchar(100) | NO   |     | NULL              |                |
| work_file    | varchar(200) | YES  |     | no.jpg            |                |
| work_com     | char(5)      | NO   |     | n                 |                |
| work_label   | varchar(50)  | NO   |     | #fdfdfd00         |                |
+--------------+--------------+------+-----+-------------------+----------------+
14 rows in set (0.00 sec)

 */
	
	private int work_no, ws_no,p_no , wl_no, jyp_no; 
	private String work_content, work_start , work_end; 
	private int work_finish;
	private String work_date, work_ip;
	private String work_file;
	private String work_label;
	/////////////
	private String work_alarm;
	private String p_name;
	private String wl_name;
	private String dday;
	private String start_dday;
	private String end_dday;
	private String work_com;
	
	///
	private String jyp_name;
	
	
	
	
	public String getWork_label() {
		return work_label;
	}
	public void setWork_label(String work_label) {
		this.work_label = work_label;
	}
	public String getJyp_name() {
		return jyp_name;
	}
	public void setJyp_name(String jyp_name) {
		this.jyp_name = jyp_name;
	}
	public String getP_name() {
		return p_name;
	}
	public String getWork_com() {
		return work_com;
	}
	public void setWork_com(String work_com) {
		this.work_com = work_com;
	}
	public String getDday() {
		return dday;
	}
	public void setDday(String dday) {
		this.dday = dday;
	}
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
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getWl_name() {
		return wl_name;
	}
	public void setWl_name(String wl_name) {
		this.wl_name = wl_name;
	}
	///
	
	
	
	
	public String getWork_alarm() {
		return work_alarm;
	}
	public void setWork_alarm(String work_alarm) {
		this.work_alarm = work_alarm;
	}
	public String getWork_file() {
		return work_file;
	}
	public void setWork_file(String work_file) {
		this.work_file = work_file;
	}
	public int getWork_no() {
		return work_no;
	}
	public void setWork_no(int work_no) {
		this.work_no = work_no;
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
	public int getJyp_no() {
		return jyp_no;
	}
	public void setJyp_no(int jyp_no) {
		this.jyp_no = jyp_no;
	}
	public String getWork_content() {
		return work_content;
	}
	public void setWork_content(String work_content) {
		this.work_content = work_content;
	}
	public String getWork_start() {
		return work_start;
	}
	public void setWork_start(String work_start) {
		this.work_start = work_start;
	}
	public String getWork_end() {
		return work_end;
	}
	public void setWork_end(String work_end) {
		this.work_end = work_end;
	}

	public int getWork_finish() {
		return work_finish;
	}
	public void setWork_finish(int work_finish) {
		this.work_finish = work_finish;
	}
	public String getWork_date() {
		return work_date;
	}
	public void setWork_date(String work_date) {
		this.work_date = work_date;
	}
	public String getWork_ip() {
		return work_ip;
	}
	public void setWork_ip(String work_ip) {
		this.work_ip = work_ip;
	}
	@Override
	public String toString() {
		return "P_Work_DTO [work_no=" + work_no + ", ws_no=" + ws_no + ", p_no=" + p_no + ", wl_no=" + wl_no
				+ ", jyp_no=" + jyp_no + ", work_content=" + work_content + ", work_start=" + work_start + ", work_end="
				+ work_end + ", work_finish=" + work_finish + ", work_date=" + work_date + ", work_ip=" + work_ip
				+ ", work_file=" + work_file + ", work_alarm=" + work_alarm + ", p_name=" + p_name + ", wl_name="
				+ wl_name + ", dday=" + dday + ", start_dday=" + start_dday + ", end_dday=" + end_dday + ", work_com="
				+ work_com + ", jyp_name=" + jyp_name + "]";
	}
	public P_Work_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public P_Work_DTO(int work_no, int ws_no, int p_no, int wl_no, int jyp_no, String work_content, String work_start,
			String work_end, int work_finish, String work_date, String work_ip, String work_file,String work_com, String work_label ) {
		super();
		this.work_no = work_no;
		this.ws_no = ws_no;
		this.p_no = p_no;
		this.wl_no = wl_no;
		this.jyp_no = jyp_no;
		this.work_content = work_content;
		this.work_start = work_start;
		this.work_end = work_end;
		this.work_finish = work_finish;
		this.work_date = work_date;
		this.work_ip = work_ip;
		this.work_file = work_file;
		this.work_com = work_com;
		this.work_label = work_label;
	}
	
	
	
//ArrayList<P_Work_DTO> work_listAll(int p_no){	
	public P_Work_DTO(int work_no, int ws_no, int p_no, int wl_no, int jyp_no, String work_content, String work_start,
			String work_end, int work_finish, String work_date, String work_ip, String work_file,String work_com, String jyp_name, String work_label ) {
		super();
		this.work_no = work_no;
		this.ws_no = ws_no;
		this.p_no = p_no;
		this.wl_no = wl_no;
		this.jyp_no = jyp_no;
		this.work_content = work_content;
		this.work_start = work_start;
		this.work_end = work_end;
		this.work_finish = work_finish;
		this.work_date = work_date;
		this.work_ip = work_ip;
		this.work_file = work_file;
		this.work_com = work_com;
		this.jyp_name = jyp_name;
		this.work_label = work_label;
	}
	
	
	
	//work_detail(P_Work_DTO dto_w,PW_Member_DTO dto_pw ) {
	public P_Work_DTO(int work_no, int ws_no, int p_no, int wl_no, int jyp_no, String work_content, String work_start,
			String work_end, int work_finish, String work_date, String work_ip, String work_file, String work_alarm, String work_com, String jyp_name, String work_label ) {
		super();
		this.work_no = work_no;
		this.ws_no = ws_no;
		this.p_no = p_no;
		this.wl_no = wl_no;
		this.jyp_no = jyp_no;
		this.work_content = work_content;
		this.work_start = work_start;
		this.work_end = work_end;
		this.work_finish = work_finish;
		this.work_date = work_date;
		this.work_ip = work_ip;
		this.work_file = work_file;
		this.work_alarm = work_alarm;
		this.work_com = work_com;
		this.jyp_name = jyp_name;
		this.work_label = work_label;
	}
	
	
	//public ArrayList<P_Work_DTO> work_listAll2(int p_no){
	public P_Work_DTO(int work_no, int ws_no, int p_no, int wl_no, int jyp_no, String work_content, String work_start,
			String work_end, int work_finish, String work_date, String work_ip, String work_file, String work_com, String jyp_name,
			String wl_name, int j, String work_label ) {
		super();
		this.work_no = work_no;
		this.ws_no = ws_no;
		this.p_no = p_no;
		this.wl_no = wl_no;
		this.jyp_no = jyp_no;
		this.work_content = work_content;
		this.work_start = work_start;
		this.work_end = work_end;
		this.work_finish = work_finish;
		this.work_date = work_date;
		this.work_ip = work_ip;
		this.work_file = work_file;
		this.work_com = work_com;
		this.jyp_name = jyp_name;
		this.wl_name = wl_name;
		this.work_label = work_label;
	}
	
	
	
	public P_Work_DTO(int work_no, int ws_no, int p_no, int wl_no, int jyp_no, String work_content, 
			int work_finish, String work_date, String work_ip, String work_file, String work_alarm,
			String p_name, String wl_name, String dday, String work_label ) {
		super();
		this.work_no = work_no;
		this.ws_no = ws_no;
		this.p_no = p_no;
		this.wl_no = wl_no;
		this.jyp_no = jyp_no;
		this.work_content = work_content;
		this.work_finish = work_finish;
		this.work_date = work_date;
		this.work_ip = work_ip;
		this.work_file = work_file;
		this.work_alarm = work_alarm;
		this.p_name = p_name;
		this.wl_name = wl_name;
		this.dday = dday;
		this.work_label = work_label;
	}
	
	
	//업무의 시작일/마감일
	
	public P_Work_DTO(int work_no, int ws_no, int p_no, int wl_no, int jyp_no, String work_content, String work_start,
			String work_end, int work_finish, String work_date, String work_ip, String work_file, 
			String p_name, String wl_name, String start_dday, String end_dday, String work_label ) {
		super();
		this.work_no = work_no;
		this.ws_no = ws_no;
		this.p_no = p_no;
		this.wl_no = wl_no;
		this.jyp_no = jyp_no;
		this.work_content = work_content;
		this.work_start = work_start;
		this.work_end = work_end;
		this.work_finish = work_finish;
		this.work_date = work_date;
		this.work_ip = work_ip;
		this.work_file = work_file;
		this.p_name = p_name;
		this.wl_name = wl_name;
		this.start_dday = start_dday;
		this.end_dday = end_dday;
		this.work_label = work_label;
	}

	
	
	/////////////
	//update dao 
	
	

	

	
	
}//end class
