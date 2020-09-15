package com.jooyeon.controller_project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_P_Member_DAO;
import com.jooyeon.dto.P_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class P_Member2 implements MAction {

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
		int p_no = (int)session.getAttribute("p_no");
		int jyp_no = (int)session.getAttribute("jyp_no");
		int p_access = Integer.parseInt(request.getParameter("p_access")); //생성하고 추가 액션되면 1, 초대하고 추가 액션되면 2 받기 
	 	String p_Mip = (String)session.getAttribute("jyp_ip");
	 	System.out.println("P_Member에서 p_access: " +p_access);
	 	//워크스페이스 번호
	 	dto.setWs_no(ws_no);
		//프로젝트 번호
		dto.setP_no(p_no);
	 	//회원번호
	 	dto.setJyp_no(jyp_no);
		//관리자 권한
		dto.setP_access(p_access);
	 	//회원아이피
	 	dto.setP_Mip(p_Mip);
	 	
	 	int result = dao.add_member(dto);
	 	
	 	if(result>0) {
			out.println("<script>" //alert('프로젝트 멤버 추가 완료! ');
					+ "location.href='"+request.getContextPath()+"/pro_list.pro';</script>");
		}else {
			out.println("<script>alert('프로젝트 멤버 추가 ERROR\n관리자에게 문의하세요. ');"
					+ "history.go(-1);</script>");
		}
	 	

	}

}
