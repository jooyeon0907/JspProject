package com.jooyeon.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jooyeon.dao.JYP_Member_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class MIdDouble implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		JYP_Member_DAO dao = new JYP_Member_DAO();
		JYP_Member_DTO dto = new JYP_Member_DTO();
		
		String jyp_email = request.getParameter("jyp_email");  
		dto.setJyp_email(jyp_email);
		System.out.println("MIdDouble 에서  jyp_email: " +jyp_email);
		
		String cnt = dao.jypmember_idCheack(dto);
		System.out.println("MIdDouble 에서  cnt: " + cnt);
		out.println(cnt);
		
		
	}

}
