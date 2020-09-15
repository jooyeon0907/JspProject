package com.jooyeon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jooyeon.dbmanager.DBManager;
import com.jooyeon.dto.JYP_Project_DTO;
import com.jooyeon.dto.P_Member_DTO;

public class JYP_Project_DAO {
	
	// 프로젝트 생성 
	// insert into jyp_project (ws_no, p_name, p_info, p_status, p_public ,p_ip ) values (?,?,?,?,?,?) 
	// parameter : JYP_Project_DTO dto
	// return : int
	public int add_pro(JYP_Project_DTO dto) {
		int result = -1;
		
		Connection conn = null; PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement("insert into jyp_project (ws_no, p_name, p_info, p_public ,p_ip,jyp_no ) values (?,?,?,?,?,?) ");
			pstmt.setInt(1, dto.getWs_no());
			pstmt.setString(2, dto.getP_name());
			pstmt.setString(3, dto.getP_info());
			pstmt.setString(4, dto.getP_public());
			pstmt.setString(5, dto.getP_ip()); //session에 저장된 jyp_ip 저장
			pstmt.setInt(6, dto.getJyp_no());//session에 저장된 jyp_no 저장
 			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		return result;
	}
	

	
	// 방금 만든 프로젝트 번호 뽑기
	//select p_no from jyp_project where p_ip = '111.111.111'  order by ws_no desc;
	public int p_no(JYP_Project_DTO dto) {
		int result = -1;	

		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement("select p_no from jyp_project where jyp_no = ?  order by p_no desc "); //본인이 방금 만든 프로젝트 번호 뽑기
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
	
	

	
	
	//프로젝트 리스트 뽑기 (해당 워크스페이스에 있는 프로젝트 ) 
	// select*from jyp_project where ws_no = ? (session에 저장된 워크스페이스 번호) 
	// parameter : int sw_no
	// return : ArrayList<JYP_Project_DTO>
	public ArrayList<JYP_Project_DTO> p_list(int ws_no){
		 ArrayList<JYP_Project_DTO> list = new  ArrayList<JYP_Project_DTO>();
		 
		 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();												
				pstmt = conn.prepareStatement("select*from jyp_project where ws_no = ? and jyp_no>0 order by p_no desc");
				pstmt.setInt(1,ws_no);
				rset = pstmt.executeQuery(); //표
				while(rset.next()) {//줄
					list.add(
							new JYP_Project_DTO(
									rset.getInt("p_no"),rset.getInt("ws_no"),
									rset.getString("p_name"),rset.getString("p_info"), rset.getInt("p_status"),
									rset.getString("p_start"), rset.getString("p_end"),rset.getString("p_complete"),
									rset.getString("p_public"),rset.getString("p_date"),rset.getString("p_ip"), 
									rset.getInt("jyp_no")
									)
							);
				}
				
	
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
		 
		 return list;
	}
	
	
	////////////////////////////
	//분류해서 리스트 뽑기

	//1. 내가 속한 프로젝트(관리자)
	//select*from jyp_project where p_no in (select p_no from p_member where ws_no=1 and jyp_no =6 and p_access = 1)   order by p_no desc
	public ArrayList<JYP_Project_DTO> pro_admin(P_Member_DTO dto){
		 ArrayList<JYP_Project_DTO> list = new  ArrayList<JYP_Project_DTO>();
		 
		 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();												
				pstmt = conn.prepareStatement("select*from jyp_project where p_no in (select p_no from p_member where ws_no=? and jyp_no =? and p_access = 1)   order by p_no desc");
				pstmt.setInt(1,dto.getWs_no());
				pstmt.setInt(2, dto.getJyp_no());
				rset = pstmt.executeQuery(); //표
				while(rset.next()) {//줄
					list.add(
							new JYP_Project_DTO(
									rset.getInt("p_no"),rset.getInt("ws_no"),
									rset.getString("p_name"),rset.getString("p_info"), rset.getInt("p_status"),
									rset.getString("p_start"), rset.getString("p_end"),rset.getString("p_complete"),
									rset.getString("p_public"),rset.getString("p_date"),rset.getString("p_ip"), 
									rset.getInt("jyp_no")
									)
							);
				}//end while
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
		 return list;
	}
	
	
	//2. 내가 속한 프로젝트(멤버)
	//select*from jyp_project where p_no in (select p_no from p_member where ws_no=1 and jyp_no =6 and p_access = 2)  order by p_no desc
	public ArrayList<JYP_Project_DTO> pro_member(P_Member_DTO dto){
		 ArrayList<JYP_Project_DTO> list = new  ArrayList<JYP_Project_DTO>();
		 
		 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();												
				pstmt = conn.prepareStatement("select*from jyp_project where p_no in (select p_no from p_member where ws_no=? and jyp_no =? and p_access = 2)  order by p_no desc");
				pstmt.setInt(1,dto.getWs_no());
				pstmt.setInt(2, dto.getJyp_no());
				rset = pstmt.executeQuery(); //표
				while(rset.next()) {//줄
					list.add(
							new JYP_Project_DTO(
									rset.getInt("p_no"),rset.getInt("ws_no"),
									rset.getString("p_name"),rset.getString("p_info"), rset.getInt("p_status"),
									rset.getString("p_start"), rset.getString("p_end"),rset.getString("p_complete"),
									rset.getString("p_public"),rset.getString("p_date"),rset.getString("p_ip"), 
									rset.getInt("jyp_no")
									)
							);
				}//end while
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
		 return list;
	}
	
	
	//3. 그 외 프로젝트 
	/* 
	  select * from jyp_project where p_no not in
	 ( select p_no from p_member where jyp_no =4) and ws_no=1 and jyp_no !=4 and jyp_no>0 order by p_no desc
	*/
	public ArrayList<JYP_Project_DTO> pro_etc(P_Member_DTO dto_pm, JYP_Project_DTO dto_p){
		 ArrayList<JYP_Project_DTO> list = new  ArrayList<JYP_Project_DTO>();
		 
		 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
			DBManager db = new DBManager();
			String sql =" select * from jyp_project where p_no not in " + 
					"	 ( select p_no from p_member where jyp_no =?) and ws_no=? and jyp_no !=? and jyp_no>0 order by p_no desc";
			try {
				conn = db.getConnection();												
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto_pm.getJyp_no());
				pstmt.setInt(2,dto_p.getWs_no());
				pstmt.setInt(3, dto_p.getJyp_no());
				rset = pstmt.executeQuery(); //표
				while(rset.next()) {//줄
					list.add(
							new JYP_Project_DTO(
									rset.getInt("p_no"),rset.getInt("ws_no"),
									rset.getString("p_name"),rset.getString("p_info"), rset.getInt("p_status"),
									rset.getString("p_start"), rset.getString("p_end"),rset.getString("p_complete"),
									rset.getString("p_public"),rset.getString("p_date"),rset.getString("p_ip"), 
									rset.getInt("jyp_no")
									)
							);
				}//end while
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
		 return list;
	}
	
	
	//////////////////////////////

	//프로젝트 정보 
	// select*from jyp_project where p_no=?
	// parameter : JYP_Project_DTO dto 
	// return  :  JYP_Project_DTO dto
	public JYP_Project_DTO p_info(JYP_Project_DTO dto ) {
		JYP_Project_DTO result = new JYP_Project_DTO();
		
		 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();												
				pstmt = conn.prepareStatement("select*from jyp_project where p_no=?");
				pstmt.setInt(1,dto.getP_no());
				rset = pstmt.executeQuery(); //표
				if(rset.next()) {//줄
						result =	new JYP_Project_DTO(
									rset.getInt("p_no"),rset.getInt("ws_no"),
									rset.getString("p_name"),rset.getString("p_info"), rset.getInt("p_status"),
									rset.getString("p_start"), rset.getString("p_end"),rset.getString("p_complete"),
									rset.getString("p_public"),rset.getString("p_date"),rset.getString("p_ip"), 
									rset.getInt("jyp_no")
									);
							
				}
							
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}		
		
		return result;
	}
	
	
	//프로젝트 정보수정 
	// update jyp_project set p_name=?, p_info=?, p_status=?, p_start=?,p_end=?,p_complete=?,p_public=?  where p_no=?
	// paramater : JYP_Project_DTO dto
	// return : int 
	public int p_edit(JYP_Project_DTO dto) {
		int result =-1;
		
		 Connection conn = null; PreparedStatement pstmt = null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();												
				pstmt = conn.prepareStatement("update jyp_project set p_name=?, p_info=?, p_status=?, p_start=?,p_end=?,p_complete=?,p_public=?  where p_no=?");
				pstmt.setString(1, dto.getP_name());
				pstmt.setString(2, dto.getP_info());
				pstmt.setInt(3, dto.getP_status());
				pstmt.setString(4, dto.getP_start());
				pstmt.setString(5, dto.getP_end());
				pstmt.setString(6, dto.getP_complete());
				pstmt.setString(7, dto.getP_public());
				pstmt.setInt(8, dto.getP_no());
				
				result = pstmt.executeUpdate();
	
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
		 
		 return result; 
	}
	
	

	//프로젝트 삭제  (권한체크 통과하고나서 dao 실행) 
	// parameter : JYP_Project_DTO dto
	// return : int 
	public int pro_delete(JYP_Project_DTO dto) {
		int result =-1;
		
		 Connection conn = null; PreparedStatement pstmt = null;
			DBManager db = new DBManager();
			String sql ="delete jyp_project,p_member,p_worklist,p_work,pw_member \r\n" + 
							"from jyp_project \r\n" + 
							"join p_member\r\n" + 
							"join p_worklist \r\n" + 
							"join p_work \r\n" + 
							"join pw_member \r\n" + 
							"where jyp_project.p_no = p_member.p_no\r\n" + 
							"and jyp_project.p_no = p_worklist.p_no\r\n" + 
							"and jyp_project.p_no = p_work.p_no\r\n" + 
							"and jyp_project.p_no = pw_member.p_no\r\n" + 
							"and jyp_project.p_no= ?" ;
			try {
				conn = db.getConnection();												
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getP_no());
				
				result = pstmt.executeUpdate();
	
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
		 
		 return result; 
	}
	
	
	
	
	
	
	
	
	
	
	
	

} //end class 
