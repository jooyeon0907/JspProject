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

public class WMember implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		
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
		
		int jyp_no = (int)session.getAttribute("jyp_no");
		String jyp_email = request.getParameter("jyp_email");
	//	System.out.println("WMember에서 jyp_email: " + jyp_email);
		if(jyp_email==null) {
			jyp_email = (String)session.getAttribute("jyp_email");   //초대모드가 아닐시 본인을 멤버로 추가 (업무를 작성한 경우)
		}    
	//	System.out.println("WMember에서 jyp_email22: " + jyp_email);
		dto2.setJyp_email(jyp_email);
		
		dto2.setJyp_no(jyp_no);
		dto2 = dao2.userinfo(dto2);  //jyp_no, jyp_email
		
		int result = dao_mem.pw_member(dto1, dto2); //멤버추가
		
		


		
		if(result>0 && work_no>0) { //alert('pw멤버 추가 완료');
			out.println("<script>"
					+ "location.href='"+request.getContextPath()+"/list.work';</script>");		
		}else { 
			out.println("<script>alert('pw멤버 추가 ERORR); history.go(-1); </script>");
		} 
		
		
	}

}
