package com.jooyeon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jooyeon.dbmanager.DBManager;
import com.jooyeon.dto.P_Member_DTO;

public class JYP_P_Member_DAO {

	
	//프로젝트 맴버추가
	// insert into jyp_project (ws_no, p_no, jyp_no, p_access, p_Mip) values (?,?,?,?,?) 
	// parameter : P_Member_DTO dto
	// return : int
	//초대 수락 버튼 누르면 실행됨 
	public int add_member( P_Member_DTO dto) {
		int result =-1;
		
		Connection conn = null; PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement("insert into p_member (ws_no, p_no, jyp_no, p_access, p_Mip) values (?,?,?,?,?)  ");
			pstmt.setInt(1, dto.getWs_no());
			pstmt.setInt(2, dto.getP_no()); 
			pstmt.setInt(3, dto.getJyp_no()); //session에 저장된 jyp_no 저장
			pstmt.setInt(4, dto.getP_access()); 
			pstmt.setString(5, dto.getP_Mip());  //session에 저장된 jyp_ip 저장

			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}		
		return result;
	}

	
	
	//프로젝트 멤버의 리스트
	// select*from p_member where jyp_no?
	// parameter : P_Member_DTO dto
	// return : P_Member_DTO 
	public ArrayList<P_Member_DTO> p_memList(P_Member_DTO dto) {
		ArrayList<P_Member_DTO> list = new ArrayList<P_Member_DTO>();
		
		 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		 DBManager db = new DBManager();
		 String sql = "select pm.*, jm.jyp_name " + 
				 		"from p_member pm join  jyp_member jm  " + 
				 		"on pm.jyp_no = jm.jyp_no " + 
				 		" where pm.p_no=? and pm.jyp_no>0 ";
		 try {
			conn=db.getConnection();
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getP_no()); 
		
			rset=pstmt.executeQuery();
			while(rset.next()) {
				//int ws_no, int p_no, int jyp_no, int p_access, String p_Mdate, String p_Mip)
				list.add(new P_Member_DTO(
						rset.getInt("ws_no"),
						rset.getInt("p_no"),
						rset.getInt("jyp_no"),
						rset.getInt("p_access"),
						rset.getString("p_Mdate"),
						rset.getString("p_Mip"),
						rset.getString("jyp_name")
						));
			}
			 
		} catch (Exception e) { e.printStackTrace(); }
		 finally {
			 if(rset!=null)  { try{rset.close();} catch (Exception e) { e.printStackTrace(); } }
			 if(pstmt!=null) { try{pstmt.close();} catch (Exception e) { e.printStackTrace(); } }
			 if(conn!=null)  { try{conn.close();} catch (Exception e) { e.printStackTrace(); } }
		 }
		 
		 return list;
	}
	
	
	
	
	//프로젝트 멤버의 정보 
	// select*from p_member where jyp_no?
	// parameter : P_Member_DTO dto
	// return : P_Member_DTO 
	public P_Member_DTO p_memInfo(P_Member_DTO dto) {
		P_Member_DTO result = new P_Member_DTO();
		
		 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		 DBManager db = new DBManager();
		 
		 try {
			conn=db.getConnection();
			
			pstmt=conn.prepareStatement("select*from p_member where p_no=? and jyp_no=?");
			pstmt.setInt(1, dto.getP_no()); 
			pstmt.setInt(2, dto.getJyp_no()); 
		
			rset=pstmt.executeQuery();
			if(rset.next()) {
				//int ws_no, int p_no, int jyp_no, int p_access, String p_Mdate, String p_Mip)
				result= new P_Member_DTO(
						rset.getInt("ws_no"),
						rset.getInt("p_no"),
						rset.getInt("jyp_no"),
						rset.getInt("p_access"),
						rset.getString("p_Mdate"),
						rset.getString("p_Mip")
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
	
	//유저가 프로젝트멤버인지 체크 
	// select*from p_member where jyp_no?
	// parameter : P_Member_DTO dto
	// return : int
	public int p_memCheck(P_Member_DTO dto) {
		int result = -1;
		
		 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		 DBManager db = new DBManager();
		 
		 try {
			conn=db.getConnection();
			
			pstmt=conn.prepareStatement("select count(*) from p_member where p_no=? and jyp_no=?");
			pstmt.setInt(1, dto.getP_no()); 
			pstmt.setInt(2, dto.getJyp_no()); 
		
			rset=pstmt.executeQuery();
			if(rset.next()) { result=rset.getInt(1);}
				
			 
		} catch (Exception e) { e.printStackTrace(); }
		 finally {
			 if(rset!=null)  { try{rset.close();} catch (Exception e) { e.printStackTrace(); } }
			 if(pstmt!=null) { try{pstmt.close();} catch (Exception e) { e.printStackTrace(); } }
			 if(conn!=null)  { try{conn.close();} catch (Exception e) { e.printStackTrace(); } }
		 }
		 
		 return result;
	}
	
	
	// 해당 프로젝트의 권한 (관리자인지 팀원인지 구별)
	// parameter :  P_Member_DTO dto
	// return : int   
	public int p_access( P_Member_DTO dto) {
		int result = -1;
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			
			//해당 프로젝트의 권한 조회 
			pstmt = conn.prepareStatement("select p_access from p_member where  p_no =? and jyp_no=?");
			pstmt.setInt(1, dto.getP_no());
			pstmt.setInt(2, dto.getJyp_no());
			rset = pstmt.executeQuery(); //표
			if(rset.next()) { result = rset.getInt(1);} 
		
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null)  {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		
		return result;
	}
	
	
	
	///프로젝트 멤버 삭제  (워크스페이스 탈퇴할때)
	// delete from p_member where p_no=? and jyp_no=?
	public int ws_pmDelete(P_Member_DTO dto) {
		int result = -1;
		
		Connection conn = null; PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			
			pstmt = conn.prepareStatement("delete from p_member where ws_no =? and jyp_no=?");
			pstmt.setInt(1, dto.getWs_no());
			pstmt.setInt(2, dto.getJyp_no());
			result = pstmt.executeUpdate();
			
		
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		
		return result;
	}
	
	
	///프로젝트 멤버 삭제  (프로젝트 탈퇴할때)
	// delete from p_member where p_no=? and jyp_no=?
	public int pro_pmDelete(P_Member_DTO dto) {
		int result = -1;
		
		Connection conn = null; PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			
			pstmt = conn.prepareStatement("delete from p_member where p_no =? and jyp_no=?");
			pstmt.setInt(1, dto.getP_no());
			pstmt.setInt(2, dto.getJyp_no());
			result = pstmt.executeUpdate();
			
		
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		
		return result;
	}
	
	//프로젝트  관리자 인원수 
	// select count(*) from p_member where p_no=? and p_access=1
	public int pAdmin_cnt(P_Member_DTO dto) {
		int result = -1;
		
		Connection conn = null; PreparedStatement pstmt = null;   ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			
			//해당 프로젝트의 권한 조회 
			pstmt = conn.prepareStatement("select count(*) from p_member where p_no=? and p_access=1");
			pstmt.setInt(1, dto.getP_no());
			rset = pstmt.executeQuery();
			if(rset.next()) {result = rset.getInt(1);}
			
		
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null)  {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		
		return result;
	}
	
	
	
} // end class
