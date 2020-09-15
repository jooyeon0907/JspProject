package com.jooyeon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jooyeon.dbmanager.DBManager;
import com.jooyeon.dto.JYP_Project_DTO;
import com.jooyeon.dto.P_WL_DTO;

public class JYP_WL_DAO {
	/* ㅁ 업무리스트
	 mysql> desc p_worklist;
	+---------+--------------+------+-----+-------------------+----------------+
	| Field   | Type         | Null | Key | Default           | Extra          |
	+---------+--------------+------+-----+-------------------+----------------+
	| wl_no   | int(11)      | NO   | PRI | NULL              | auto_increment |
	| ws_no   | int(11)      | NO   |     | NULL              |                |
	| p_no    | int(11)      | NO   |     | NULL              |                |
	| wl_name | varchar(100) | NO   |     | NULL              |                |
	| wl_date | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
	| wl_ip   | varchar(100) | NO   |     | NULL              |                |
	+---------+--------------+------+-----+-------------------+----------------+
	6 rows in set (0.03 sec) 
	 **/
		
		//업무리스트 추가하기 
		// insert into p_worklist (ws_no, p_no, wl_name, wl_ip) values (?,?,?,?) 
		// parameter : P_WL_DTO dto
		// return : iny
		public int wl_add(P_WL_DTO dto) {
			int result=-1;
			
			Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("insert into p_worklist (ws_no, p_no, wl_name, wl_ip,jyp_no) values (?,?,?,?,?) ");
				pstmt.setInt(1, dto.getWs_no()); // session에 저장된 ws_no 넣기
				pstmt.setInt(2, dto.getP_no());   // get으로 받은 p_no 넣기
				pstmt.setString(3, dto.getWl_name()); 
				pstmt.setString(4, dto.getWl_ip()); 
				pstmt.setInt(5, dto.getJyp_no());// session에 저장된 jyp_no 넣기
				result = pstmt.executeUpdate();
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null)  {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
			
			
			return result;
		}
		
	
		// 방금 만든 업무리스트 번호 뽑기
		//select p_no from jyp_project where p_ip = '111.111.111'  order by ws_no desc;
		public int wl_no(P_WL_DTO dto) {
			int result = -1;	

			Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();												
				pstmt = conn.prepareStatement("select wl_no from p_worklist where jyp_no = ?  order by wl_no desc "); //본인이 방금 만든 프로젝트 번호 뽑기
				pstmt.setInt(1, dto.getJyp_no()); 

				rset = pstmt.executeQuery();
				if(rset.next()) { result = rset.getInt(1); }
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
			
			
			return result;
		}
		
		
		
		
		//해당 프로젝트의 업무리스트 뽑아오기  
		// select*from p_worklist where p_no = ?  (get으로 받은 p_no)
		// parameter : P_WL_DTO dto
		// return : ArrayList<P_WL_DTO>
		public ArrayList<P_WL_DTO> list_name( P_WL_DTO dto) {
			ArrayList<P_WL_DTO> list= new ArrayList<P_WL_DTO>();
			
			Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("select*from p_worklist where p_no = ? and jyp_no>0");
				pstmt.setInt(1, dto.getP_no());
				

				rset = pstmt.executeQuery(); //표
				while(rset.next()) {//줄
					//int wl_no, int ws_no, int p_no, String wl_name, String wl_date, String wl_ip
					list.add(new P_WL_DTO( rset.getInt("wl_no"),
										   rset.getInt("ws_no"),
										   rset.getInt("p_no"),
										   rset.getString("wl_name"),  
										   rset.getString("wl_date"),  
										   rset.getString("wl_ip"),
										   rset.getInt("jyp_no")) );	
					}//while
		
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null)  {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
			
			return list;
		}
		
		
		//업무리스트 이름 변경 -  작성자이면 변경가능 (wl_ip() 에서 체크)
		//  update p_worklist set wl_name =? where ws_no=? 
		// parameter : P_WL_DTO dto
		// return : int
		public int wl_edit_name(P_WL_DTO dto) {
			int result =-1;
			
			Connection conn = null; PreparedStatement pstmt = null; 
			DBManager db = new DBManager();

			try {
				conn = db.getConnection();
				//해당 업무리스트이 작성자인지 조회 
				pstmt=conn.prepareStatement("update p_worklist set wl_name =? where wl_no=? ");
				pstmt.setString(1, dto.getWl_name());
				pstmt.setInt(2, dto.getWl_no());
				
				result=pstmt.executeUpdate();
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
			
			return result;
			
		}
		
		
		
		//해당 업무리스트 삭제 - 작성자이거나 관리자면 삭제가능 
		/*
		 2. 업무리스트 삭제하기  
		-  1.해당 프로젝트의 관리자이거나(p_member의 p_access가 1이라면 관리자권한)  ///JYP_Project_DAO에 작성
		  	 업무리스트 작성자이면 (p_worklist의 wl_ip ) //아니면 회원번호 필드를 넣어줘야할까요?(jyp_no) 
		   
		   @해당 프로젝트의 관리자이거나(p_member의 p_access가 1이라면 관리자권한)
		   	유저의 p_access가 관리자 권한인지(1) 확인 
		 //  select p_access from p_member where ws_no=? and  p_no =? and jyp_no=?
		   select p_access from p_member where  p_no =? and jyp_no=?
		   
		   
		   
		   @ 업무리스트 작성자이면 (p_worklist의 wl_ip )
		   // 유저의 ip와 같은지 확인 
		   select wl_ip from p_worklist where  wl_no=?   */

		  
		// 업무리스트 작성자 (jyp_no)
		// parameter :  P_WL_DTO dto
		// return : int 
		public int wl_jypNo(P_WL_DTO dto) {
			int result = -1;
			
			Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			DBManager db = new DBManager();

			try {
				conn = db.getConnection();
				//해당 업무리스트이 작성자인지 조회 
				pstmt=conn.prepareStatement("select jyp_no from p_worklist where  wl_no=?");
				pstmt.setInt(1, dto.getWl_no());
				rset=pstmt.executeQuery(); //표
				if(rset.next()) { result=rset.getInt(1); }
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null)  {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
			
			return result;
		}
		   	
		
	  
		 // @@@통과되면 삭제가능하게 
		//업무리스트 삭제 
		// parameter : P_WL_DTO dto_wl, P_Work_DTO dto_work 
		// return : int
		public int wl_delete(P_WL_DTO dto) {
			int result =-1;
			
			Connection conn = null; PreparedStatement pstmt = null; 
			DBManager db = new DBManager();
			String sql ="delete p_worklist,p_work,pw_member \r\n" + 
						"from p_worklist \r\n" + 
						"join p_work \r\n" + 
						"join pw_member \r\n" + 
						"where p_worklist.wl_no = p_work.wl_no\r\n" + 
						"and p_worklist.wl_no = pw_member.wl_no\r\n" + 
						"and p_worklist.wl_no= ?";
			try {
				conn = db.getConnection();
				//해당 업무리스트이 작성자인지 조회 
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getWl_no());
				
				result=pstmt.executeUpdate();
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
			
			return result;
		}
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
} //end class 
