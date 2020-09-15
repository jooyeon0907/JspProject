package com.jooyeon.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.frontcontroller.MAction;

public class MLogout implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.invalidate(); //세션값 초기화
		
		
	//	request.getRequestDispatcher("login_view.members").forward(request, response);
		PrintWriter out = response.getWriter();
	    out.println("<script>alert('로그아웃합니다.'); "
	    		+ "location.href='"+request.getContextPath()+"/login_view.members'; </script>");
		
		
		

	}

}
