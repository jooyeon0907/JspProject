package com.jooyeon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jooyeon.dbmanager.DBManager;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.dto.PW_Member_DTO;
import com.jooyeon.dto.P_Member_DTO;
import com.jooyeon.dto.WS_Invitation_DTO;
import com.jooyeon.dto.WS_Memeber_DTO;

public class WS_Invitation_DAO {
	
	//jyproject 회원검색 (ajax)
	 public String userinfo(JYP_Member_DTO dto) {
		 String result ="";
		 	
		 
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt =null; ResultSet rset=null;
		
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("select*from jyp_member where jyp_email =?");
				pstmt.setString(1, dto.getJyp_email());
				rset = pstmt.executeQuery(); //표

				
				if(rset.next()) { //줄
					result+= "{ \"jyp_email\" : \""+rset.getString("jyp_email")+"\" , \"jyp_name\":\""+rset.getString("jyp_name")+"\" }";
				}
				
				/*if(rset.next()) { //줄 
					String jyp_email = rset.getString("jyp_email");
					result+= "<p>EMAIL : "+jyp_email+"</p>"
							+ "<p>NAME : " +rset.getInt("jyp_name")+"</p>";
						//	+"<input type=\"button\" id=\"ws_invite\" value=\"초대하기\"  class=\"btn btn-info\" />";
						//	+"<p><a href='${pageContext.request.contextPath}/ws_Invitation.mem?jyp_email="+jyp_email+"' class='btn btn-info' >초대하기</a></p>";
				
				}*/
				
				
			} catch (Exception e) {  e.printStackTrace(); } 
			finally {
				if(rset!=null) {try{rset.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(pstmt!=null) {try{pstmt.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(conn!=null) {try{conn.close();}catch (Exception e) {  e.printStackTrace(); } }
			}
		 return result;
	 }
	

	 
	 
	 //ws초대리스트 등록
	 // insert into ws_invitation (send_no ,send_ip , get_no, get_ip, get_ip, ws_no, ws_name ) values (?,?,?,?,?,?,?,?)
	 // parameter : WS_Invitation_DTO dto
	 // return : int
	 public int ws_invitation(JYP_Member_DTO dto_send,JYP_Member_DTO dto_get,JYP_WS_DTO dto_ws ) {
		 int result = -1;
		 
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt =null;
		
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("insert into ws_invitation (send_email ,send_ip , get_email, get_ip,  ws_no, ws_name ) values (?,?,?,?,?,?) ");
				pstmt.setString(1, dto_send.getJyp_email());
				pstmt.setString(2, dto_send.getJyp_ip());
				pstmt.setString(3, dto_get.getJyp_email());
				pstmt.setString(4, dto_get.getJyp_ip());
				pstmt.setInt(5, dto_ws.getWs_no());
				pstmt.setString(6, dto_ws.getWs_name());
				
				result = pstmt.executeUpdate();
			} catch (Exception e) {  e.printStackTrace(); } 
			finally {
				if(pstmt!=null) {try{pstmt.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(conn!=null) {try{conn.close();}catch (Exception e) {  e.printStackTrace(); } }
			}
			
		 return result;
	 }
	 
	 
	 //등록되어 있는 유저인지 체크
	 // select count(*) from ws_invitation where ws_no=? and get_email=? (초대하려는 jyp_no 넣기 ) 
	// select count(*) from ws_invitation where ws_no=? and get_email=? (초대하려는 이메일 넣기 ) 
	// parameter : JYP_WS_DTO dto_ws, JYP_Member_DTO dto_mem 
	// return : int  
	 public int check(WS_Invitation_DTO dto_invi ) {
		 int result = -1;
		 
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt =null; ResultSet rset=null;
		
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("select count(*) from ws_invitation where ws_no=? and get_email=? and ass_status ='n' ");
				pstmt.setInt(1, dto_invi.getWs_no());
				pstmt.setString(2, dto_invi.getGet_email());

				rset=pstmt.executeQuery();// 표
				if(rset.next()) { result = rset.getInt(1); }
				
			} catch (Exception e) {  e.printStackTrace(); } 
			finally {
				if(rset!=null) {try{rset.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(pstmt!=null) {try{pstmt.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(conn!=null) {try{conn.close();}catch (Exception e) {  e.printStackTrace(); } }
			}
			
		 return result;
	 }
	 
	 
	 
	 
	 //유저가 초대리스트에 등록되어있는지 확인하기 (수락안한상태) 
	 // select*from ws_invitation where get_no =?   (유저의 jyp_no 넣기 ) 
	 // parameter :  JYP_Member_DTO dto 
	 // return :  ArrayList<WS_Invitation_DTO> list
	 public ArrayList<WS_Invitation_DTO> ws_inviList(JYP_Member_DTO dto){
		 ArrayList<WS_Invitation_DTO> list = new ArrayList<WS_Invitation_DTO> ();
		 
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt =null; ResultSet rset=null;
		
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("select*from ws_invitation where get_email =? and ass_status='n'");
				pstmt.setString(1,dto.getJyp_email());
				rset = pstmt.executeQuery(); //표

				while(rset.next()) { //줄 
					//int invi_no,int send_no, String send_ip, int get_no, String get_ip, int ws_no, String ws_name, String acc_date, String ass_satatus
					list.add(new WS_Invitation_DTO(	rset.getInt("invi_no"),
													rset.getString("send_email"),
													rset.getString("send_ip"),
													rset.getString("get_email"),
													rset.getString("get_ip"),
													rset.getInt("ws_no"),
													rset.getString("ws_name"),
													rset.getString("acc_date"),
													rset.getString("ass_status") ));
				}
				
			} catch (Exception e) {  e.printStackTrace(); } 
			finally {
				if(rset!=null) {try{rset.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(pstmt!=null) {try{pstmt.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(conn!=null) {try{conn.close();}catch (Exception e) {  e.printStackTrace(); } }
			}
		 
		 return list;
	 }
	 
	 
	 
	 
	 
	 ///초대받은 초대리스트 Paging DAO///////////////////////////////////////
	 //초대받은 전체 리스트의 개수  (초대받은 사람의 기준으로 해야됨)
	 // select count(*) from ws_invitation where get_email=?
	public int listCnt(JYP_Member_DTO dto) {
		int result = -1;
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement("select count(*) from ws_invitation where get_email=?");
			pstmt.setString(1, dto.getJyp_email()); 
			
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
	 
	//페이지 시작번호 (10의 단위) (먼저 초대받은 날짜 순으) 
	 //초대리스트 정보 다 뽑기 (ajax)
	 public ArrayList<WS_Invitation_DTO> list10(JYP_Member_DTO dto, int pstartNo){
		 	ArrayList<WS_Invitation_DTO> list = new ArrayList<WS_Invitation_DTO>();
		 
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt =null; ResultSet rset=null;
			String sql = "select * " + 
						"from ws_invitation " + 
						"where get_email in (select jyp_email from jyp_member where jyp_email = ?)  " + 
						"and ass_status='n' order by acc_date asc limit ?,10";
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getJyp_email());
				pstmt.setInt(2, pstartNo);
				rset = pstmt.executeQuery(); //표
				
				while(rset.next()) { //줄 
					//int invi_no,int send_no, String send_ip, int get_no, String get_ip, int ws_no, String ws_name, String acc_date, String ass_satatus
					list.add(new WS_Invitation_DTO(	rset.getInt("invi_no"),
													rset.getString("send_email"),
													rset.getString("send_ip"),
													rset.getString("get_email"),
													rset.getString("get_ip"),
													rset.getInt("ws_no"),
													rset.getString("ws_name"),
													rset.getString("acc_date"),
													rset.getString("ass_status") ));
				}//end whlie						
				
			} catch (Exception e) {  e.printStackTrace(); } 
			finally {
				if(rset!=null) {try{rset.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(pstmt!=null) {try{pstmt.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(conn!=null) {try{conn.close();}catch (Exception e) {  e.printStackTrace(); } }
			}
		 
		 return list;
	 }
	
	
	
	///end 초대받은 초대리스트 Paging DAO///////////////////////////////////////
	 
	 
	 ////주소록 초대리스트에서 페이징////////
	 //select*from ws_invitation where ws_no =1  limit 0,10
	 public ArrayList<WS_Invitation_DTO> invi_list10(WS_Invitation_DTO dto, int pstartNo){
		 	ArrayList<WS_Invitation_DTO> list = new ArrayList<WS_Invitation_DTO>();
		 
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt =null; ResultSet rset=null;
			String sql = "select*from ws_invitation where ws_no =?  limit ?,10";
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getWs_no());
				pstmt.setInt(2, pstartNo);
				rset = pstmt.executeQuery(); //표
				
				while(rset.next()) { //줄 
					//int invi_no,int send_no, String send_ip, int get_no, String get_ip, int ws_no, String ws_name, String acc_date, String ass_satatus
					list.add(new WS_Invitation_DTO(	rset.getInt("invi_no"),
													rset.getString("send_email"),
													rset.getString("send_ip"),
													rset.getString("get_email"),
													rset.getString("get_ip"),
													rset.getInt("ws_no"),
													rset.getString("ws_name"),
													rset.getString("acc_date"),
													rset.getString("ass_status") ));
				}//end whlie						
				
			} catch (Exception e) {  e.printStackTrace(); } 
			finally {
				if(rset!=null) {try{rset.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(pstmt!=null) {try{pstmt.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(conn!=null) {try{conn.close();}catch (Exception e) {  e.printStackTrace(); } }
			}
		 
		 return list;
	 }
	
	 
	 //워크스페이스  전체 초대 리스트의 개수 
	 // select count(*) from ws_invitation where get_email=?
	public int listCnt2(WS_Invitation_DTO dto) {
		int result = -1;
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset =null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();												
			pstmt = conn.prepareStatement("select count(*) from ws_invitation where ws_no=?");
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
	
	 ////end 주소록 초대리스트에서 페이징/////////////////
	 
	 
	 
	 //초대리스트 정보 다 뽑기 (ajax)
	 public JsonArray ws_inviListAll(JYP_WS_DTO dto){
		 	//1. 배열 만들기 []
			JsonArray list = new JsonArray();
		 
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt =null; ResultSet rset=null;
		
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("select*from ws_invitation where ws_no=?");
				pstmt.setInt(1, dto.getWs_no());
				rset = pstmt.executeQuery(); //표
				
				//{"menu" :[] }
				
				while(rset.next()) { //줄 
					JsonObject job = new JsonObject();
					job.addProperty("invi_no", rset.getInt("invi_no"));
					job.addProperty("send_email", rset.getString("send_email"));
					job.addProperty("send_ip", rset.getString("send_ip"));
					job.addProperty("get_email", rset.getString("get_email"));
					job.addProperty("get_ip", rset.getString("get_ip"));
					job.addProperty("ws_no", rset.getInt("ws_no"));
					job.addProperty("ws_name", rset.getString("ws_name"));
					job.addProperty("acc_date", rset.getString("acc_date"));
					job.addProperty("ass_status", rset.getString("ass_status"));

					list.add(job);
				}//end while
				
//				Gson gson = new Gson();
//				JsonObject inviList = new JsonObject();
//				inviList.add("list", list);
				//
						
				
			} catch (Exception e) {  e.printStackTrace(); } 
			finally {
				if(rset!=null) {try{rset.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(pstmt!=null) {try{pstmt.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(conn!=null) {try{conn.close();}catch (Exception e) {  e.printStackTrace(); } }
			}
		 
		 return list;
	 }
	 
	 
	 
	 //초대리스트의 정보 뽑기 (페이징용)
	 // select*from ws_invitation where invi_no =?
	 // parameter :  JYP_Member_DTO dto 
	 // return :  ArrayList<WS_Invitation_DTO> list
	 public WS_Invitation_DTO ws_inviInfo(WS_Invitation_DTO dto){
		 WS_Invitation_DTO result = new WS_Invitation_DTO();
		 
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt =null; ResultSet rset=null;
		
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("select*from ws_invitation where invi_no =? ");
				pstmt.setInt(1,dto.getInvi_no());
				rset = pstmt.executeQuery(); //표

				while(rset.next()) { //줄 
					//int invi_no,int send_no, String send_ip, int get_no, String get_ip, int ws_no, String ws_name, String acc_date, String ass_satatus
					result = new WS_Invitation_DTO(	rset.getInt("invi_no"),
													rset.getString("send_email"),
													rset.getString("send_ip"),
													rset.getString("get_email"),
													rset.getString("get_ip"),
													rset.getInt("ws_no"),
													rset.getString("ws_name"),
													rset.getString("acc_date"),
													rset.getString("ass_satatus") );
				}
				
			} catch (Exception e) {  e.printStackTrace(); } 
			finally {
				if(rset!=null) {try{rset.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(pstmt!=null) {try{pstmt.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(conn!=null) {try{conn.close();}catch (Exception e) {  e.printStackTrace(); } }
			}
		 
		 return result;
	 }
	 	 
	 
	 
	 //초대리스트 삭제(바로삭제) 
	 // delete from ws_invitation where invi_no = ? 
	 // parameter  : WS_Invitation_DTO dto
	 // return : int 
	 public int ws_inviDelete(WS_Invitation_DTO dto) {
		 int result = -1;
		 
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt =null;
		
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("delete from ws_invitation where invi_no = ? ");
				pstmt.setInt(1, dto.getInvi_no());
				
				result = pstmt.executeUpdate();
			} catch (Exception e) {  e.printStackTrace(); } 
			finally {
				if(pstmt!=null) {try{pstmt.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(conn!=null) {try{conn.close();}catch (Exception e) {  e.printStackTrace(); } }
			}
			
		 return result;
	 } 
	
	 
	 
	 // 초대보낸 날짜로부터 7일 지난 데이터 지우기 
	/*  초대한 날의 7일 뒤의 날짜가 현재 날짜이면 삭제 -> 워크스페이스 리스트 들어가기전에 액션 걸기 
	delete from ws_invitation where date(now()) in (  date_add( acc_date , INTERVAL 7 DAY));
	*/
	 public int ws_delAll() {
		 int result = -1;
		 
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt =null;
			String sql ="delete from ws_invitation where date(now()) >= (  date_add( acc_date , INTERVAL 7 DAY));";
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				
				result = pstmt.executeUpdate();
			} catch (Exception e) {  e.printStackTrace(); } 
			finally {
				if(pstmt!=null) {try{pstmt.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(conn!=null) {try{conn.close();}catch (Exception e) {  e.printStackTrace(); } }
			}
			
		 return result;
	 }
	 
	 
	 
	 
	 
	 //초대 거절 상태로 바꾸기
	 // update ws_invitation set ass_status = 'y'  where invi_no=?
	 public int ws_refuse(WS_Invitation_DTO dto) {
		 int result = -1;
		 
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt =null;
		
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("update ws_invitation set ass_status = 'x'  where invi_no=?");
				pstmt.setInt(1, dto.getInvi_no());
				
				result = pstmt.executeUpdate();
			} catch (Exception e) {  e.printStackTrace(); } 
			finally {
				if(pstmt!=null) {try{pstmt.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(conn!=null) {try{conn.close();}catch (Exception e) {  e.printStackTrace(); } }
			}
			
		 return result;
	 } 
	 
	 
	 
	 //초대 수락 상태로 바꾸기 
	 // update ws_invitation set ass_status = 'y'  where invi_no=?
	 public int ws_accept(WS_Invitation_DTO dto) {
		 int result = -1;
		 
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt =null;
		
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("update ws_invitation set ass_status = 'y'  where invi_no=?");
				pstmt.setInt(1, dto.getInvi_no());
				
				result = pstmt.executeUpdate();
			} catch (Exception e) {  e.printStackTrace(); } 
			finally {
				if(pstmt!=null) {try{pstmt.close();}catch (Exception e) {  e.printStackTrace(); } }
				if(conn!=null) {try{conn.close();}catch (Exception e) {  e.printStackTrace(); } }
			}
			
		 return result;
	 } 
	 
	 
	 
	 
	 ///////////////////
	 /// WS 회원검색  (프로젝트 멤버)
		public String  ws_memSearch(P_Member_DTO dto_p, WS_Memeber_DTO dto_ws){
			String result = "";
			
			 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			 DBManager db = new DBManager();
			 String sql =" select jm.jyp_no,jm.jyp_email,jm.jyp_name, jm.dept, jm.position, pm.p_no,pm.p_access" + 
			 		"	     from jyp_member jm left join p_member pm" + 
			 		"	     using(jyp_no)" + 
			 		"	     where  jm.jyp_no  in (select jyp_no from ws_member where ws_no=?) and pm.p_no=? ";
			 try {
				conn=db.getConnection();
				
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, dto_ws.getWs_no());
				pstmt.setInt(2, dto_p.getP_no());
			
				rset=pstmt.executeQuery();
				boolean first = true;
				result="{ \"members\" :[";
				while(rset.next()) {
					if(!first) {result+=",";}
					int p_access = rset.getInt("p_access");
					String p_accessR ="";
					if(p_access==1) {p_accessR ="관리자";}
					else if(p_access==2) {p_accessR ="멤버";}
					result +="{ \"jyp_email\" : \""+rset.getString("jyp_email")+"\" , "
							+ " \"jyp_name\" : \""+rset.getString("jyp_name")+"\" , " 
							+ " \"dept\" : \""+rset.getString("dept")+"\" , "
							+ " \"position\" : \""+rset.getString("position")+"\", "
							+ " \"jyp_no\" : \""+rset.getInt("jyp_no")+"\", "
							+ " \"p_no\" : \""+rset.getInt("p_no")+"\", "
							+ " \"p_access\" : \""+p_accessR+"\" "
							+ " }";
					first=false;

				}
				 result +="] }"; //System.out.println(result);
			} catch (Exception e) { e.printStackTrace(); }
			 finally {
				 if(rset!=null)  { try{rset.close();} catch (Exception e) { e.printStackTrace(); } }
				 if(pstmt!=null) { try{pstmt.close();} catch (Exception e) { e.printStackTrace(); } }
				 if(conn!=null)  { try{conn.close();} catch (Exception e) { e.printStackTrace(); } }
			 }		
			return result;
		}
				
		
		 /// 프로젝 회원검색  (프로젝트 비멤버)
			public String  ws_noMem(P_Member_DTO dto_p, WS_Memeber_DTO dto_ws){
				String result = "";
				
				 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
				 DBManager db = new DBManager();
				 String sql ="	     select jm.jyp_no,jm.jyp_email,jm.jyp_name, jm.dept, jm.position, pm.p_no,pm.p_access " + 
				 		"	     from jyp_member jm left join ws_member wm " + 
				 		"	      using(jyp_no) " + 
				 		"	     					left join p_member pm " + 
				 		"	     using(jyp_no) " + 
				 		"	     where wm.ws_no=?  " + 
				 		"	     group by jyp_no " + 
				 		"	     having  jm.jyp_no not in (select jyp_no from p_member where p_no=?)";
				 try {
					conn=db.getConnection();
					
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, dto_ws.getWs_no());
					pstmt.setInt(2, dto_p.getP_no());
				
					rset=pstmt.executeQuery();
					boolean first = true;
					result="{ \"members\" :[";
					while(rset.next()) {
						if(!first) {result+=",";}
						result +="{ \"jyp_email\" : \""+rset.getString("jyp_email")+"\" , "
								+ " \"jyp_name\" : \""+rset.getString("jyp_name")+"\" , " 
								+ " \"dept\" : \""+rset.getString("dept")+"\" , "
								+ " \"position\" : \""+rset.getString("position")+"\", "
								+ " \"jyp_no\" : \""+rset.getInt("jyp_no")+"\", "
								+ " \"p_no\" : \""+rset.getInt("p_no")+"\", "
								+ " \"p_access\" : \""+rset.getInt("p_access")+"\" "
								+ " }";
						first=false;

					}
					 result +="] }"; //System.out.println(result);
				} catch (Exception e) { e.printStackTrace(); }
				 finally {
					 if(rset!=null)  { try{rset.close();} catch (Exception e) { e.printStackTrace(); } }
					 if(pstmt!=null) { try{pstmt.close();} catch (Exception e) { e.printStackTrace(); } }
					 if(conn!=null)  { try{conn.close();} catch (Exception e) { e.printStackTrace(); } }
				 }		
				return result;
			}
					
			
			
		//////////////////////////////////////////////////	
		 /// 프로젝트 회원검색  (업무 멤버)
			public JsonArray  pro_Memserach(PW_Member_DTO dto_pw, P_Member_DTO dto_p){
				JsonArray list = new JsonArray();
				
				 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
				 DBManager db = new DBManager();
				 String sql ="select jm.jyp_no,jm.jyp_email,jm.jyp_name, jm.dept, jm.position, pwm.work_no " + 
				 		"	     from jyp_member jm left join pw_member pwm " + 
				 		"	     using(jyp_no) " + 
				 		"	     where  jm.jyp_no  in (select jyp_no from p_member where p_no=?) and pwm.work_no=?" + 
				 		"";
				 try {
					conn=db.getConnection();
					
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, dto_p.getP_no());
					pstmt.setInt(2, dto_pw.getWork_no());
					rset=pstmt.executeQuery(); //표
					
					//{" " :[] } 
					while(rset.next()) {
						JsonObject job = new JsonObject();
						job.addProperty("jyp_email", rset.getString("jyp_email"));
						job.addProperty("jyp_name", rset.getString("jyp_name"));
						job.addProperty("dept", rset.getString("dept"));
						job.addProperty("position", rset.getString("position"));
						job.addProperty("jyp_no", rset.getString("jyp_no"));
						job.addProperty("work_no", rset.getString("work_no"));
						
						list.add(job);

					}
					 
					
				} catch (Exception e) { e.printStackTrace(); }
				 finally {
					 if(rset!=null)  { try{rset.close();} catch (Exception e) { e.printStackTrace(); } }
					 if(pstmt!=null) { try{pstmt.close();} catch (Exception e) { e.printStackTrace(); } }
					 if(conn!=null)  { try{conn.close();} catch (Exception e) { e.printStackTrace(); } }
				 }		
				return list;
			}			
	
				
		 /// 프로젝트 회원검색  (업무 비멤버)
			public JsonArray  pro_noMem(PW_Member_DTO dto_pw, P_Member_DTO dto_p){
				JsonArray list = new JsonArray();
				
				 Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
				 DBManager db = new DBManager();
				 String sql ="select jm.jyp_no,jm.jyp_email,jm.jyp_name, jm.dept, jm.position, pwm.work_no " + 
				 		"	     from jyp_member jm left join  p_member pm " + 
				 		"	      using(jyp_no) " + 
				 		"	     					left join  pw_member pwm " + 
				 		"	     using(jyp_no) " + 
				 		"	     where pm.p_no=? " + 
				 		"	     group by jyp_no  " + 
				 		"	     having  jm.jyp_no not in (select jyp_no from pw_member where work_no=?) " + 
				 		"";
				 try {
					conn=db.getConnection();
					
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, dto_p.getP_no());
					pstmt.setInt(2, dto_pw.getWork_no());
					rset=pstmt.executeQuery(); //표
					//{" " :[] } 
					while(rset.next()) {//줄
						JsonObject job = new JsonObject();
						job.addProperty("jyp_email", rset.getString("jyp_email"));
						job.addProperty("jyp_name", rset.getString("jyp_name"));
						job.addProperty("dept", rset.getString("dept"));
						job.addProperty("position", rset.getString("position"));
						job.addProperty("jyp_no", rset.getString("jyp_no"));
						job.addProperty("work_no", rset.getString("work_no"));
						
						list.add(job);

					}
					
				} catch (Exception e) { e.printStackTrace(); }
				 finally {
					 if(rset!=null)  { try{rset.close();} catch (Exception e) { e.printStackTrace(); } }
					 if(pstmt!=null) { try{pstmt.close();} catch (Exception e) { e.printStackTrace(); } }
					 if(conn!=null)  { try{conn.close();} catch (Exception e) { e.printStackTrace(); } }
				 }		
				return list;
			}			

	 
}//end class
