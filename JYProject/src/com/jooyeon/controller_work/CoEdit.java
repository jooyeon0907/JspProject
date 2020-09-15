package com.jooyeon.controller_work;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.P_Co_DAO;
import com.jooyeon.dto.P_Co_DTO;
import com.jooyeon.frontcontroller.MAction;

public class CoEdit implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");    
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		P_Co_DAO dao = new P_Co_DAO();
		P_Co_DTO dto = new P_Co_DTO();
	
		int co_no = Integer.parseInt(request.getParameter("co_no"));   System.out.println("CoEdit에서 co_no: " +co_no);
		String comment = request.getParameter("comment");			System.out.println("CoEdit에서 comment: " +comment);
		dto.setCo_no(co_no); dto.setComment(comment);
		
		int result = dao.edit(dto);
		
		if(result>0) { out.println("성공");  }
		else if(result>0) {	out.println("ERROR");}
		
		
		

	}

}
