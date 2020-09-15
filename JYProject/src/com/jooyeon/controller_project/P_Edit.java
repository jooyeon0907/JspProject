package com.jooyeon.controller_project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_P_Member_DAO;
import com.jooyeon.dao.JYP_Project_DAO;
import com.jooyeon.dto.JYP_Project_DTO;
import com.jooyeon.dto.P_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class P_Edit implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8 ");
		
		PrintWriter out = response.getWriter();
		
		int p_no = Integer.parseInt(request.getParameter("p_no")); System.out.println("P_Edit에서 p_no:" + p_no);
		JYP_Project_DTO dto = new JYP_Project_DTO();
		JYP_Project_DAO dao = new JYP_Project_DAO();
		
		String p_name = request.getParameter("p_name");
		String p_info = request.getParameter("p_info");
		//System.out.println("stats::::" +request.getParameter("p_status"));
		int p_status = Integer.parseInt(request.getParameter("p_status"));   System.out.println("P_Edit에서 p_status:" + p_status);
		String p_start = request.getParameter("p_start");  System.out.println("P_Edit에서 p_start:" + p_start);
			if(p_start.equals("")) {p_start=null;}
		String p_end = request.getParameter("p_end");
			if(p_end.equals("")) {p_end=null;}
		String p_complete = request.getParameter("p_complete"); 
			if(p_complete.equals("")) {p_complete=null;}
		String p_public = request.getParameter("p_public");   System.out.println("P_Edit에서 p_public:" + p_public);
		
		dto.setP_name(p_name);
		dto.setP_info(p_info);
		dto.setP_status(p_status);
		dto.setP_start(p_start);
		dto.setP_end(p_end);
		dto.setP_complete(p_complete);
		dto.setP_public(p_public);
		dto.setP_no(p_no);
		
		int result = dao.p_edit(dto);
		if(result>0) {
			out.println("<script>alert('프로젝트 수정완료.');"
					+ "location.href='"+request.getContextPath()+"/pro_list.pro';</script>");
		}else {out.println("<script>alert('프로젝트 수정완료.');"
				+ "history.go(-1)</script>");}
		
		

	}

}
