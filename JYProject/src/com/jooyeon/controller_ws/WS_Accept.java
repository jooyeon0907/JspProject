package com.jooyeon.controller_ws;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jooyeon.dao.WS_Invitation_DAO;
import com.jooyeon.dto.WS_Invitation_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WS_Accept implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		WS_Invitation_DTO dto = new WS_Invitation_DTO();
		WS_Invitation_DAO dao = new WS_Invitation_DAO();
		
		int ws_no = Integer.parseInt(request.getParameter("ws_no")); System.out.println("WS_Accept에서  ws_no : " + ws_no);
		int invi_no = Integer.parseInt(request.getParameter("invi_no"));
		dto.setInvi_no(invi_no);
		
		int result = dao.ws_accept(dto); // 수락상태 y로 바꾸기 
		if(result>0){out.println("<script>alert('초대수락 완료');"
				+ "location.href='"+request.getContextPath()+"/ws_member.ws?ws_no="+ws_no+"';</script>");}
		else{out.println("<script>alert('초대수락 ERROR'); history.go(-1);</script>");}
		
		
		
		
	}

}
