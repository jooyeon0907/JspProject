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

public class P_Public implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		
		P_Member_DTO dto = new P_Member_DTO();
		JYP_P_Member_DAO dao = new JYP_P_Member_DAO();

		String p_public = request.getParameter("p_public");
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		int jyp_no = (int)session.getAttribute("jyp_no");
		
		//유저가 프로젝트 멤버인지 확인하기
		dto.setP_no(p_no);
		dto.setJyp_no(jyp_no);
		int memCheck=dao.p_memCheck(dto);
		System.out.println("P_Public에서 memCheck : " + memCheck);

		//프로젝트가 비공개라면 해당 멤버만 볼 수 있게하기 멤버가 아니면 리턴 시킴 
		if(p_public.equals("n")) { 
			if(memCheck<1) { out.println("<script>alert('프로젝트 멤버가아닙니다.'); history.go(-1);</script>"); }
		}
		
		
		out.println("<script>location.href='"+request.getContextPath()+"/list.work?p_no="+p_no+"';</script>");

	}

}
