package com.jooyeon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jooyeon.dbmanager.DBManager;
import com.jooyeon.dto.JYP_Member_DTO;

public class JYP_Member_DAO {
/*
	mysql> desc jyp_member;
	+------------+--------------+------+-----+-------------------+----------------+
	| Field      | Type         | Null | Key | Default           | Extra          |
	+------------+--------------+------+-----+-------------------+----------------+
	| jyp_no     | int(11)      | NO   | PRI | NULL              | auto_increment |
	| jyp_email  | varchar(200) | NO   |     | NULL              |                |
	| jyp_name   | varchar(100) | NO   |     | NULL              |                |
	| jyp_pass   | varchar(20)  | NO   |     | NULL              |                |
	| jyp_tel    | varchar(100) | NO   |     | NULL              |                |
	| gender     | char(2)      | NO   |     | NULL              |                |
	| birth      | datetime     | YES  |     | NULL              |                |
	| jyp_date   | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
	| jyp_ip     | varchar(20)  | NO   |     | NULL              |                |
	| jyp_access | varchar(20)  | YES  |     | NULL              |                |
	| dept       | varchar(50)  | YES  |     | NULL              |                |
	| position   | varchar(50)  | YES  |     | NULL              |                |
	+------------+--------------+------+-----+-------------------+----------------+
	12 rows in set (0.01 sec)
	 */
		//<<join+login>>
		//ㅁ table : jyp_member
		//<<회원가입 >>
		//아이디 중복체크 (이메일을 아이디로 사용 ) 
		//select count(*) from jyp_member where jyp_email =?
		/**
		 1. public int idCheck(JYP_Member_DTO dto)
		 2. return : int
		 3. parameter : JYP_Member_DTO
		 **return 값이 0이면 통과 1이상이면 중복되는 아이디가 있으므로 사용불가 
		 */
		public int idCheck(JYP_Member_DTO dto) {
			 int result =-1;
			 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			 DBManager db = new DBManager();
			 
			 try {
				conn=db.getConnection();
				
				pstmt=conn.prepareStatement("select count(*) from jyp_member where jyp_email=?");
				pstmt.setString(1, dto.getJyp_email()); 
				
				rset=pstmt.executeQuery();
				if(rset.next()) { result=rset.getInt(1); } 
				 
			} catch (Exception e) { e.printStackTrace(); }
			 finally {
				 if(rset!=null)  { try{rset.close();} catch (Exception e) { e.printStackTrace(); } }
				 if(pstmt!=null) { try{pstmt.close();} catch (Exception e) { e.printStackTrace(); } }
				 if(conn!=null)  { try{conn.close();} catch (Exception e) { e.printStackTrace(); } }
			 }

			 return result;
		 }
		
		
		
		//가입정보저장
		/*insert into jyp_member (jyp_email,jyp_name, jyp_pass, jyp_tel, gender, jyp_ip) 
						  values (?, ?, ?, ?, ? , ?)  */
		/**
		 1. public int join(JYP_Member_DTO dto)
		 2. return : int
		 3. parameter : JYP_Member_DTO
		  **return 값>0이면 가입성공
		 */	
		 public int join(JYP_Member_DTO dto) {
			 int result =-1;
			 Connection conn = null; PreparedStatement pstmt = null; 
			 DBManager db = new DBManager();
			 
			 try {
				conn=db.getConnection();
				
				pstmt=conn.prepareStatement("insert into jyp_member (jyp_email,jyp_name, jyp_pass, jyp_tel, gender, birth, jyp_ip) values (?, ?, ?, ?, ? , ?, ?)  ");
				pstmt.setString(1, dto.getJyp_email()); 
				pstmt.setString(2, dto.getJyp_name()); 
				pstmt.setString(3, dto.getJyp_pass()); 
				pstmt.setString(4, dto.getJyp_tel()); 
				pstmt.setString(5, dto.getGender());
				pstmt.setString(6, dto.getBirth()); 
				pstmt.setString(7, dto.getJyp_ip()); 
				
				result=pstmt.executeUpdate();
				 
			} catch (Exception e) { e.printStackTrace(); }
			 finally {
				 if(pstmt!=null) { try{pstmt.close();} catch (Exception e) { e.printStackTrace(); } }
				 if(conn!=null)  { try{conn.close();} catch (Exception e) { e.printStackTrace(); } }
			 }

			 return result;
		 }
		//<<로그인>>   
		//로그인하기 (해당아이디와 비밀번호가 같은 유저가 1명인지 체크
		// select count(*) from jyp_member where jyp_email = ? and jyp_pass =? 
		/**
		 1. public int login(JYP_Member_DTO dto)
		 2. return : int
		 3. parameter : JYP_Member_DTO
		  **return 값==1이면 로그인 성공   
		  *로그인 성공시 session에 jyp_email, jyp_pass 저장
		 */	
		 public int login(JYP_Member_DTO dto) {
			 int result =-1;
			 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			 DBManager db = new DBManager();
			 
			 try {
				conn=db.getConnection();
				
				pstmt=conn.prepareStatement(" select count(*) from jyp_member where jyp_email = ? and jyp_pass =?");
				pstmt.setString(1, dto.getJyp_email()); 
				pstmt.setString(2, dto.getJyp_pass()); 
				
				rset=pstmt.executeQuery();
				if(rset.next()) { result=rset.getInt(1); } 
				 
			} catch (Exception e) { e.printStackTrace(); }
			 finally {
				 if(rset!=null)  { try{rset.close();} catch (Exception e) { e.printStackTrace(); } }
				 if(pstmt!=null) { try{pstmt.close();} catch (Exception e) { e.printStackTrace(); } }
				 if(conn!=null)  { try{conn.close();} catch (Exception e) { e.printStackTrace(); } }
			 }

			 return result;
		 }	
		
		 
		 //로그인한 회원이 소속된 워크스페이스가 있는지 검사 
		 // select count(*) from jyp_workspace where jyp_no = ? 로그인되어있는 회원번호(jyp_no) 
		 public int login_ws(int jyp_no) {
			 int result = -1;

			 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			 DBManager db = new DBManager();
			 
			 try {
				conn=db.getConnection();
				
				pstmt=conn.prepareStatement("select count(*) from ws_member where jyp_no = ?");
			//	pstmt=conn.prepareStatement("select count(*) from jyp_workspace where jyp_no = ?");
				pstmt.setInt(1, jyp_no); 
				
				rset=pstmt.executeQuery();
				if(rset.next()) { result=rset.getInt(1); } 
				 
			} catch (Exception e) { e.printStackTrace(); }
			 finally {
				 if(rset!=null)  { try{rset.close();} catch (Exception e) { e.printStackTrace(); } }
				 if(pstmt!=null) { try{pstmt.close();} catch (Exception e) { e.printStackTrace(); } }
				 if(conn!=null)  { try{conn.close();} catch (Exception e) { e.printStackTrace(); } }
			 }

			 return result;
		 }
		 
		 
		//마이페이지 보기,회원가입 완료페이지, 마이페이지 수정폼 정보 불러오기 
		//- 이메일,이름, 부서, 직함, 전화번호, 성별 , 가입날짜, 생일  
		// select*from jyp_member where jyp_email =? 
		/**
		 1. public JYP_Member_DTO userinfo(JYP_Member_DTO dto)
		 2. return : JYP_Member_DTO
		 3. parameter : JYP_Member_DTO
		 */	
		 public JYP_Member_DTO userinfo(JYP_Member_DTO dto) {
			 JYP_Member_DTO result= new JYP_Member_DTO();
			 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			 DBManager db = new DBManager();
			 
			 try {
				conn=db.getConnection();
				
				pstmt=conn.prepareStatement("select*from jyp_member where jyp_email =?");
				pstmt.setString(1, dto.getJyp_email()); 
			
				rset=pstmt.executeQuery();
				if(rset.next()) {
					result= new JYP_Member_DTO(
							rset.getInt("jyp_no"),
							rset.getString("jyp_email"),
							rset.getString("jyp_name"), 
							rset.getString("jyp_pass"), 
							rset.getString("jyp_tel"),
							rset.getString("gender"),
							rset.getString("birth"),
							rset.getString("jyp_date"),
							rset.getString("jyp_ip"),
							rset.getString("dept"),
							rset.getString("position"),
							rset.getString("jyp_profile")
							);
				}
				 
			} catch (Exception e) { e.printStackTrace(); }
			 finally {
				 if(rset!=null)  { try{rset.close();} catch (Exception e) { e.printStackTrace(); } }
				 if(pstmt!=null) { try{pstmt.close();} catch (Exception e) { e.printStackTrace(); } }
				 if(conn!=null)  { try{conn.close();} catch (Exception e) { e.printStackTrace(); } }
			 }

			 return result;
		 }		
		 
		 ///회원 번호로 이메일 뽑기
		 // select jyp_email from jyp_member where jyp_no=?
		 public String jyp_email(JYP_Member_DTO dto) {
			 String result ="";
			 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			 DBManager db = new DBManager();
			 
			 try {
				conn=db.getConnection();
				
				pstmt=conn.prepareStatement("select jyp_email from jyp_member where jyp_no=?");
				pstmt.setInt(1, dto.getJyp_no()); 
				
				rset=pstmt.executeQuery();
				if(rset.next()) { result=rset.getString(1); } 
				 
			} catch (Exception e) { e.printStackTrace(); }
			 finally {
				 if(rset!=null)  { try{rset.close();} catch (Exception e) { e.printStackTrace(); } }
				 if(pstmt!=null) { try{pstmt.close();} catch (Exception e) { e.printStackTrace(); } }
				 if(conn!=null)  { try{conn.close();} catch (Exception e) { e.printStackTrace(); } }
			 }

			 return result;
		 }
		 
		
	 
		 
		
		//가입정보수정 (마이페이지 수정) ※※※※이메일 변경을 따로 뺄지 고민 (따로 빼서 변경될때 세션값도 변경시켜주기?)
		//-이메일, 이름, 전화번호, 부서, 직함,  생일// 성별(변경불가) , 가입날짜(변경불가)
		/* update jyp_member set jyp_email=?, jyp_name =?, jyp_tel=?, dept=?, position=?, birth=? 
		 					where jyp_email =? */
		/**
		 1. public JYP_Member_DTO user_edit(JYP_Member_DTO dto)
		 2. return : JYP_Member_DTO
		 3. parameter : JYP_Member_DTO
		 return 값 > 0이면 수정 성공 

		 */			
		 public int user_edit(JYP_Member_DTO dto) {
			 int result =-1;
			 Connection conn = null; PreparedStatement pstmt = null; 
			 DBManager db = new DBManager();
			 
			 try {
				conn=db.getConnection();
				
				pstmt=conn.prepareStatement(" update jyp_member set  jyp_name =?, jyp_tel=?, dept=?, position=?, birth=?, jyp_profile=? " + 
						"	 					where jyp_email =? ");
				pstmt.setString(1, dto.getJyp_name()); 
				pstmt.setString(2, dto.getJyp_tel()); 
				pstmt.setString(3, dto.getDept()); 
				pstmt.setString(4, dto.getPosition()); 
				pstmt.setString(5, dto.getBirth()); 
				pstmt.setString(6, dto.getJyp_profile());
				pstmt.setString(7, dto.getJyp_email());
				
				result=pstmt.executeUpdate();
				 
			} catch (Exception e) { e.printStackTrace(); }
			 finally {
				 if(pstmt!=null) { try{pstmt.close();} catch (Exception e) { e.printStackTrace(); } }
				 if(conn!=null)  { try{conn.close();} catch (Exception e) { e.printStackTrace(); } }
			 }

			 return result;
		 }
		
		 
		 // 프로필사진  - 기본 이미지로 변경
		 // update jyp_member set jyp_profile='noImg.png' where jyp_no =? 
		 public int noImg(JYP_Member_DTO dto) {
			 int result =-1;
			 Connection conn = null; PreparedStatement pstmt = null; 
			 DBManager db = new DBManager();
			 
			 try {
				conn=db.getConnection();
				
				pstmt=conn.prepareStatement("update jyp_member set jyp_profile='noImg.png' where jyp_no =? ");
				pstmt.setInt(1, dto.getJyp_no());
				result=pstmt.executeUpdate();
				 
			} catch (Exception e) { e.printStackTrace(); }
			 finally {
				 if(pstmt!=null) { try{pstmt.close();} catch (Exception e) { e.printStackTrace(); } }
				 if(conn!=null)  { try{conn.close();} catch (Exception e) { e.printStackTrace(); } }
			 }

			 return result;
		 }
		 
		 
		 
		 
		 
		//비번변경 
		// update jyp_member set jyp_pass=? where jyp_email=? and jyp_pass=? 
		/**
		 1. public int user_pass(JYP_Member_DTO dto, Stirng oldpass)
		 2. return : int
		 3. parameter : JYP_Member_DTO
		 return 값>0이면 비번변경 성공
		 */			
		 public int user_pass(JYP_Member_DTO dto,String oldpass) {
			 int result =-1;
			 Connection conn = null; PreparedStatement pstmt = null; 
			 DBManager db = new DBManager();
			 
			 try {
				conn=db.getConnection();
				
				pstmt=conn.prepareStatement("update jyp_member set jyp_pass=? where jyp_email=? and jyp_pass=? ");
				pstmt.setString(1, dto.getJyp_pass()); 
				pstmt.setString(2, dto.getJyp_email()); 
				pstmt.setString(3, oldpass); 
				
				result=pstmt.executeUpdate();
				 
			} catch (Exception e) { e.printStackTrace(); }
			 finally {
				 if(pstmt!=null) { try{pstmt.close();} catch (Exception e) { e.printStackTrace(); } }
				 if(conn!=null)  { try{conn.close();} catch (Exception e) { e.printStackTrace(); } }
			 }

			 return result;
		 }
		 
		 
		//탈퇴하기 
		// delete from jyp_member where jyp_email=? and jyp_pass=? 
		/**
		 1. public int user_delete(JYP_Member_DTO dto)
		 2. return : int
		 3. parameter : JYP_Member_DTO
		 return 값>0이면 탈퇴 성공
		 */			
		 public int user_delete(JYP_Member_DTO dto) {
			 int result =-1;
			 Connection conn = null; PreparedStatement pstmt = null; 
			 DBManager db = new DBManager();
			 
			 try {
				conn=db.getConnection();
				
				pstmt=conn.prepareStatement("delete from jyp_member where jyp_email=? and jyp_pass=? ");
				pstmt.setString(1, dto.getJyp_email()); 
				pstmt.setString(2, dto.getJyp_pass()); 
				
				result=pstmt.executeUpdate();
				 
			} catch (Exception e) { e.printStackTrace(); }
			 finally {
				 if(pstmt!=null) { try{pstmt.close();} catch (Exception e) { e.printStackTrace(); } }
				 if(conn!=null)  { try{conn.close();} catch (Exception e) { e.printStackTrace(); } }
			 }

			 return result;
		 }
		 	
		
		///////////jyproject 회원 검색 (ajax _gson)
		// select*from jyp_member;
		// parameter : X
		// return : ArrayList<JYP_Member_DTO> 
		 public JsonObject jypmemberList(){
			 JsonObject result = new JsonObject();
			 
				
				DBManager db = new DBManager();
				Connection conn = null; PreparedStatement pstmt =null; ResultSet rset = null;
				
				try {
					conn = db.getConnection();
					pstmt = conn.prepareStatement("select*from jyp_member");
					rset = pstmt.executeQuery(); //표
					
					JsonArray list = new JsonArray();
					//1. 배열만들기 []
					while(rset.next()) { //줄
						JsonObject job = new JsonObject();
						job.addProperty("jyp_no", rset.getInt("jyp_no"));
						job.addProperty("jyp_email", rset.getString("jyp_email"));
						job.addProperty("jyp_email", rset.getString("jyp_name"));
						job.addProperty("jyp_email", rset.getString("jyp_pass"));
						job.addProperty("jyp_email", rset.getString("jyp_tel"));
						job.addProperty("jyp_email", rset.getString("gender"));
						job.addProperty("jyp_email", rset.getString("birth"));
						job.addProperty("jyp_name", rset.getString("jyp_date"));
						job.addProperty("jyp_name", rset.getString("jyp_ip"));
						job.addProperty("jyp_name", rset.getString("dept"));
						job.addProperty("jyp_name", rset.getString("position"));
						list.add(job);
					}
					//2. gson
				//	Gson gson = new Gson();
				//	JsonObject user = new JsonObject();
					result.add("user", list);
				//	out.println(gson.toJson(user));
				//	out.close();
					
				} catch (Exception e) {  e.printStackTrace(); }
				finally {
					if(rset!=null) {try{rset.close();}catch (Exception e) {  e.printStackTrace(); }}
					if(pstmt!=null) {try{pstmt.close();}catch (Exception e) {  e.printStackTrace(); }}
					if(conn!=null) {try{conn.close();}catch (Exception e) {  e.printStackTrace(); }}
				}
				
			 
			 
			 return result;
		 }
		
		
		////가입할때 아이디중복확인 
		 
		 public String jypmember_idCheack(JYP_Member_DTO dto){
			 String result = "";
			 
				DBManager db = new DBManager();
				Connection conn = null; PreparedStatement pstmt =null; ResultSet rset = null;
					
				try {
					conn = db.getConnection();
					pstmt = conn.prepareStatement("select count(*) cnt from jyp_member where jyp_email =?");
					pstmt.setString(1, dto.getJyp_email());
					rset = pstmt.executeQuery(); //표
					int cnt =-1;
					String data ="사용 가능한 이메일";
				
					if(rset.next()) { cnt = rset.getInt("cnt");}
					if(cnt==1) { data= "이미 가입된 이메일입니다.";} //이미 존재하는 아이디가 있다면
//					System.out.println("jypmember_idCheack의 cnt :  " + cnt );		
					Gson gson = new Gson();
					JsonObject menu = new JsonObject();
					menu.addProperty("data", data);
					result = gson.toJson(menu);

					
				} catch (Exception e) {  e.printStackTrace(); }
				finally {
					if(rset!=null) {try{rset.close();}catch (Exception e) {  e.printStackTrace(); }}
					if(pstmt!=null) {try{pstmt.close();}catch (Exception e) {  e.printStackTrace(); }}
					if(conn!=null) {try{conn.close();}catch (Exception e) {  e.printStackTrace(); }}
				}
			 
			 
			 return result;
		 }
		 
		 
		 
		 
		 
		 
		 
	}//end class


