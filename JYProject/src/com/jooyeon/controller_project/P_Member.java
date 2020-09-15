package com.jooyeon.controller_project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_Member_DAO;
import com.jooyeon.dao.JYP_P_Member_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.dto.P_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class P_Member implements MAction {
//초대 ver (ajax)
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//프로젝트 멤버 추가하기 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		
		P_Member_DTO dto = new P_Member_DTO();
		JYP_P_Member_DAO dao = new JYP_P_Member_DAO();
		
		int ws_no = (int)session.getAttribute("ws_no");
		int p_no = Integer.parseInt(request.getParameter("p_no")); System.out.println("P_Member에서 p_no: " +p_no);
		int p_access = Integer.parseInt(request.getParameter("p_access"));  System.out.println("P_Member에서 p_access: " +p_access);
		String jyp_email  = request.getParameter("jyp_email"); System.out.println("P_Member 에서 jyp_email: " +jyp_email );
		
		JYP_Member_DAO dao_mem = new JYP_Member_DAO();
		JYP_Member_DTO dto_mem = new JYP_Member_DTO();
		
		dto_mem.setJyp_email(jyp_email);
		dto_mem = dao_mem.userinfo(dto_mem); //추가하려는 회원의 정보 뺴오기 
		System.out.println("dto_mem : "+ dto_mem);
		//정보뺴오기 
		int jyp_no = dto_mem.getJyp_no();
	 	String p_Mip = dto_mem.getJyp_ip();
	 	
	 	//워크스페이스 번호
	 	dto.setWs_no(ws_no);
		//프로젝트 번호
		dto.setP_no(p_no);
	 	//회원번호
	 	dto.setJyp_no(jyp_no);
		//관리자 권한
		dto.setP_access(p_access);
	 	//회원아이피
	 	dto.setP_Mip(p_Mip); // System.out.println("P_Member 에서 p_Mip: " +p_Mip );
	 	
	 	int result = dao.add_member(dto);
	 	if(result>0) { out.println("성공");
		}else { out.println("오류"); }
	 	


	}

}
