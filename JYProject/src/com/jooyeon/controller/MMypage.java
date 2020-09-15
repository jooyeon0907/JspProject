package com.jooyeon.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_Member_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class MMypage implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//<%MemberDto dto = (MemberDto)request.getAttribute("dto"); %> //userinfo 넣기 
		
		JYP_Member_DTO dto = new JYP_Member_DTO();
		JYP_Member_DAO dao = new JYP_Member_DAO();
		HttpSession session = request.getSession(true);
		
		dto.setJyp_email((String)session.getAttribute("jyp_email"));
		dto.setJyp_pass((String)session.getAttribute("jyp_pass"));
		request.setAttribute("dto", dao.userinfo(dto));
//		System.out.println("userinfo : " +dao.userinfo(dto));

	//	System.out.println("mid : " +session.getAttribute("mid") );
		request.getRequestDispatcher("/join_login/mypage.jsp").forward(request, response);

	}

}
