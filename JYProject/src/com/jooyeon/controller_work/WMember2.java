package com.jooyeon.controller_work;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_Member_DAO;
import com.jooyeon.dao.JYP_PW_Member_DAO;
import com.jooyeon.dao.JYP_Work_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.dto.PW_Member_DTO;
import com.jooyeon.dto.P_Work_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WMember2 implements MAction {
//다른 멤버 추가 ver
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
	//	HttpSession session = request.getSession(true);
		
		JYP_Work_DAO dao = new JYP_Work_DAO();
		P_Work_DTO dto = new P_Work_DTO();
		PW_Member_DTO dto1 = new PW_Member_DTO();
		
		int work_no = Integer.parseInt(request.getParameter("work_no")); System.out.println("WMember에서 work_no : " +work_no);
		// work의 정보 뺴오기
		dto = dao.work_detail2(work_no);
		
		dto1.setWs_no(dto.getWs_no()); 
		dto1.setP_no(dto.getP_no());
		dto1.setWl_no(dto.getWl_no());
		dto1.setWork_no(dto.getWork_no());
		
		//유저정보 뽑아오기
		JYP_Member_DAO dao2 = new JYP_Member_DAO();
		JYP_Member_DTO dto2 = new JYP_Member_DTO();
		JYP_PW_Member_DAO dao_mem = new JYP_PW_Member_DAO();
		
	//	int jyp_no = Integer.parseInt(request.getParameter("jyp_no"));   //추가하는 멤버의 번호
		String jyp_email = request.getParameter("jyp_email");			//추가하는 멤버의 이메일
		System.out.println("WMember2에서 추가하는 jyp_email: " + jyp_email);
		
		dto2.setJyp_email(jyp_email);  //  dto2.setJyp_no(jyp_no);
		dto2 = dao2.userinfo(dto2);  //jyp_no, jyp_email
			
		int result = dao_mem.pw_member(dto1, dto2); //멤버추가
	 	if(result>0) {
			out.println("성공");
		}else {
			out.println("오류");
		}
			
		

		
	}

}
