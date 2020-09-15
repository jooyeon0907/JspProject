package com.jooyeon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jooyeon.dbmanager.DBManager;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.dto.WS_Memeber_DTO;

public class JYP_WS_Member_DAO {
	/////////////////////////////////////////////////////////////////
	//ws_member
	
	//워크스페이스 멤버 추가
	// insert into ws_member (ws_no, jyp_no,ws_Mip ) values (?,?,?)
	public int member(WS_Memeber_DTO dto) {
		int result = -1;
		Connection conn = null; PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement("insert into ws_member (ws_no, jyp_no,ws_Mip ) values (?,?,?)");
			pstmt.setInt(1, dto.getWs_no());
			pstmt.setInt(2, dto.getJyp_no());	
			pstmt.setString(3, dto.getWs_Mip());  
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		return result;
	}
	
	
	//워크스페이스의 멤버 리스트
	//select ws_name from jyp_workspace where ws_no = ( select ws_no from ws_member where jyp_no= ? );
	// parameter : int jyp_no
	// return : ArraylList<JYP_WS_DTO> 
	public ArrayList<WS_Memeber_DTO> ws_memList(int jyp_no){
		ArrayList<WS_Memeber_DTO> list = new ArrayList<WS_Memeber_DTO>();
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement("select * from  ws_member where jyp_no = ? order by ws_no asc ");
			pstmt.setInt(1, jyp_no); //request에서 받은 값 저장

			rset = pstmt.executeQuery();
			//(int ws_no, String ws_name, int jyp_no, String ws_ip, String ws_date, String ws_del, String ws_delDday
			//int ws_no, int jyp_no, String ws_Mdate, String ws_Mip
			while(rset.next()) { list.add(new WS_Memeber_DTO(
												rset.getInt("ws_no"),
												rset.getInt("jyp_no"),
												rset.getString("ws_Mdate"),
												rset.getString("ws_Mip")
											)); }
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		return list;
	}
	
	//워크스페이스 멤버 정보리스트
	//  select jm.* from jyp_member jm join ws_member wm using(jyp_no) where jyp_no=?
	// parameger : JYP_Member_DTO dto
	// return : ArrayList<JYP_Member_DTO>
	public ArrayList<JYP_Member_DTO> ws_memInfo(WS_Memeber_DTO dto){
		ArrayList<JYP_Member_DTO> list = new ArrayList<JYP_Member_DTO>();
		
		 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		 DBManager db = new DBManager();
		 
		 try {
			conn=db.getConnection();
			
			pstmt=conn.prepareStatement("select*from jyp_member where jyp_no in (select jyp_no from ws_member where ws_no=?)");
			pstmt.setInt(1, dto.getWs_no());
		
			rset=pstmt.executeQuery();
			while(rset.next()) {
				list.add(new JYP_Member_DTO(
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
						));
			} //while
			 
		} catch (Exception e) { e.printStackTrace(); }
		 finally {
			 if(rset!=null)  { try{rset.close();} catch (Exception e) { e.printStackTrace(); } }
			 if(pstmt!=null) { try{pstmt.close();} catch (Exception e) { e.printStackTrace(); } }
			 if(conn!=null)  { try{conn.close();} catch (Exception e) { e.printStackTrace(); } }
		 }		
	
		return list;
	}
	
	//////////주소록 paging DAO////////////////////////////
	//전체 리스트의 개수 
	// select count(*) from ws_member where ws_no=?  
	public int listCnt(WS_Memeber_DTO dto) {
		int result = -1;
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement("select count(*) from ws_member where ws_no=?  ");
			pstmt.setInt(1, dto.getWs_no()); 
			
			rset = pstmt.executeQuery();
			if(rset.next()) {result = rset.getInt(1);}
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		return result;
	}
	
	
	//페이지 시작번호 (10의 단위) (먼저 가입한 멤버 순으로) 
	public ArrayList<JYP_Member_DTO> list10(WS_Memeber_DTO dto, int pstartNo){
		ArrayList<JYP_Member_DTO> list = new ArrayList<JYP_Member_DTO>();
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
		DBManager db = new DBManager();
		String sql="select jm.*, wm.ws_Mdate  " + 
				"from jyp_member jm join ws_member wm " + 
				"on jm.jyp_no = wm.jyp_no " + 
				"where wm.ws_no=? " + 
				"group by wm.jyp_no " + 
				"having jm.jyp_no in (select jyp_no from ws_member)  " + 
				"order by wm.ws_Mdate asc limit ?,10";
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getWs_no()); 
			pstmt.setInt(2, pstartNo); 
			
			rset = pstmt.executeQuery(); //표
			while(rset.next()) {
				list.add(new JYP_Member_DTO(
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
						rset.getString("ws_Mdate"),
						rset.getString("jyp_profile")
						));
			} //while
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}	
		
		return list;
	}
	//////////end paging DAO////////////////////////////
	
	
	
	
	
	// 유저가 워크스페이스 멤버인지 확인하기
	// select count(*) ws_member from ws_no=? and jyp_no=? 
	public int ws_memCheck(WS_Memeber_DTO dto){
		int result = -1;
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement("select count(*) from ws_member where ws_no=? and jyp_no=? ");
			pstmt.setInt(1, dto.getWs_no()); 
			pstmt.setInt(2, dto.getJyp_no()); 
			
			rset = pstmt.executeQuery();
			if(rset.next()) {result = rset.getInt(1);}
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		return result;
	}
	
	
	
	
	// 해당 워크스페이스의 멤버 모두 삭제     ////워크스페이스 삭제하는 구문에 있어서 안쓸듯 
	// delete from ws_member where ws_no =? 
	// parameter : JYP_WS_DTO dto
	// return : int 
	public int ws_memDelAll(WS_Memeber_DTO dto) {
		int result = -1;

		Connection conn = null; PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement("delete from ws_member where ws_no =? ");
			pstmt.setInt(1, dto.getWs_no());
			result = pstmt.executeUpdate();
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		return result; 
	}
	
	
	
	// 해당 워크스페이스에서 선택한 멤버 삭제 
	// -> p_member , pw_member에도 탈퇴할 멤버의 데이터 삭제해줘야됨
	// delete from ws_member where ws_no =? 
	// parameter : JYP_WS_DTO dto
	// return : int 
	public int ws_memDelete(WS_Memeber_DTO dto) {
		int result = -1;

		Connection conn = null; PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement("delete from ws_member where ws_no =? and jyp_no=?  ");
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
	
	
	
}//end class 
