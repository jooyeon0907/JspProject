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
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.dto.WS_Memeber_DTO;

public class JYP_WS_DAO {
	
	//워크스페이스 생성
	// insert into jyp_workspace (ws_name, jyp_no ,ws_ip ) values (?,?,?)
	public int ws_add(JYP_WS_DTO dto) {
		int result =-1;
		
		Connection conn = null; PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement("insert into jyp_workspace (ws_name, jyp_no ,ws_ip ) values (?,?,?)");
			pstmt.setString(1, dto.getWs_name()); //request에서 받은 값 저장
			pstmt.setInt(2, dto.getJyp_no()); //session에 저장된 jyp_no 저장
			pstmt.setString(3, dto.getWs_ip()); ///session에 저장된 jyp_ip 저장
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		return result;
	}
	
	
	public int ws_no(JYP_WS_DTO dto) { //방금 만든 ws의 ws_no
		int result = -1;	

		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement("select ws_no from jyp_workspace where jyp_no = ? order by ws_no desc "); //본인이 방금 만든 워크스페이스 번호 뽑기
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
	
	

	
	/*??삭제??*/
	public ArrayList<JYP_WS_DTO> ws_name(int jyp_no) { 
		ArrayList<JYP_WS_DTO> list = new ArrayList<JYP_WS_DTO>();

		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement("select*from jyp_workspace where ws_no = (select ws_no from ws_member where jyp_no = ? order by ws_no asc) ");
			pstmt.setInt(1, jyp_no); //request에서 받은 값 저장

			rset = pstmt.executeQuery();
			//int ws_no, String ws_name, int jyp_no, String ws_ip, String ws_date, String ws_del,
			//String ws_delDday)
			if(rset.next()) { list.add(new JYP_WS_DTO( rset.getInt("ws_no"),
													rset.getString("ws_name"),
													rset.getInt("jyp_no"),
													rset.getString("ws_ip"),
													rset.getString("ws_date"),
													rset.getString("ws_del"),
													rset.getString("ws_delDday")));   }
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		return list;
	}
	
	//////워크스페이스리스트 paging DAO/////////////////
	// 전체 리스트의 개수 
	// select count(*) from ws_member where jyp_no =?
	public int listCnt(WS_Memeber_DTO dto) {
		int result = -1;
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement("select count(*) from ws_member where jyp_no =?");
			pstmt.setInt(1, dto.getJyp_no()); 
			
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
	
	
	//페이지 시작번호 (10의 단위) (먼저 가입한 워크스페이스 순으로) 
	public ArrayList<JYP_WS_DTO> list10(WS_Memeber_DTO dto, int pstartNo) {
		ArrayList<JYP_WS_DTO> list = new ArrayList<JYP_WS_DTO>();

		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
		DBManager db = new DBManager();
		String sql ="select ws.*, wm.ws_Mdate  " + 
					"from jyp_workspace ws join ws_member wm " + 
					"on ws.ws_no = wm.ws_no " + 
					"where wm.jyp_no=? " + 
					"group by ws.ws_no " + 
					"having ws.ws_no in (select ws_no from ws_member )   " + 
					"order by wm.ws_Mdate asc limit ?,10 " ;
				
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getJyp_no()); //request에서 받은 값 저장
			pstmt.setInt(2, pstartNo); 
			
			rset = pstmt.executeQuery();
			while(rset.next()) { list.add(new JYP_WS_DTO( rset.getInt("ws_no"),
													rset.getString("ws_name"),
													rset.getInt("jyp_no"),
													rset.getString("ws_ip"),
													rset.getString("ws_date"),
													rset.getString("ws_del"),
													rset.getString("ws_delDday")));   } //end while
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		
		return list;
	}
	//////end 워크스페이스리스트 paging DAO/////////////////
	
	
	/**/
	//입장할 워크스페이스 번호,이름  (내가 속해있는 워크스페이스 번호)  - 작은 번호 받아서 그 워크스페이스로 입장시키게 하기 
	
	//내가 속한 워크스페이스 리스트 정보 
	//파라 : JYP_WS_DTO dto
	// 리턴 : ArrayList<JYP_WS_DTO> 
	public ArrayList<JYP_WS_DTO> ws_list(JYP_Member_DTO dto) {
		ArrayList<JYP_WS_DTO> list = new ArrayList<JYP_WS_DTO>();

		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement(" select*from jyp_workspace where  ws_no in (select ws_no from ws_member where jyp_no=?); ");
			pstmt.setInt(1, dto.getJyp_no()); //request에서 받은 값 저장

			rset = pstmt.executeQuery();
			//int ws_no, String ws_name, int jyp_no, String ws_ip, String ws_date, String ws_del,
			//String ws_delDday)
			while(rset.next()) { list.add(new JYP_WS_DTO( rset.getInt("ws_no"),
													rset.getString("ws_name"),
													rset.getInt("jyp_no"),
													rset.getString("ws_ip"),
													rset.getString("ws_date"),
													rset.getString("ws_del"),
													rset.getString("ws_delDday")));   }
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		
		return list;
	}
	
	public JsonArray ws_list_ajax(JYP_Member_DTO dto) {
	//	ArrayList<JYP_WS_DTO> list2 = new ArrayList<JYP_WS_DTO>();
		JsonArray list = new JsonArray();
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement(" select*from jyp_workspace where  ws_no in (select ws_no from ws_member where jyp_no=?); ");
			pstmt.setInt(1, dto.getJyp_no()); //request에서 받은 값 저장

			rset = pstmt.executeQuery();
			//int ws_no, String ws_name, int jyp_no, String ws_ip, String ws_date, String ws_del,
			//String ws_delDday)
			//{ "list" : [] }
			// 1. 배열 만들기
			
			while(rset.next()) { 
				JsonObject job = new JsonObject();
				job.addProperty("ws_no", rset.getInt("ws_no"));
				job.addProperty("ws_name", rset.getString("ws_name"));
				
				list.add(job);
			 }//end while
			
			//2. gson { "list" : [] }
		//	Gson gson = new Gson();
		//	JsonObject ws_list = new JsonObject();
		//	ws_list.add("ws_list", list);
		//	out.println(gson.toJson(ws_list));	
		//	gson.toJson(ws_list);
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		
		return list;
	}
	//워크스페이스 정보 
	// select*from jyp_workspace where ws_no =? 
	//파라 : JYP_WS_DTO dto
	// 리턴 : JYP_WS_DTO 
	public JYP_WS_DTO ws_info(JYP_WS_DTO dto) {
		JYP_WS_DTO result = new JYP_WS_DTO();
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement("select*from jyp_workspace where ws_no =?  ");
			pstmt.setInt(1, dto.getWs_no()); //request에서 받은 값 저장

			rset = pstmt.executeQuery();
			//int ws_no, String ws_name, int jyp_no, String ws_ip, String ws_date, String ws_del,
			//String ws_delDday)
			while(rset.next()) { result =new JYP_WS_DTO( rset.getInt("ws_no"),
													rset.getString("ws_name"),
													rset.getInt("jyp_no"),
													rset.getString("ws_ip"),
													rset.getString("ws_date"),
													rset.getString("ws_del"),
													rset.getString("ws_delDday"),
													rset.getString("address"));   }
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		return result;
	}
	
	

	
	
	
	
	//워크스페이스 삭제 - 관리자 권한이고, 이메일과 패스가 맞다면 현재의 워크스페이스 번호삭제
	/*dao - 1.워크스페이스 관리자 권한 , 2. 권리자의 이메일과 패스가 맞다면 해당 워크스페이스 삭제  (하위 카테고리들의 정보들도 다 삭제 )
		delete ws,wm,pro,pm,wl,work,pwm 
		from jyp_workspace ws 
		inner join ws_member wm
		inner join jyp_project pro
		inner join p_member pm
		inner join p_worklist wl
		inner join p_work work
		inner join pw_member pwm
		inner join jyp_member jm 
		where ws.ws_no=10
		and ws.jyp_no = 4 and jm.jyp_email='44@44' and jm.jyp_pass='44';
		  */
	//parameter : JYP_WS_DTO dto_ws , JYP_Member_DTO dto_mem
	// return : int
	public int ws_delete(JYP_WS_DTO dto) {
		int result =-1;
		
		Connection conn = null; PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		String sql="delete jyp_workspace,ws_member,jyp_project,p_member,p_worklist,p_work,pw_member \r\n" + 
					"from jyp_workspace\r\n" + 
					"join ws_member \r\n" + 
					"join jyp_project \r\n" + 
					"join p_member\r\n" + 
					"join p_worklist \r\n" + 
					"join p_work \r\n" + 
					"join pw_member \r\n" + 
					"where jyp_workspace.ws_no = ws_member.ws_no\r\n" + 
					"and jyp_workspace.ws_no = jyp_project.ws_no\r\n" + 
					"and jyp_workspace.ws_no = p_member.ws_no\r\n" + 
					"and jyp_workspace.ws_no = p_worklist.ws_no\r\n" + 
					"and jyp_workspace.ws_no = p_work.ws_no\r\n" + 
					"and jyp_workspace.ws_no = pw_member.ws_no\r\n" + 
					"and jyp_workspace.ws_no= ?";
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getWs_no());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}		
		
		return result;
	}
	

	
	
	///워크스페이스 이름 변경  (유저가 관리자 권한이라면 ) 
	// update jyp_workspace set ws_name = ? where jyp_no = ? (유저번호)
	// prameter : JYP_WS_DTO dto1 ,JYP_Member_DTO dto2 
	// return : int 
	/*
	 유저가 관리자권한이라면 이름변경할 수 있음  
	 유저의 번호(4 )
	 11번 워크스페이스의 관리자 (select jyp_no from jyp_workspace where ws_no=11 )
	
	  유저가 워크스페이스의 관리자권한인지 체크
	 -> 유저번호는 session불러오고 
	 -> 해당 워크스페이스 번호도 session에 불러옴   두 번호가 같으면 
	-> 해당 워크스페이스 이름 변경										//ws의 		//유저의
	 update jyp_workspace set ws_name = ? where ws_no = ? and  jyp_no = ? and jyp_no=?   ;  
	 
	 */
	public int ws_editName(JYP_WS_DTO dto1 ,JYP_Member_DTO dto2 ) {
		int result = -1;

		Connection conn = null; PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement(" update jyp_workspace set ws_name = ? where ws_no = ? and  jyp_no = ? and jyp_no=?  ");
			pstmt.setString(1, dto1.getWs_name());
			pstmt.setInt(2, dto1.getWs_no());	
			pstmt.setInt(3, dto1.getJyp_no());
			pstmt.setInt(4, dto2.getJyp_no());	
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		return result; 
	}
	
	
	///회사주소 등록하기
	// update jyp_workspace set address = ? where ws_no = ? 
	public int address(JYP_WS_DTO dto) {
		int result = -1;

		Connection conn = null; PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement("update jyp_workspace set address = ? where ws_no = ? ");
			pstmt.setString(1, dto.getAddress());
			pstmt.setInt(2, dto.getWs_no());	
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		return result; 
	}
	
	
	
	
	
}//end class
