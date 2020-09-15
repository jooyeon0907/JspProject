package com.jooyeon.controller_project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_Project_DAO;
import com.jooyeon.frontcontroller.MAction;

public class P_list2 implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//프로젝트 리스트 뽑기  삭제해도 될듯
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession(true);
		JYP_Project_DAO dao = new JYP_Project_DAO();
		int ws_no = (int)session.getAttribute("ws_no");
		System.out.println("ws_no : " +ws_no);
		request.setAttribute("list",dao.p_list(ws_no) );
		
		
		request.getRequestDispatcher("/project/p_home.jsp").forward(request, response);
		
		
		
		
		
		
		
		
	}

}
