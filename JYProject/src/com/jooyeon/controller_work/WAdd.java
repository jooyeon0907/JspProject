package com.jooyeon.controller_work;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_PW_Member_DAO;
import com.jooyeon.dao.JYP_P_Member_DAO;
import com.jooyeon.dao.JYP_Work_DAO;
import com.jooyeon.dto.PW_Member_DTO;
import com.jooyeon.dto.P_Member_DTO;
import com.jooyeon.dto.P_Work_DTO;
import com.jooyeon.frontcontroller.MAction;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WAdd implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session =request.getSession(true);
		
		//업무작성 권한 : 프로젝트 멤버 
		// 프로젝트 멤버인지 체크하기
		
		P_Member_DTO dto_pro = new P_Member_DTO();
		JYP_P_Member_DAO dao_pro = new JYP_P_Member_DAO();
		
		int jyp_no = (int)session.getAttribute("jyp_no");
		int p_no = (int)session.getAttribute("p_no");
		dto_pro.setP_no(p_no); dto_pro.setJyp_no(jyp_no);
		
		int p_memCheck = dao_pro.p_memCheck(dto_pro); //유저가 프로젝트 멤버인지 확인 
		//프로젝트멤버이면 글 작성가능 
		if(p_memCheck>0) {
		
			
			String path="/upload/";
			path = request.getServletContext().getRealPath(path);
																	//1024*1024*5
			MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*15, "UTF-8", new DefaultFileRenamePolicy());
			String file = multi.getFilesystemName("file");
			System.out.println("WAdd에서 file : " + file);
			if(file==null) {System.out.println("파일업로드 안됨."); file="no.jpg";}
			//////////////////////////////////////////////////////////////////
	
			P_Work_DTO dto = new P_Work_DTO();
			JYP_Work_DAO dao = new JYP_Work_DAO();
	
			
			int ws_no = (int)session.getAttribute("ws_no");
			int wl_no = Integer.parseInt(multi.getParameter("wl_no"));   System.out.println("WAdd에서 wl_no: " + wl_no);
			String work_cotent = multi.getParameter("work_content");
			//System.out.println("'"+(request.getParameter("work_start"))+"'");
			String work_start = multi.getParameter("work_start");
				if(work_start.equals("")) {work_start=null;}
			String work_end = multi.getParameter("work_end"); 
				if(work_end.equals("")) {work_end=null;}
			String work_ip = (String)session.getAttribute("jyp_ip");
			
			//워크페이스번호
			dto.setWs_no(ws_no);
			//프로젝트번호
			dto.setP_no(p_no);
			//업무리스트번호
			dto.setWl_no(wl_no);
			//업무작성한 회원 번호
			dto.setJyp_no(jyp_no);
			//업무 내용 ( work_content text not null)
			dto.setWork_content(work_cotent);
			//업무 시작일 (work_start date)
			dto.setWork_start(work_start);
			//업무 마감일 ( worl_end date)
			dto.setWork_end(work_end);
			//업무 작성한 멤버의 아이피  (work_ip varchar(100) not null)
			dto.setWork_ip(work_ip);
			//파일 첨부
			dto.setWork_file(file);
			
			int result = dao.write(dto); //업무 추가하기
			
			//방금 만든 업무의 work_no번호 뽑기  (해당 work_no에 멤버추가하려고 / 미리알림설정dao에 필요)
			int work_no = dao.work_no(dto);
			System.out.println("WAdd에서 work_no : " + work_no);
			
			///////////////*--
			//미리알림 설정하기 
			 //설정할 날짜,시간
		/*	String work_alarm_date = multi.getParameter("work_alarm_date"); System.out.println("WAdd에서 work_alarm_date : " + work_alarm_date);
			String work_alarm_time = multi.getParameter("work_alarm_time"); System.out.println("WAdd에서 work_alarm_time : " + work_alarm_time);
			if(work_alarm_date!=null && work_alarm_time != null) {  //미리알람설정을 했다면 데이터 저장시키기
			String work_alarm = work_alarm_date +" " + work_alarm_time;
			//System.out.println("###1. WAdd에서 work_alarm : " + work_alarm);
			
			PW_Member_DTO dto_mem = new PW_Member_DTO();
			JYP_PW_Member_DAO dao_mem  = new JYP_PW_Member_DAO();
			dto_mem.setWork_alarm(work_alarm); System.out.println("###1.WAdd에서 work_alarm : " + dto_mem.getWork_alarm());
			dto_mem.setWork_no(work_no); System.out.println("###2.WAdd에서 work_no : " + dto_mem.getWork_no());
			dto_mem.setJyp_no(jyp_no);	System.out.println("###3.WAdd에서 jyp_no : " + dto_mem.getJyp_no());
			
			int result2 = dao_mem.work_alarm(dto_mem); //알림 설정하기  
			System.out.println("WAdd에서 미리알림추가 성공? : " + result2);
			}//if*/ 
			////////////////

			
			if(result>0 && work_no>0) {
		/*		out.println("<script>alert('업무 업로드 완료');"
					//	+ "location.href='"+request.getContextPath()+"/list.work';</script>");
						+ "location.href='"+request.getContextPath()+"/work_member.work?work_no="+work_no+"';</script>");*/
				request.getRequestDispatcher("work_member.work?work_no="+work_no).forward(request, response);
			}else { 
				out.println("<script>alert('업무 업로드 ERORR); history.go(-1); </script>");
			} 
		}else { 
			//System.out.println("WEditview : 권한X");
			  out.println("<script>alert('권한없음'); history.go(-1);</script>"); }
		
		
		
	}

}//end class
