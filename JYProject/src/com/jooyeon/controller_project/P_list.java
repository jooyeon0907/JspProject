package com.jooyeon.controller_project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_Project_DAO;
import com.jooyeon.dto.JYP_Project_DTO;
import com.jooyeon.dto.P_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class P_list implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//프로젝트 리스트 뽑기 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession(true);
		JYP_Project_DAO dao = new JYP_Project_DAO();
		JYP_Project_DTO dto_p = new JYP_Project_DTO();
		P_Member_DTO dto_pm = new P_Member_DTO();
		
		int ws_no = (int)session.getAttribute("ws_no");
		int jyp_no =  (int)session.getAttribute("jyp_no");
		dto_pm.setWs_no(ws_no); dto_pm.setJyp_no(jyp_no);
		dto_p.setWs_no(ws_no);	dto_p.setJyp_no(jyp_no);
		System.out.println("ws_no : " +ws_no);
//		request.setAttribute("list",dao.p_list(ws_no) );

		////////////////////////////
		//분류해서 리스트 뽑기
		request.setAttribute("pro_admin",dao.pro_admin(dto_pm) );
		request.setAttribute("pro_member",dao.pro_member(dto_pm) );
		request.setAttribute("pro_etc",dao.pro_etc(dto_pm,dto_p) );
		
		
		
		request.getRequestDispatcher("/project/p_home.jsp").forward(request, response);
		
		
		
		
		
		
		
		
	}

}
