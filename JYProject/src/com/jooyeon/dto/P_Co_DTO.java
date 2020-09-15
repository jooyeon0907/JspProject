package com.jooyeon.dto;

public class P_Co_DTO {
	/*
mysql> desc p_comments;
+-----------+--------------+------+-----+-------------------+----------------+
| Field     | Type         | Null | Key | Default           | Extra          |
+-----------+--------------+------+-----+-------------------+----------------+
| co_no     | int(11)      | NO   | PRI | NULL              | auto_increment |
| ws_no     | int(11)      | NO   |     | NULL              |                |
| p_no      | int(11)      | NO   |     | NULL              |                |
| wl_no     | int(11)      | NO   |     | NULL              |                |
| work_no   | int(11)      | NO   |     | NULL              |                |
| jyp_no    | int(11)      | NO   |     | NULL              |                |
| comment   | varchar(100) | NO   |     | NULL              |                |
| co_date   | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
| co_ip     | varchar(100) | NO   |     | NULL              |                |
| co_group  | int(11)      | YES  |     | 0                 |                |
| co_step   | int(11)      | YES  |     | 0                 |                |
| co_indent | int(11)      | YES  |     | 0                 |                |
+-----------+--------------+------+-----+-------------------+----------------+
12 rows in set (0.00 sec)
	 */
		
		private int co_no;
		private int ws_no;
		private int p_no;
		private int wl_no;
		private int work_no;
		private int jyp_no;
		private String comment;
		private String co_date;
		private String co_ip;
		private int co_group;
		private int co_step;
		private int co_indent;
		/////
		private String jyp_name;
		private String jyp_profile;
		
	
		
		
		public String getJyp_profile() {
			return jyp_profile;
		}
		public void setJyp_profile(String jyp_profile) {
			this.jyp_profile = jyp_profile;
		}
		public String getJyp_name() {
			return jyp_name;
		}
		public void setJyp_name(String jyp_name) {
			this.jyp_name = jyp_name;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public int getCo_no() {
			return co_no;
		}
		public void setCo_no(int co_no) {
			this.co_no = co_no;
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
		public String getCo_date() {
			return co_date;
		}
		public void setCo_date(String co_date) {
			this.co_date = co_date;
		}
		public String getCo_ip() {
			return co_ip;
		}
		public void setCo_ip(String co_ip) {
			this.co_ip = co_ip;
		}
		public int getCo_group() {
			return co_group;
		}
		public void setCo_group(int co_group) {
			this.co_group = co_group;
		}
		public int getCo_step() {
			return co_step;
		}
		public void setCo_step(int co_step) {
			this.co_step = co_step;
		}
		public int getCo_indent() {
			return co_indent;
		}
		public void setCo_indent(int co_indent) {
			this.co_indent = co_indent;
		}		
		
		@Override
		public String toString() {
			return "P_Co_DTO [co_no=" + co_no + ", ws_no=" + ws_no + ", p_no=" + p_no + ", wl_no=" + wl_no
					+ ", work_no=" + work_no + ", jyp_no=" + jyp_no + ", comment=" + comment + ", co_date=" + co_date
					+ ", co_ip=" + co_ip + ", co_group=" + co_group + ", co_step=" + co_step + ", co_indent="
					+ co_indent + ", jyp_name=" + jyp_name + ", jyp_profile=" + jyp_profile + "]";
		}
		
		public P_Co_DTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		public P_Co_DTO(int co_no, int ws_no, int p_no, int wl_no, int work_no, int jyp_no, String comment,
				String co_date, String co_ip, int co_group, int co_step, int co_indent) {
			super();
			this.co_no = co_no;
			this.ws_no = ws_no;
			this.p_no = p_no;
			this.wl_no = wl_no;
			this.work_no = work_no;
			this.jyp_no = jyp_no;
			this.comment = comment;
			this.co_date = co_date;
			this.co_ip = co_ip;
			this.co_group = co_group;
			this.co_step = co_step;
			this.co_indent = co_indent;
		}
		public P_Co_DTO(int co_no, int ws_no, int p_no, int wl_no, int work_no, int jyp_no, String comment,
				String co_date, String co_ip, int co_group, int co_step, int co_indent, String jyp_name, String jyp_profile) {
			super();
			this.co_no = co_no;
			this.ws_no = ws_no;
			this.p_no = p_no;
			this.wl_no = wl_no;
			this.work_no = work_no;
			this.jyp_no = jyp_no;
			this.comment = comment;
			this.co_date = co_date;
			this.co_ip = co_ip;
			this.co_group = co_group;
			this.co_step = co_step;
			this.co_indent = co_indent;
			this.jyp_name = jyp_name;
			this.jyp_profile = jyp_profile;
		}
		
		
		
		

		
		
		
		
		
		
	
} //end class
