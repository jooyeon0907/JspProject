package com.jooyeon.controller_work;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jooyeon.dao.JYP_Work_DAO;
import com.jooyeon.dto.P_Work_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WCheck implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		PrintWriter out = response.getWriter();
		
		P_Work_DTO dto = new P_Work_DTO();
		JYP_Work_DAO dao = new JYP_Work_DAO();
		
		int p_no = Integer.parseInt(request.getParameter("p_no"));  System.out.println("WCheck에서 p_no : " + p_no);//22; 
		dto.setP_no(p_no);
		
		
		out.println(dao.workCheck(dto)); //정보 보내기

	}

}
