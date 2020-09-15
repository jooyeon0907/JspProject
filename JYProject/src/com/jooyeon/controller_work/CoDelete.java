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

public class CoDelete implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");    
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		
		P_Co_DAO dao = new P_Co_DAO();
		P_Co_DTO dto = new P_Co_DTO();
		
		int work_no= Integer.parseInt(request.getParameter("work_no"));
		int co_no = Integer.parseInt(request.getParameter("co_no"));
		int jyp_no = (int)session.getAttribute("jyp_no");
		dto.setCo_no(co_no); dto.setJyp_no(jyp_no);
		int result = dao.delete(dto);
		
		
		if(result>0) {
			out.println("<script>alert('댓글삭제완료');"
					+ "location.href='"+request.getContextPath()+"/work_detail.work?work_no="+work_no+"&&editRe=2';</script>");
			
		}
		else if(result>0) {	out.println("<script>alert('댓글삭제ERROR'); history.go(-1);</script>");}
		
		
		
		
	}

}
