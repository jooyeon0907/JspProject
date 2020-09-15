package com.jooyeon.controller_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_PW_Member_DAO;
import com.jooyeon.dao.JYP_Project_DAO;
import com.jooyeon.dao.JYP_WL_DAO;
import com.jooyeon.dao.JYP_Work_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.dto.JYP_Project_DTO;
import com.jooyeon.dto.PW_Member_DTO;
import com.jooyeon.dto.P_WL_DTO;
import com.jooyeon.dto.P_Work_DTO;
import com.jooyeon.frontcontroller.MAction;

public class P_Add implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//프로젝트 생성하기
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		
		JYP_Project_DTO dto = new JYP_Project_DTO();
		JYP_Project_DAO dao = new JYP_Project_DAO();
		
		int ws_no = (int)session.getAttribute("ws_no");
		String p_name = request.getParameter("p_name");
		String p_info = request.getParameter("p_info");
		String p_public = request.getParameter("p_public");
		String p_ip = (String)session.getAttribute("jyp_ip");
		int jyp_no = (int)session.getAttribute("jyp_no");
		
		System.out.println("P_Add에서 p_public: " +p_public);
		//워크스페이스 번호
		dto.setWs_no(ws_no);
		//프로젝트이름
		dto.setP_name(p_name);
		//프로젝트 정보
		dto.setP_info(p_info); 
		//프로젝트 공개여부 y/n
		dto.setP_public(p_public);
		//프로젝트 생성한 회원 ip
		dto.setP_ip(p_ip);
		//프로젝트 생성한 회원 번호
		dto.setJyp_no(jyp_no);
		
		int result = dao.add_pro(dto); //프로젝트 생성하기
		int p_no = dao.p_no(dto);//본인이 방금 만든 프로젝트 번호 뽑기
		System.out.println("P_Add에서 p_no: " +p_no);
		
		
///////@@샘플데이터 추가  -p_worklist,p_work,pw_member @@////////////////
		int sample =0;  jyp_no =0;
		SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
		Calendar now = Calendar.getInstance();
		String nowTime = date.format(now.getTime()); // System.out.println("WS_Add에서 nowTime: " + nowTime);
		
		JYP_WL_DAO 		  dao_wl = new JYP_WL_DAO();
		P_WL_DTO dto_wl = new P_WL_DTO();
		
		//워크스페이스 번호
		dto_wl.setWs_no(ws_no);
		//프로젝트 번호
		dto_wl.setP_no(p_no);
		//업무리스트 이름 
		dto_wl.setWl_name("test");
		//작성한 회원의 아이피 
		dto_wl.setWl_ip(p_ip);
		//회원 번호
		dto_wl.setJyp_no(jyp_no); //0넣기
		
		//업무리스트 생성//
		sample+= dao_wl.wl_add(dto_wl);
		//방금 만든 업무리스트 번호 뽑기
		int wl_no = dao_wl.wl_no(dto_wl);
		
		
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
		dto_work.setWork_ip(p_ip);
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
		
		dto_mem2.setJyp_no(jyp_no);
		dto_mem2.setJyp_ip(p_ip);
		
		//업무멤버생성//
		sample+= dao_pwmem.pw_member(dto_mem1, dto_mem2);
		
		
		System.out.println("sample : " + sample);  //3여야함
		
		
/////////////////////////////////////////////////////
		
		
		
		
		
		if(result>0 && p_no>0 && sample==3) {
			out.println("<script>alert('프로젝트 생성 완료! ');"
					+ "location.href='"+request.getContextPath()+"/p_member2.pro?p_access=1&&jyp_email="+jyp_email+"&&p_no="+p_no+"';</script>"); //관리자 권한으로 주기 p_access=1 (생성하고 나서 멤버추가되는거니까)
				session.setAttribute("p_no", p_no);
		}else {
			out.println("<script>alert('프로젝트 생성 ERROR\n관리자에게 문의하세요. ');"
					+ "history.go(-1);</script>");
		}
		

	}

}
