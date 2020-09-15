package com.jooyeon.controller_work;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_PW_Member_DAO;
import com.jooyeon.dao.JYP_P_Member_DAO;
import com.jooyeon.dao.JYP_WL_DAO;
import com.jooyeon.dao.JYP_WS_DAO;
import com.jooyeon.dao.JYP_Work_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.dto.PW_Member_DTO;
import com.jooyeon.dto.P_Member_DTO;
import com.jooyeon.dto.P_WL_DTO;
import com.jooyeon.dto.P_Work_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WL_add implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		HttpSession session =request.getSession(true);
		PrintWriter out = response.getWriter();
		
		 //권한체크 - 워크스페이스관리자, 프로젝트멤버 
		
		//워크스페이스 관리자번호 체크
		//워크스페이스 정보빼오기 
		JYP_WS_DAO dao_ws = new JYP_WS_DAO();
		JYP_WS_DTO dto_ws = new JYP_WS_DTO();
		
		int ws_no = (int)session.getAttribute("ws_no");
		dto_ws.setWs_no(ws_no);
		dto_ws = dao_ws.ws_info(dto_ws);
		int jyp_no_ws = dto_ws.getJyp_no(); System.out.println("jyp_no_ws: " + jyp_no_ws); 
		
		
		 //프로젝트 멤버인지 체크
		 JYP_P_Member_DAO dao_pMem = new JYP_P_Member_DAO();
		 P_Member_DTO dto_pMem = new P_Member_DTO();
		 
		 int p_no = (int)session.getAttribute("p_no"); // session 값 받기    
		 int jyp_no = (int)session.getAttribute("jyp_no"); // session 값 받기
		 dto_pMem.setP_no(p_no);
		 dto_pMem.setJyp_no(jyp_no);
		 
		 //프로젝트 멤버인지 체크
		 int p_memCheck = dao_pMem.p_memCheck(dto_pMem); 
		 
		//프로젝트멤버이면  WL추가가능
		 if(p_memCheck>0){ 
			 
			 
			P_WL_DTO dto = new P_WL_DTO();
			JYP_WL_DAO dao = new JYP_WL_DAO();
			
			
			String wl_name = request.getParameter("wl_name");
			String wl_ip = (String)session.getAttribute("jyp_ip");
			//워크스페이스 번호
			dto.setWs_no(ws_no);
			//프로젝트 번호
			dto.setP_no(p_no);
			//회원 번호
			dto.setJyp_no(jyp_no);
			//업무리스트 이름 
			dto.setWl_name(wl_name);
			//작성한 회원의 아이피 
			dto.setWl_ip(wl_ip);
			
			//업무리스트 생성 
			int result = dao.wl_add(dto);
			
			
			//방금 만든 업무리스트 번호 뽑기
			int wl_no = dao.wl_no(dto);  System.out.println("wl_no : "+wl_no);
		///////@@샘플데이터 추가  -p_work,pw_member @@////////////////
			int sample =0;  jyp_no =0;
				SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
				Calendar now = Calendar.getInstance();
				String nowTime = date.format(now.getTime()); // System.out.println("WS_Add에서 nowTime: " + nowTime);
			
			
				JYP_Work_DAO	  dao_work = new JYP_Work_DAO();
				P_Work_DTO dto_work = new P_Work_DTO();
				
				//워크페이스번호
				dto_work.setWs_no(ws_no);
				//프로젝트번호
				dto_work.setP_no(p_no);
				//업무리스트 번호
				dto_work.setWl_no(wl_no);
				//업무작성한 회원 번호
				dto_work.setJyp_no(jyp_no); //0
				//업무 내용 ( work_content text not null)
				dto_work.setWork_content("test");
				//업무 시작일 (work_start date)
				dto_work.setWork_start(nowTime);
				//업무 마감일 ( worl_end date)
				dto_work.setWork_end(nowTime);
				//업무 작성한 멤버의 아이피  (work_ip varchar(100) not null)
				dto_work.setWork_ip(wl_ip);
				//파일 첨부
				dto_work.setWork_file("no.jpg");
				
				//업무 생성//
				sample+= dao_work.write(dto_work);
				//방금 만든 work_no 뽑기
				int work_no = dao_work.work_no(dto_work);
				
				JYP_PW_Member_DAO dao_pwmem = new JYP_PW_Member_DAO();
				PW_Member_DTO dto_mem1 = new PW_Member_DTO();
				JYP_Member_DTO dto_mem2 = new JYP_Member_DTO();
				
				dto_mem1.setWs_no(ws_no); 
				dto_mem1.setP_no(p_no);
				dto_mem1.setWl_no(wl_no);
				dto_mem1.setWork_no(work_no);
	
				String jyp_email = (String)session.getAttribute("jyp_email"); //session 값 가져오기 
				dto_mem2.setJyp_email(jyp_email);
				
				dto_mem2.setJyp_no(jyp_no); //0
				dto_mem2.setJyp_ip(wl_ip);
				
				//업무멤버생성//
				sample+= dao_pwmem.pw_member(dto_mem1, dto_mem2);
				
				
				System.out.print("sample : " + sample);  //2여야함
				
				
				
		///////////////////////////////////////////////////////////		
				
			
			if(result>0 && sample==2 ) {
				out.println("<script>alert('업무리스트 생성 완료');"
						+ "location.href='"+request.getContextPath()+"/list.work';</script>");
			}else { 
				out.println("<script>alert('업무리스트 생성 ERORR); history.go(-1); </script>");
			} 
		 }
		 else {
			 out.println("<script>alert('권한없음'); history.go(-1);</script>");
		 }
			
			
			
			
	}

}
