package com.jooyeon.controller_work;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jooyeon.dao.JYP_Work_DAO;
import com.jooyeon.dto.P_Work_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WChecked implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		JYP_Work_DAO dao = new JYP_Work_DAO();
		P_Work_DTO dto  =new P_Work_DTO();
		
		int work_no = Integer.parseInt(request.getParameter("work_no")); dto.setWork_no(work_no);
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		
		int result= dao.checked(dto);
		if(result>0) {
			if(page==1) { //wl_list로
				out.println("<script>location.href='"+request.getContextPath()+"/list.work?p_no="+p_no+"';</script>");
			}else if(page==2) { //work_detail로 
				out.println("<script>location.href='"+request.getContextPath()+"/work_detail.work?work_no="+work_no+"';</script>");
			}
		} else if(result>0) {	out.println("<script>alert('ERROR'); history.go(-1);</script>");}

	}

}
