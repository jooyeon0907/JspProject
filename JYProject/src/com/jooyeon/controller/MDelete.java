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

public class MDelete implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		JYP_Member_DTO dto = new JYP_Member_DTO();
		JYP_Member_DAO dao = new JYP_Member_DAO();
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		
		dto.setJyp_email((String)session.getAttribute("jyp_email"));
		dto.setJyp_pass(request.getParameter("jyp_pass"));
		int result = dao.user_delete(dto);
		
		
		if(result>0) {
			out.println("<script>alert('계정이 삭제되었습니다..'); "
					+ " location.href='"+request.getContextPath()+"/login_view.members';</script>");
			session.invalidate(); //세션값 초기화
		}else {
			out.println("<script>alert('비밀번호를 확인해주세요.'); history.go(-1); </script>");
		}
		
		

	}

}
