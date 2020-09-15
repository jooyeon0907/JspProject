package com.jooyeon.controller_ml;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jooyeon.dao.WS_Invitation_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class JYP_MemberSearch implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		PrintWriter out = response.getWriter();
		
		WS_Invitation_DAO dao = new WS_Invitation_DAO();
		JYP_Member_DTO dto = new JYP_Member_DTO();
		
		String jyp_email = request.getParameter("jyp_email");  System.out.println("ACC_Search에서 jyp_email : " + jyp_email);
		dto.setJyp_email(jyp_email);
		System.out.println(dao.userinfo(dto));
		if(dao.userinfo(dto).equals("")) {
			System.out.println("JYP_MemberSearch: 정보없음 ");
			out.println("없음"); 
		}else {  
			out.println(dao.userinfo(dto)); //검색된 회원정보 보내기
		}
		

	}

}
