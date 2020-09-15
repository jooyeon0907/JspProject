package com.jooyeon.controller_work;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_WL_DAO;
import com.jooyeon.dao.JYP_Work_DAO;
import com.jooyeon.dto.P_WL_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WList implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session =request.getSession(true);
		
		JYP_Work_DAO dao_work = new JYP_Work_DAO();
		JYP_WL_DAO dao_wl = new JYP_WL_DAO();
		P_WL_DTO dto = new P_WL_DTO();

		if(request.getParameter("p_no")!=null) { 
			int p_no = Integer.parseInt(request.getParameter("p_no"));
			System.out.println("WList에서 p_no : " + p_no);
			session.setAttribute("p_no", p_no);} //end if
		
		int p_no  = (int)session.getAttribute("p_no");
		dto.setP_no(p_no); 
		System.out.println("WList에서 p_no : " + p_no);
		request.setAttribute("worklist", dao_wl.list_name(dto)); //업무리스트
		request.setAttribute("work", dao_work.work_listAll(p_no));//업무

	}

}
