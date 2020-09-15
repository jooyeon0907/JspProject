package com.jooyeon.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jooyeon.dao.JYP_Member_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class MJoin_com implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		JYP_Member_DTO dto = new JYP_Member_DTO();
		JYP_Member_DAO dao = new JYP_Member_DAO();
		
		String jyp_email = request.getParameter("jyp_email");
		dto.setJyp_email(jyp_email);
		request.setAttribute("dto", dao.userinfo(dto));
		System.out.println(dao.userinfo(dto) );
		request.getRequestDispatcher("/join_login/join_com.jsp").forward(request, response);

	}

}
