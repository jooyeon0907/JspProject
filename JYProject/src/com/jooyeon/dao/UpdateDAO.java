package com.jooyeon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jooyeon.dbmanager.DBManager;
import com.jooyeon.dto.JYP_Project_DTO;
import com.jooyeon.dto.P_Work_DTO;

public class UpdateDAO {

	
	// 미리알림
	// pw_member에 미리알림 설정한 레코드의 디데이, 정보 출력(디데이, p_work 모든정보, 미리알림날짜, 업무리스트이름, 프로젝트 이름) 
	/*
	 select  DATEDIFF ( date(pw.work_alarm), now() ) as Dday  ,w.*, pw.work_alarm, wl.wl_name , pro.p_name
from p_work w join pw_member pw join p_worklist wl join jyp_project pro
where pw.work_no = w.work_no
 and pw.wl_no = wl.wl_no
 and pw.p_no = pro.p_no 
 and pw.work_alarm is not null and pw.ws_no = 1 
 and pw.work_no in (select work_no from pw_member where jyp_no=4)  ;
 
 
 
 
 select  DATEDIFF ( date(pw.work_alarm), now() ) as Dday  ,w.*, pw.work_alarm, wl.wl_name , pro.p_name
	from p_work w join pw_member pw join p_worklist wl join jyp_project pro
	where pw.work_no = w.work_no
	 and pw.wl_no = wl.wl_no
	 and pw.p_no = pro.p_no 
	 and pw.work_no in (select work_no from pw_member where jyp_no=6 and ws_no = 1 and work_alarm is not null )  ;
	 */
	// parameter : P_Work_DTO dto
	// return : ArrayList<P_Work_DTO>
	public ArrayList<P_Work_DTO> workAlarm(P_Work_DTO dto){
		ArrayList<P_Work_DTO> list = new ArrayList<P_Work_DTO>();
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		DBManager db = new DBManager();
		String sql = "select  DATEDIFF ( date(pw.work_alarm), now() ) as dday  ,w.*, pw.work_alarm, wl.wl_name , pro.p_name " + 
				"from p_work w join pw_member pw join p_worklist wl join jyp_project pro " + 
				"where pw.work_no = w.work_no " + 
				" and pw.wl_no = wl.wl_no " + 
				" and pw.p_no = pro.p_no " + 
				" and pw.work_alarm is not null and pw.ws_no = ?  " + 
				" and pw.work_no in (select work_no from pw_member where jyp_no=?)  ";
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getWs_no());
			pstmt.setInt(2, dto.getJyp_no());
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new P_Work_DTO( rset.getInt("work_no"),
						   rset.getInt("ws_no"),
						   rset.getInt("p_no"),
						   rset.getInt("wl_no"),
						   rset.getInt("jyp_no"),
						   rset.getString("work_content"), 
						   rset.getInt("work_finish"), 
						   rset.getString("work_date"),
						   rset.getString("work_ip"),
						   rset.getString("work_file"),
						   rset.getString("work_alarm"),
						   rset.getString("p_name"),
						   rset.getString("wl_name"),
						   rset.getString("dday"),
						   rset.getString("work_label") ));
			}//while
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null)  {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		return list;
	}
	
	
	
	
	
	//시작일/마감일 ////
	//업무에서 시작일/마감일 
	// 시작일이나 마감일 
	/*
	 	select  DATEDIFF ( date(w.work_start), now() ) as start_dday,DATEDIFF ( date(w.work_end), now() ) as end_dday ,w.* , wl.wl_name , pro.p_name
		from p_work w  join p_worklist wl join jyp_project pro
		where w.wl_no = wl.wl_no
		 and  w.p_no = pro.p_no 
		 and  w.ws_no=1    
	 */
	//is not null 안해주고 view단에서 조건문으로 거르기 
	// parameter : P_Work_DTO dto
	// return : ArrayList<P_Work_DTO>
	public ArrayList<P_Work_DTO> workDate(P_Work_DTO dto){
		ArrayList<P_Work_DTO> list = new ArrayList<P_Work_DTO>();
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		DBManager db = new DBManager();
		String sql = "select  DATEDIFF ( date(w.work_start), now() ) as start_dday,DATEDIFF ( date(w.work_end), now() ) as end_dday ,w.* , wl.wl_name , pro.p_name " + 
				"	from p_work w  join p_worklist wl join jyp_project pro " + 
				"	where w.wl_no = wl.wl_no " + 
				"	 and  w.p_no = pro.p_no  " + 
				"	 and  w.ws_no=?"+
				"	 and w.work_no in (select work_no from pw_member where jyp_no=? )";
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getWs_no());
			pstmt.setInt(2, dto.getJyp_no());
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new P_Work_DTO( rset.getInt("work_no"),
										   rset.getInt("ws_no"),
										   rset.getInt("p_no"),
										   rset.getInt("wl_no"),
										   rset.getInt("jyp_no"),
										   rset.getString("work_content"), 
										   rset.getString("work_start"),
										   rset.getString("work_end"), 
										   rset.getInt("work_finish"), 
										   rset.getString("work_date"),
										   rset.getString("work_ip"),
										   rset.getString("work_file"),
										   rset.getString("p_name"),
										   rset.getString("wl_name"),
										   rset.getString("start_dday"),
										   rset.getString("end_dday"),
										   rset.getString("work_label")));
			}//while
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null)  {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		return list;
	}
	
	
	
	
	//프로젝트에서 시작일/마감일
	// 프로젝트 시작일/마감일 디데이도 같이 출력해서 view에서 판단하여 정보 쓰기 
	/*
	 select *,DATEDIFF ( date(p_start), now() ) as start_dday, DATEDIFF ( date(p_end), now() ) as end_dday 
 	from jyp_project
 	where  ws_no=1 and p_no in (select p_no from p_member where jyp_no=4)
	 */
	// parameter : JYP_Project_DTO dto
	// return : ArrayList<JYP_Project_DTO>
	public ArrayList<JYP_Project_DTO> p_list(JYP_Project_DTO dto){
		 ArrayList<JYP_Project_DTO> list = new  ArrayList<JYP_Project_DTO>();
		 
		 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
			DBManager db = new DBManager();
			String sql ="select *,DATEDIFF ( date(p_start), now() ) as start_dday, DATEDIFF ( date(p_end), now() ) as end_dday " + 
					" 	from jyp_project " + 
					" 	where  ws_no=? and p_no in (select p_no from p_member where jyp_no=?) ";
			try {
				conn = db.getConnection();												
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,dto.getWs_no());
				pstmt.setInt(2, dto.getJyp_no());
				rset = pstmt.executeQuery(); //표
				while(rset.next()) {//줄
					list.add(
							new JYP_Project_DTO(
									rset.getInt("p_no"),
									rset.getInt("ws_no"),
									rset.getString("p_name"),
									rset.getString("p_info"),
									rset.getInt("p_status"),
									rset.getString("p_start"), 
									rset.getString("p_end"),
									rset.getString("p_complete"),
									rset.getString("p_public"),
									rset.getString("p_date"),
									rset.getString("p_ip"), 
									rset.getInt("jyp_no"),
									rset.getString("start_dday"),
									rset.getString("end_dday") ));
					
				}//while
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}		 
		 return list;
	}
	
	
	
	
	
	
} //end class
