package com.jooyeon.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_Member_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class MPass implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8 ");
		//update mvcmember3 set mpass = ? where mid =? and mpass=?
	
		
		JYP_Member_DTO dto = new JYP_Member_DTO();
		JYP_Member_DAO dao = new JYP_Member_DAO();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);

		dto.setJyp_email((String)session.getAttribute("jyp_email"));
		dto.setJyp_pass(request.getParameter("new"));
		
		int result = dao.user_pass(dto, request.getParameter("old"));
		
		if(result>0){out.println("<script>alert('비번수정 완료!!');"
				+ " location.href='"+request.getContextPath()+"/mypage_view.members';</script>"); } 
		else {out.println("<script>alert('비번수정 ERROR'); history.go(-1); </script>");}
		
	

	}

}
