package com.jooyeon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jooyeon.dbmanager.DBManager;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.dto.PW_Member_DTO;
import com.jooyeon.dto.P_Member_DTO;
import com.jooyeon.dto.P_Work_DTO;
import com.jooyeon.dto.WS_Memeber_DTO;

public class JYP_Work_DAO {

	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/* ㅁ 업무리스트에서의 업무들
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
| worl_end     | date         | YES  |     | NULL              |                |
| work_alarm   | datetime     | YES  |     | NULL              |                |
| work_finish  | int(11)      | NO   |     | 1                 |                |
| wl_date      | datetime     | YES  |     | CURRENT_TIMESTAMP |                |
| wl_ip        | varchar(100) | NO   |     | NULL              |                |
+--------------+--------------+------+-----+-------------------+----------------+
12 rows in set (0.00 sec)

	 */
	
//  해당 프로젝트의 모든 업무 데이터 불러오기     -> 미완료 업무 나열 후 , 업무시작먼저해야될순 후 , work_no 나열순   
	// 	select*from p_work where jyp_no>0 and p_no = 50 order by FIELD(work_com, 'n') DESC,work_no DESC
	
	/*  ++jyp_name 추가 ver 
	select w.*, mem.jyp_name
	from p_work w join jyp_member mem
	on w.jyp_no = mem.jyp_no 
	where w.jyp_no>0 and w.p_no =46 order by  w.work_start asc , FIELD( w.work_com, 'n') DESC, w.work_no DESC
	
	####
	select w.*, mem.jyp_name
	from p_work w join jyp_member mem
	on w.jyp_no = mem.jyp_no 
	where w.jyp_no>0 and w.p_no =46 order by   FIELD( w.work_com, 'n') DESC, ISNULL(w.work_start) ASC ,w.work_start asc, w.work_date DESC
	
	ISNULL(해당 컬럼) ASC  //null값 정렬순서 

	*/
	/******
	 1. public ArrayList<JYP_Work_DTO> work_listAll()
	 2. return    : ArrayList<JYP_Work_DTO>   (전체이므로 배열) 
	 3. parameter : X 
	 ******/
	
	public ArrayList<P_Work_DTO> work_listAll(int p_no){
		ArrayList<P_Work_DTO> list = new ArrayList<P_Work_DTO>();
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		DBManager db = new DBManager();
		String sql ="select w.*, mem.jyp_name " + 
				"	from p_work w join jyp_member mem " + 
				"	on w.jyp_no = mem.jyp_no  " + 
				"	where w.jyp_no>0 and w.p_no =? order by FIELD( w.work_com, 'n') DESC, ISNULL(w.work_start) ASC ,w.work_start asc,  w.work_date DESC";
		try {
			conn = db.getConnection();											/////미완료 업무부터 나열 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_no);
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
										   rset.getString("work_com"),
										   rset.getString("jyp_name"),
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
	
	
	
	
	// 캘린더카테고리에서 업무관리용 (업무시작일 기준 오름차순), 워크스페이스의 전체 업무 
	//select*from p_work where ws_no = ? and jyp_no!=0 order by ISNULL(work_start) ASC ,work_start asc
	public ArrayList<P_Work_DTO> ws_listAll(int ws_no){
		ArrayList<P_Work_DTO> list = new ArrayList<P_Work_DTO>();
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		DBManager db = new DBManager();
		String sql ="select*from p_work where ws_no = ? and jyp_no!=0 order by ISNULL(work_start) ASC , ISNULL(work_end),work_start asc";
		try {
			conn = db.getConnection();											/////미완료 업무부터 나열 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ws_no);
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
										   rset.getString("work_com"),
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
	
	
	
	
	
	
	
	//////////////////////////////////////
	//fileList 용 
	//  해당 프로젝트의 모든 업무 데이터 불러오기   업데이트순 나열  파일첨부안함 업무 제외 
	/*
	select w.*, mem.jyp_name , wl.wl_name
	from p_work w join jyp_member mem join p_worklist wl
	where w.jyp_no = mem.jyp_no 
		and  w.wl_no = wl.wl_no
	and   w.jyp_no>0 and w.p_no =46 and w.work_file != 'no.jpg' order by w.work_date
	 */
	public ArrayList<P_Work_DTO> work_listAll2(int p_no){
		ArrayList<P_Work_DTO> list = new ArrayList<P_Work_DTO>();
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		DBManager db = new DBManager();
		String sql =" select w.*, mem.jyp_name , wl.wl_name " + 
				"	from p_work w join jyp_member mem join p_worklist wl " + 
				"	where w.jyp_no = mem.jyp_no  " + 
				"		and  w.wl_no = wl.wl_no " + 
				"	and   w.jyp_no>0 and w.p_no =? and w.work_file != 'no.jpg' order by w.work_date";
		try {
			conn = db.getConnection();											/////미완료 업무부터 나열 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_no);
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
										   rset.getString("work_com"),
										   rset.getString("jyp_name"),
										   rset.getString("wl_name"),
										   rset.getInt("jyp_no"),
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
	
	// 파일리스트 paging DAO////
	//전체 리스트의 개수 
	// select count(*) from p_work where work_file != 'no.jpg' and  p_no = 46  
	public int listCnt(int p_no) {
	int result = -1;
	
	Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
	DBManager db = new DBManager();
	
	try {
		conn = db.getConnection();												
		pstmt = conn.prepareStatement("select count(*) from p_work where work_file != 'no.jpg' and  p_no = ?  ");
		pstmt.setInt(1, p_no); 
		
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
	/*
	 select w.*, mem.jyp_name , wl.wl_name
	from p_work w join jyp_member mem join p_worklist wl
	where w.jyp_no = mem.jyp_no 
		and  w.wl_no = wl.wl_no
	and   w.jyp_no>0 and w.p_no =46 and w.work_file != 'no.jpg' order by w.work_date  limit 0,10
	 */
	public ArrayList<P_Work_DTO> list10(P_Work_DTO dto, int pstartNo){
		ArrayList<P_Work_DTO> list = new ArrayList<P_Work_DTO>();
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
		DBManager db = new DBManager();
		String sql=" select w.*, mem.jyp_name , wl.wl_name " + 
				"	from p_work w join jyp_member mem join p_worklist wl " + 
				"	where w.jyp_no = mem.jyp_no  " + 
				"		and  w.wl_no = wl.wl_no " + 
				"	and   w.jyp_no>0 and w.p_no =? and w.work_file != 'no.jpg' order by w.work_date  limit ?,10";
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getP_no()); 
			pstmt.setInt(2, pstartNo); 
			
			rset = pstmt.executeQuery(); //표
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
						   rset.getString("work_com"),
						   rset.getString("jyp_name"),
						   rset.getString("wl_name"),
						   rset.getInt("jyp_no"),
						   rset.getString("work_label")));
			} //while
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}	
		
		return list;
	}
	//////////////////////////////////////////////////////
	
	
	
	
	
	// 업무 작성 
		// 	insert into p_work (ws_no, p_no, wl_no, jyp_no, work_content, work_start, work_end, work_ip) values (?,?,?,?,?,?,?,?)
		/******
		 1. public int write(JYP_Work_DTO dto)
		 2. return    : int
		 3. parameter : JYP_Work_DTO  (?,?,?,?,? 각 다섯개의 파라미터를 던지는 것보다 dto하나로 사용하는게 편리)
		 ******/
		public int write(P_Work_DTO dto) {
			int result=-1;
			
			Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("insert into p_work (ws_no, p_no, wl_no, jyp_no, work_content, work_start, work_end, work_ip, work_file) values (?,?,?,?,?,?,?,?,?) ");
				pstmt.setInt(1, dto.getWs_no());
				pstmt.setInt(2, dto.getP_no());
				pstmt.setInt(3, dto.getWl_no());
				pstmt.setInt(4, dto.getJyp_no());
				pstmt.setString(5, dto.getWork_content());
				pstmt.setString(6, dto.getWork_start());
				pstmt.setString(7, dto.getWork_end());
				pstmt.setString(8, dto.getWork_ip());
				pstmt.setString(9, dto.getWork_file());
				result = pstmt.executeUpdate();
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null)  {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
			
			return result;
		}
		
		
		//방금 만든 work_no번호 뽑기 
		//
		public int work_no(P_Work_DTO dto) { //방금 만든 ws의 ws_no
			int result = -1;	

			Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();												
				pstmt = conn.prepareStatement("select work_no from p_work where jyp_no = ? order by work_no desc "); //본인이 방금 만든 워크스페이스 번호 뽑기
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
		
		
		
		
		//작성자 
		//select jyp_name from jyp_member where jyp_no = (select jyp_no from p_work where work_no = 170)
		public String writer(int work_no) { ///멤버 dao에 userinfo에서 꺼내오기 (수정하기)
			String result = null;
			Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("select jyp_name from jyp_member where jyp_no = (select jyp_no from p_work where work_no = ?)");
				pstmt.setInt(1, work_no);
				rset=pstmt.executeQuery();
			//	if(rset.next()) { result.setWork_writer( rset.getString("jyp_name"));}
				if(rset.next()) { result=  rset.getString("jyp_name"); }
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null)  {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
			
			return result;
		}
		
		
		
		//업무상세보기  +jyp_name +미리알림도 조회
		/*
		 select w.*, (select work_alarm from pw_member where jyp_no=6 and work_no=195) work_alarm  , mem.jyp_name
		from p_work w join jyp_member mem
		on w.jyp_no = mem.jyp_no
		where w.work_no=195 
		 
		 * */
		/******
		 1. public JYP_Work_DTO work_detail(int work_no)
		 2. return    : JYP_Work_DTO   (한줄이므로 ArrayList말고 dto로 받기)
		 3. parameter : int
		 ******/
		public P_Work_DTO work_detail(P_Work_DTO dto_w,PW_Member_DTO dto_pw ) {
			P_Work_DTO dto = null;
			
			Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			DBManager db = new DBManager();
			String sql = " select w.*, (select work_alarm from pw_member where jyp_no=? and work_no=? ) work_alarm  , mem.jyp_name " + 
					"		from p_work w join jyp_member mem " + 
					"		on w.jyp_no = mem.jyp_no " + 
					"		where w.work_no=? ";
			try {
				conn = db.getConnection();
		//		pstmt = conn.prepareStatement("select*from p_work where work_no=?");
				pstmt= conn.prepareStatement(sql);
				pstmt.setInt(1,dto_pw.getJyp_no());  
				pstmt.setInt(2, dto_pw.getWork_no());
				pstmt.setInt(3, dto_w.getWork_no());
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
//					int work_no, int ws_no, int p_no, int wl_no, int jyp_no, String work_content, String work_start,
//					String work_end, String work_alarm, int work_finish, String work_date, String work_ip
					String work_start="", work_end="";
					if( rset.getString("work_start")==null) {work_start="-";}else {work_start=rset.getString("work_start");}
					if( rset.getString("work_end")==null) {work_end="-";}else {work_end=rset.getString("work_end");}
					dto = new P_Work_DTO(  rset.getInt("work_no"),
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
										   rset.getString("work_alarm"),
										   rset.getString("work_com"),
										   rset.getString("jyp_name"),
										   rset.getString("work_label"));
				}//if
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null)  {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}		
			
			return dto;
		}
	
		//
		public P_Work_DTO work_detail2(int work_no) {
			P_Work_DTO dto = null;
			
			Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			DBManager db = new DBManager();
			String sql = "select* from p_work where work_no=?";
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("select*from p_work where work_no=?");
				pstmt.setInt(1, work_no);
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
//					int work_no, int ws_no, int p_no, int wl_no, int jyp_no, String work_content, String work_start,
//					String work_end, String work_alarm, int work_finish, String work_date, String work_ip
					String work_start="", work_end="";
					if( rset.getString("work_start")==null) {work_start="-";}else {work_start=rset.getString("work_start");}
					if( rset.getString("work_end")==null) {work_end="-";}else {work_end=rset.getString("work_end");}
					dto = new P_Work_DTO(  rset.getInt("work_no"),
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
										   rset.getString("work_com"),
										   rset.getString("work_label"));
				}//if
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null)  {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}		
			
			return dto;
		}
		
		
		///
		
		
		
		//업무수정하기
		// 	update p_work set work_title= ?, work_content = ?, work_start=? , work_end= ?  where work_no = ?
		/******
		 1. public int update(JYP_Work_DTO dto)
		 2. return    : int
		 3. parameter : JYP_Work_DTO
		 ******/
		public int update(P_Work_DTO dto) {
			int result = -1;
			
			Connection conn = null; PreparedStatement pstmt = null; 
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();												
				pstmt = conn.prepareStatement("update p_work set work_content = ?, work_start=? , work_end= ? , work_file=?, work_label=?  where work_no = ?");
				pstmt.setString(1, dto.getWork_content());
				pstmt.setString(2, dto.getWork_start());
				pstmt.setString(3, dto.getWork_end());
				pstmt.setString(4, dto.getWork_file());
				pstmt.setString(5, dto.getWork_label());
				pstmt.setInt(6, dto.getWork_no());
				result = pstmt.executeUpdate();
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
			
			return result;
		}
		
	///////////////////////삭제? 	
		//#############
		//업무 삭제(관리자모드) -> 로그인된 회원번호와 해당 게시글을 작성한 회원 번호가 일치하면 지우기  or ## 해당  프로젝트 관리자 권한이면 지울 수 있음 
		public int delete1(P_Work_DTO dto,P_Member_DTO dto2) {
			int p_access = -1;
			int result = -1;
			/*												
			 select p_access from p_member where p_no =? and jyp_no=?  //1. 해당 프로젝트에 대한 유저의 권한 구하기
			 // 권한이 1이면 (관리자 권한) 해당 업무 번호 삭제가능 
			 delete from p_work where work_no=?
			 */
			Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();
				
				pstmt=conn.prepareStatement("select p_access from p_member where p_no =? and jyp_no=?");
				pstmt.setInt(1, dto2.getP_no());
				pstmt.setInt(2, dto2.getJyp_no());
				rset=pstmt.executeQuery();
				if(rset.next()) {  p_access = rset.getInt("p_access");}
				
				if(p_access==1) { //관리자 권한이라면 
					pstmt.close();
					pstmt = conn.prepareStatement("delete from p_work where work_no=?");
					pstmt.setInt(1, dto.getWork_no());
					
					result=pstmt.executeUpdate();
				}
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
			
			return result;
		}	
	
	
		//업무 삭제(작성자모드) -> ##로그인된 회원번호와 해당 게시글을 작성한 회원 번호가 일치하면 지우기  or해당  프로젝트 관리자 권한이면 지울 수 있음 
		public int delete2(P_Work_DTO dto,int jyp_no) {
			int result = -1;
			/*
			 delete from p_work where work_no=? and jyp_no = ?
			 
			 */
			Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("delete from p_work where work_no=? and jyp_no = ?");
				pstmt.setInt(1, dto.getWork_no());
				pstmt.setInt(2, jyp_no);
				
				result=pstmt.executeUpdate();
				
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
			
			return result;
		}	
	

///////////////////////////////////////////
		
	
		
		//업무 삭제 
		public int work_delete(P_Work_DTO dto) {
			int result = -1;
			Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("delete from p_work where work_no=? ");
				pstmt.setInt(1, dto.getWork_no());
				
				result=pstmt.executeUpdate();
				
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
			
			return result;
		}	

		//업무리스트의 업무들 삭제
		//delete from p_work where wl_no=?
		// parameter : P_Work_DTO dto
		// return : int 
		public int wl_delete( P_Work_DTO dto) {
			int result =-1;
			
			Connection conn = null; PreparedStatement pstmt = null; 
			DBManager db = new DBManager();

			try {
				conn = db.getConnection();
				//해당 업무리스트이 작성자인지 조회 
				pstmt=conn.prepareStatement("delete from p_work where wl_no=?");
				pstmt.setInt(1, dto.getWl_no());
				
				result=pstmt.executeUpdate();
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
			
			return result;
		}
		
		
		
		///업무 배정인원인지 확인
		// select count(*) from pw_member where work_no=? and jyp_no=? 
		public int work_mem( P_Work_DTO dto) {
			int result =-1;
			
			Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			DBManager db = new DBManager();

			try {
				conn = db.getConnection();
				//해당 업무리스트이 작성자인지 조회 
				pstmt=conn.prepareStatement(" select count(*) from pw_member where work_no=? and jyp_no=? ");
				pstmt.setInt(1, dto.getWork_no());
				pstmt.setInt(2, dto.getJyp_no());
				rset=pstmt.executeQuery();
				if(rset.next()) {result = rset.getInt(1);}
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
			
			return result;
		}
		
		
		
		//
		 public JsonArray workCheck(P_Work_DTO dto){
			 	//1. 배열 만들기 []
				JsonArray list = new JsonArray();
			 
				DBManager db = new DBManager();
				Connection conn = null; PreparedStatement pstmt =null; ResultSet rset=null;
			
				try {
					conn = db.getConnection();
					pstmt = conn.prepareStatement("select*from p_work where p_no=?");
					pstmt.setInt(1, dto.getP_no());
					rset = pstmt.executeQuery(); //표
					
					//{"menu" :[] }
					/*
					 set.getInt("work_no"),
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
										   rset.getString("work_com")) );
			}//while
					 */
					while(rset.next()) { //줄 
						JsonObject job = new JsonObject();
						job.addProperty("work_com", rset.getString("work_com"));
						job.addProperty("work_no", rset.getInt("work_no"));

						list.add(job);
					}//end while
					
//					Gson gson = new Gson();
//					JsonObject inviList = new JsonObject();
//					inviList.add("list", list);
					//
							
					
				} catch (Exception e) {  e.printStackTrace(); } 
				finally {
					if(rset!=null) {try{rset.close();}catch (Exception e) {  e.printStackTrace(); } }
					if(pstmt!=null) {try{pstmt.close();}catch (Exception e) {  e.printStackTrace(); } }
					if(conn!=null) {try{conn.close();}catch (Exception e) {  e.printStackTrace(); } }
				}
			 
			 return list;
		 }
		 
		 
		 
		 //업무완료 y
		 public int checked(P_Work_DTO dto) {
			int result =-1;
			
			Connection conn = null; PreparedStatement pstmt = null; 
			DBManager db = new DBManager();

			try {
				conn = db.getConnection();
				//해당 업무리스트이 작성자인지 조회 
				pstmt=conn.prepareStatement("update p_work set work_com='y' where work_no =?");
				pstmt.setInt(1, dto.getWork_no());
				
				result=pstmt.executeUpdate();
				
			} catch (Exception e) {  e.printStackTrace(); }
			finally {
				if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
				if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
			}
			
			return result;
		 }
		 
		 
		 //업무미완료n
		 public int unChecked(P_Work_DTO dto) {
				int result =-1;
				
				Connection conn = null; PreparedStatement pstmt = null; 
				DBManager db = new DBManager();

				try {
					conn = db.getConnection();
					//해당 업무리스트이 작성자인지 조회 
					pstmt=conn.prepareStatement("update p_work set work_com='n' where work_no =?");
					pstmt.setInt(1, dto.getWork_no());
					
					result=pstmt.executeUpdate();
					
				} catch (Exception e) {  e.printStackTrace(); }
				finally {
					if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
					if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
				}
				
				return result;
			 }
		 
		 
		 
		
}//end class
