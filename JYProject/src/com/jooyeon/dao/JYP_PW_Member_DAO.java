package com.jooyeon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jooyeon.dbmanager.DBManager;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.dto.PW_Member_DTO;

public class JYP_PW_Member_DAO {
	////////////
	//함께 업무 하는 멤버    // 등록된 시간추가, 
	/*
mysql> desc pw_member;
+------------+--------------+------+-----+-------------------+-------+
| Field      | Type         | Null | Key | Default           | Extra |
+------------+--------------+------+-----+-------------------+-------+
| ws_no      | int(11)      | NO   |     | NULL              |       |
| p_no       | int(11)      | NO   |     | NULL              |       |
| wl_no      | int(11)      | NO   |     | NULL              |       |
| work_no    | int(11)      | NO   |     | NULL              |       |
| jyp_no     | int(11)      | NO   |     | NULL              |       |
| jyp_ip     | varchar(100) | NO   |     | NULL              |       |
| work_alarm | datetime     | YES  |     | NULL              |       |
| pw_Mdate   | datetime     | NO   |     | CURRENT_TIMESTAMP |       |
+------------+--------------+------+-----+-------------------+-------+
8 rows in set (0.01 sec)

	 */
	
	//멤버추가 - work등록할때 같이 등록됨 
	// insert into pw_member (ws_no, p_no,wl_no ,work_no , jyp_no) values (?,?,?,?,?)
	// parameter : PW_Member_DTO dto
	// return : int 
	public int pw_member(PW_Member_DTO dto, JYP_Member_DTO dto2) {
		int result =-1;
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("insert into pw_member (ws_no, p_no,wl_no ,work_no , jyp_no,jyp_ip) values (?,?,?,?,?,?)");
			pstmt.setInt(1, dto.getWs_no());
			pstmt.setInt(2, dto.getP_no());
			pstmt.setInt(3, dto.getWl_no());
			pstmt.setInt(4, dto.getWork_no());
			pstmt.setInt(5, dto2.getJyp_no());
			pstmt.setString(6, dto2.getJyp_ip());
			result=pstmt.executeUpdate();
			
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		
		return result;
	}
	
	
	//업무에 배정된 인원 리스트 
	// select*from pw_member where work_no = ?
	public ArrayList<PW_Member_DTO> pw_memberList(PW_Member_DTO dto) {
		ArrayList<PW_Member_DTO> list = new ArrayList<PW_Member_DTO>();
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		DBManager db = new DBManager();
		String sql = "select pwm.*, jm.jyp_name " + 
					"from pw_member pwm join jyp_member jm  " + 
					"on pwm.jyp_no = jm.jyp_no " + 
					"where pwm.work_no=? and pwm.jyp_no > 0";
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getWork_no());
			rset = pstmt.executeQuery();
			while(rset.next()) {	
				//ws_no, int p_no, int wl_no, int work_no, int jyp_no, String jyp_ip, String work_alarm, String pw_Mdate
				list.add( new PW_Member_DTO( rset.getInt("ws_no"),
											 rset.getInt("p_no"),
											 rset.getInt("wl_no"),
											 rset.getInt("work_no"),
											 rset.getInt("jyp_no"),
											 rset.getString("jyp_ip"),
											 rset.getString("work_alarm"),
											 rset.getString("pw_Mdate"),
											 rset.getString("jyp_name"),
											 rset.getString("work_check")
											 ) );
			}//end while
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		
		return list;
	}
	
	
	
	///한 멤버에 대한 정보   (해당업무 확인유무 출력 
	/*
	 select pwm.*, jm.jyp_name 
	from pw_member pwm join jyp_member jm  
	on pwm.jyp_no = jm.jyp_no 
	where pwm.work_no=172 and pwm.jyp_no = 9
	 */
	public PW_Member_DTO memInfo(PW_Member_DTO dto) {
		PW_Member_DTO result = new PW_Member_DTO();
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		DBManager db = new DBManager();
		String sql = "select pwm.*, jm.jyp_name " + 
					"from pw_member pwm join jyp_member jm  " + 
					"on pwm.jyp_no = jm.jyp_no " + 
					"where pwm.work_no=? and pwm.jyp_no =? ";
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getWork_no());
			pstmt.setInt(2, dto.getJyp_no());
			rset = pstmt.executeQuery();
			while(rset.next()) {	
				//ws_no, int p_no, int wl_no, int work_no, int jyp_no, String jyp_ip, String work_alarm, String pw_Mdate
				result = new PW_Member_DTO( rset.getInt("ws_no"),
											 rset.getInt("p_no"),
											 rset.getInt("wl_no"),
											 rset.getInt("work_no"),
											 rset.getInt("jyp_no"),
											 rset.getString("jyp_ip"),
											 rset.getString("work_alarm"),
											 rset.getString("pw_Mdate"),
											 rset.getString("jyp_name"),
											 rset.getString("work_check") );
			}//end while
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(rset!=null) {try {rset.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		
		return result;
	}
	
	
	
	
	///업무 멤버인지 확인하기 
	public int memCheck(PW_Member_DTO dto) {
		int result =-1;
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select count(*) from pw_member where work_no=? and jyp_no=?");
			pstmt.setInt(1, dto.getWork_no());
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
	
	//업무미확인 -> 확인으로 바꿔주기
	// update pw_member work_check = 'y' where work_no=? and jyp_no=?
	public int workCheck(PW_Member_DTO dto) {
		int result =-1;
				
				Connection conn = null; PreparedStatement pstmt = null; 
				DBManager db = new DBManager();
				
				try {
					conn = db.getConnection();
					pstmt = conn.prepareStatement(" update pw_member set work_check = 'y' where work_no=? and jyp_no=?");
					pstmt.setInt(1, dto.getWork_no());
					pstmt.setInt(2, dto.getJyp_no());
					result = pstmt.executeUpdate();
					
				} catch (Exception e) {  e.printStackTrace(); }
				finally {
					if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
					if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
				}
				
				
				return result;
	}
	
	
	
	
		
	// 미리 알림설정하기 
	// 유저의 work_alarm에 저장됨 
	// update pw_member set work_alarm=?  where work_no = ? and jyp_no=?  //내가 맡은 업무에 알림 설정 
	// parameter : PW_Member_DTO dto
	// return : int 	
	public int work_alarm(PW_Member_DTO dto) {
		int result =-1;
		
		Connection conn = null; PreparedStatement pstmt = null; 
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
		//	pstmt = conn.prepareStatement("update pw_member set work_alarm=?  where work_no = ? and jyp_no=? ");
			pstmt = conn.prepareStatement("update pw_member set work_alarm=?  where work_no = ? and jyp_no=? ");
		//	pstmt.setDate(1, dto.getWork_alarm());
			pstmt.setString(1, dto.getWork_alarm()); 
			System.out.println("알람 DAO getWork_alarm: " +  dto.getWork_alarm()); 
			pstmt.setInt(2, dto.getWork_no());   System.out.println("알람 DAO getWork_no: " +  dto.getWork_no());
			pstmt.setInt(3, dto.getJyp_no());   System.out.println("알람 DAO getJyp_no: " +  dto.getJyp_no());
			/*
			 알람 DAO getWork_alarm: 2020-08-27 17:43
			알람 DAO getWork_no: 134
			알람 DAO getJyp_no: 6
			알람 DAO result: 0
			update pw_member set work_alarm='2020-08-27 17:43'  where work_no = 134 and jyp_no=6
			 */
			//System.out.println("update pw_member set work_alarm=?  where work_no = ? and jyp_no=? ");
			result=pstmt.executeUpdate();   //  System.out.println("알람 DAO result: " +  result);
		
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		
		return result;
	}
		
	
	// 해당 업무에 배정된 인원수
	// select count(*) from pw_member where work_no=?
	public int pw_memberCnt(PW_Member_DTO dto) {
		int result =-1;
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select count(*) from pw_member where work_no=?");
			pstmt.setInt(1, dto.getWork_no());
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
	

	
	
	
	////////삭제/////////////////////////////////////
	
	/// 업무멤버삭제
	// delete from pw_member where work_no =? and jyp_no=?
	public int work_memDel(PW_Member_DTO dto) {
		int result =-1;
		
		Connection conn = null; PreparedStatement pstmt = null; 
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(" delete from pw_member where work_no =? and jyp_no=?");
			pstmt.setInt(1, dto.getWork_no());
			pstmt.setInt(2, dto.getJyp_no());
			
			result=pstmt.executeUpdate();
			
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		
		return result;
	}
	
	// 업무에 해당된 멤버들 다 삭제 (업무삭제될때 다 지워지게)
	public int work_memDelAll(PW_Member_DTO dto) {
		int result =-1;
		
		Connection conn = null; PreparedStatement pstmt = null; 
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(" delete from pw_member where jyp_no>0 and work_no =?");
			pstmt.setInt(1, dto.getWork_no());
			
			result=pstmt.executeUpdate();
			
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		
		return result;
	}
	
	
	
	//워크스페이스 탈퇴하려는 멤버가 속한 업무 다 삭제 
	// delete from pw_member where work_no =?
	public int ws_workmemDel(PW_Member_DTO dto) {
		int result =-1;
		
		Connection conn = null; PreparedStatement pstmt = null; 
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("delete from pw_member where ws_no =? and jyp_no =?");
			pstmt.setInt(1, dto.getWs_no());
			pstmt.setInt(2, dto.getJyp_no());
			
			result=pstmt.executeUpdate();
			
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		
		return result;
	}
	
	//프로젝트 탈퇴하려는 멤버가 속한 업무 다 삭제 
	// delete from pw_member where work_no =?
	public int pro_workmemDel(PW_Member_DTO dto) {
		int result =-1;
		
		Connection conn = null; PreparedStatement pstmt = null; 
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("delete from pw_member where p_no =? and jyp_no =?");
			pstmt.setInt(1, dto.getP_no());
			pstmt.setInt(2, dto.getJyp_no());
			
			result=pstmt.executeUpdate();
			
			
		} catch (Exception e) {  e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try {pstmt.close();}catch (Exception e) {  e.printStackTrace(); }  }
			if(conn!=null)  {try {conn.close();}catch (Exception e) {  e.printStackTrace(); }  }
		}
		
		
		return result;
	}
	
	
}//end class
