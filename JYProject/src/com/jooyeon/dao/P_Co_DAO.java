package com.jooyeon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jooyeon.dbmanager.DBManager;
import com.jooyeon.dto.P_Co_DTO;

public class P_Co_DAO {

	
	//원본글 삽입
		//insert into p_comments (ws_no, p_no, wl_no, work_no, jyp_no, comment ,co_ip,co_group ,co_step values (?,?,?,?,?,?,?,?,?)
		/*
		 co_group =1 co_step=1000 으로 시작 
		 co_step = co_group*1000;
		 */
		//parameter : P_Co_DTO dto 
		//return :  int
		public int create( P_Co_DTO dto ) {
			 int result = -1;
			 
				Connection conn = null; PreparedStatement pstmt =null;
				DBManager db = new DBManager();
				
				try {
					conn= db.getConnection();
					/////////////////////////////
					pstmt = conn.prepareStatement("insert into p_comments "
												+"(ws_no, p_no, wl_no, work_no, jyp_no, comment ,co_ip,co_group ,co_step) "
												+"values (?,?,?,?,?,?,?,?,?) ");
					pstmt.setInt(1, dto.getWs_no());
					pstmt.setInt(2, dto.getP_no());
					pstmt.setInt(3, dto.getWl_no());
					pstmt.setInt(4, dto.getWork_no());
					pstmt.setInt(5, dto.getJyp_no());
					pstmt.setString(6, dto.getComment());
					pstmt.setString(7, dto.getCo_ip());
					pstmt.setInt(8, dto.getCo_group());
					pstmt.setInt(9, dto.getCo_step());
					result=pstmt.executeUpdate();

					
				} catch (Exception e) {  e.printStackTrace(); }
				finally {
					if(pstmt!=null) {try{pstmt.close();} catch (Exception e) {  e.printStackTrace(); }}
					if(conn!=null) {try{conn.close();} catch (Exception e) {  e.printStackTrace(); }}
				}
				
			 return result;
		}
		
		
		
	//내용 리스트 다 뽑기
	public JsonArray listAll_ajax(int work_no){
		JsonArray list = new JsonArray();
		
		Connection conn = null; PreparedStatement pstmt =null; ResultSet rset =null;
		DBManager db = new DBManager();
		
		try {
			conn= db.getConnection();
			/////////////////////////////
			pstmt = conn.prepareStatement("select*from p_comments where work_no =  ? order by co_step desc , co_indent asc");
			pstmt.setInt(1, work_no);
			rset = pstmt.executeQuery(); //표
			
			//{"" : []}
			while(rset.next()) {//줄
				JsonObject job = new JsonObject();
				job.addProperty("co_no", rset.getInt("co_no"));
				job.addProperty("ws_no", rset.getInt("ws_no"));
				job.addProperty("p_no", rset.getInt("p_no"));
				job.addProperty("wl_no", rset.getInt("wl_no"));
				job.addProperty("work_no", rset.getInt("work_no"));
				job.addProperty("jyp_no", rset.getInt("jyp_no"));
				job.addProperty("comment", rset.getString("comment"));
				job.addProperty("co_date", rset.getString("co_date"));
				job.addProperty("co_ip", rset.getString("co_ip"));
				job.addProperty("co_group", rset.getInt("co_group"));
				job.addProperty("co_step", rset.getInt("co_step"));
				job.addProperty("co_indent", rset.getInt("co_indent"));

				list.add(job);
			}//while
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();} catch (Exception e) {  e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();} catch (Exception e) {  e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();} catch (Exception e) {  e.printStackTrace(); }}
		}
		
		return list;
	}
	
	
	
	
	 	//내용 리스트 다 뽑기
	public ArrayList<P_Co_DTO> listAll(int work_no){
		ArrayList<P_Co_DTO> list = new ArrayList<P_Co_DTO>();
		
		Connection conn = null; PreparedStatement pstmt =null; ResultSet rset =null;
		DBManager db = new DBManager();
		String sql = "select co.*, mem.jyp_name, mem.jyp_profile " + 
					"from p_comments co join jyp_member mem " + 
					"on co.jyp_no = mem.jyp_no  " + 
					"where work_no =  ? " + 
					"order by co_step desc , co_indent asc ";
		try {
			conn= db.getConnection();
			/////////////////////////////
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, work_no);
			rset = pstmt.executeQuery(); //표
			while(rset.next()) {//줄
				list.add(new P_Co_DTO( rset.getInt("co_no"),
										rset.getInt("ws_no"),
										rset.getInt("p_no"),
										rset.getInt("wl_no"),
										rset.getInt("work_no"),
										rset.getInt("jyp_no"),
										rset.getString("comment"),
										rset.getString("co_date"),
										rset.getString("co_ip"),
										rset.getInt("co_group"),
										rset.getInt("co_step"),
										rset.getInt("co_indent"),
										rset.getString("jyp_name"),
										rset.getString("jyp_profile")
										));
			}//while
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();} catch (Exception e) {  e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();} catch (Exception e) {  e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();} catch (Exception e) {  e.printStackTrace(); }}
		}
		
		return list;
	}
	
	
	//코멘트 정보
	public P_Co_DTO coInfo(P_Co_DTO dto) {
		P_Co_DTO result = new P_Co_DTO();
		Connection conn = null; PreparedStatement pstmt =null; ResultSet rset =null;
		DBManager db = new DBManager();
		try {
			conn= db.getConnection();
			/////////////////////////////
			pstmt = conn.prepareStatement("select*from p_comments where co_no=?");
			pstmt.setInt(1, dto.getCo_no());
			rset = pstmt.executeQuery(); //표
			while(rset.next()) {//줄
				result = new P_Co_DTO( rset.getInt("co_no"),
										rset.getInt("ws_no"),
										rset.getInt("p_no"),
										rset.getInt("wl_no"),
										rset.getInt("work_no"),
										rset.getInt("jyp_no"),
										rset.getString("comment"),
										rset.getString("co_date"),
										rset.getString("co_ip"),
										rset.getInt("co_group"),
										rset.getInt("co_step"),
										rset.getInt("co_indent"));
			}//while
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();} catch (Exception e) {  e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();} catch (Exception e) {  e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();} catch (Exception e) {  e.printStackTrace(); }}
		}
		
		return result;
	}
	
	//최대번호 찾기
	// select max(bstep) from mvcboard8 where bgroup = ?;
	// parameter : Board_DTO dto 
	// return : int 
	/*
	 * bgroup 안잡는 회사도 있는데 잡는게 정리가 편함  
	*bgroup 안잡혔으면 bstep의 최대값을 구해서 계산
	 //////
	  @원본글이 삭제된 경우 ( bno=2 일때) 
	  다음 원본글 bno = 3
	  
	  bstepㅢ 
	 */
	public int max_read(int work_no  ) {
		int max =-1;
		
		Connection conn = null; PreparedStatement pstmt =null; ResultSet rset =null;
		DBManager db = new DBManager();
		try {
			conn= db.getConnection();
			/////////////////////////////
			pstmt = conn.prepareStatement("select max(co_step) from p_comments where work_no =? ");
			pstmt.setInt(1, work_no);
			rset = pstmt.executeQuery();
			if(rset.next()) {max = rset.getInt(1); }

			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();} catch (Exception e) {  e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();} catch (Exception e) {  e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();} catch (Exception e) {  e.printStackTrace(); }}
		}
		
		return max;
	}
	


	//수정기능
	// update p_comments set comment=? where co_no=?
	//parameter :  P_Co_DTO dto  
	//return : int
	public int edit(P_Co_DTO dto ) {
		int result =-1;
			
		Connection conn = null; PreparedStatement pstmt =null;
		DBManager db = new DBManager();
		
		try {
			conn= db.getConnection();
			/////////////////////////////
			pstmt = conn.prepareStatement("update p_comments set comment=? where co_no=?");
			pstmt.setString(1, dto.getComment());
			pstmt.setInt(2, dto.getCo_no());
			result=pstmt.executeUpdate();

			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();} catch (Exception e) {  e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();} catch (Exception e) {  e.printStackTrace(); }}
		}
		
		return result;
	}
	
	
	
	//삭제기능
	// delete from p_comments where co_no = ? and jyp_no= ?
	//parameter :  P_Co_DTO dto  
	//return :  int 
	public int delete( P_Co_DTO dto ) {
		int result =-1;
		
		Connection conn = null; PreparedStatement pstmt =null;
		DBManager db = new DBManager();
		
		try {
			conn= db.getConnection();
			/////////////////////////////
			pstmt = conn.prepareStatement("delete from p_comments where co_no = ? and jyp_no= ?");
			pstmt.setInt(1, dto.getCo_no());
			pstmt.setInt(2, dto.getJyp_no());
			result=pstmt.executeUpdate();

			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();} catch (Exception e) {  e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();} catch (Exception e) {  e.printStackTrace(); }}
		}	
		return result;
	}
	
	
	
	
	//답변글 달기
	// insert into p_comments (ws_no, p_no, wl_no, work_no, jyp_no, comment ,co_ip,co_group ,co_step,co_indent) values (?,?,?,?,?,?,?,?,?,?,?)
	//parameter :  Board_DTO dto 
	//return : int
	/**
	
	 */
	public int create_re(P_Co_DTO dto ) {
		int result=-1;
		
		Connection conn = null; PreparedStatement pstmt =null;
		DBManager db = new DBManager();
		try {
			conn= db.getConnection();
			/////////////////////////////
			pstmt = conn.prepareStatement("insert into p_comments "
										+"(ws_no, p_no, wl_no, work_no, jyp_no, comment ,co_ip,co_group ,co_step,co_indent) "
										+"values (?,?,?,?,?,?,?,?,?,?) ");
			pstmt.setInt(1, dto.getWs_no());
			pstmt.setInt(2, dto.getP_no());
			pstmt.setInt(3, dto.getWl_no());
			pstmt.setInt(4, dto.getWork_no()); 
			pstmt.setInt(5, dto.getJyp_no());  
			pstmt.setString(6, dto.getComment()); 
			pstmt.setString(7, dto.getCo_ip());
			pstmt.setInt(8, dto.getCo_group());
			pstmt.setInt(9, dto.getCo_step());
			pstmt.setInt(10, dto.getCo_indent());
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();} catch (Exception e) {  e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();} catch (Exception e) {  e.printStackTrace(); }}
		}
		
		return result;
	}
	
	
	//댓글번호 글들(bindent) 1개씩  bstep 빼기
	//update  p_comments set co_step = co_step-1 where co_step>? and  co_step<?  
	public int update_reply(int prev, int curr ) {
		int result =-1;
		
		Connection conn = null; PreparedStatement pstmt =null; ResultSet rset =null;
		DBManager db = new DBManager();
		try {
			conn= db.getConnection();
			
			pstmt = conn.prepareStatement("update  p_comments set co_step = co_step-1 where co_step>? and  co_step<?  ");
			pstmt.setInt(1, prev);//이전 1
			pstmt.setInt(2, curr); //현재 999

			result = pstmt.executeUpdate();

			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();} catch (Exception e) {  e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();} catch (Exception e) {  e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();} catch (Exception e) {  e.printStackTrace(); }}
		}
		
		
		return result;
	}
	
	
	
}//end class 
