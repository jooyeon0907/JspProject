package com.jooyeon.controller_ml;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.WS_Invitation_DAO;
import com.jooyeon.dto.P_Member_DTO;
import com.jooyeon.dto.WS_Memeber_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WS_MemberSearchNo implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		
		WS_Invitation_DAO dao = new WS_Invitation_DAO();
		WS_Memeber_DTO dto_ws = new WS_Memeber_DTO();
		P_Member_DTO dto_p = new P_Member_DTO();
		int ws_no =  (int)session.getAttribute("ws_no");  System.out.println("WS_MemberSearchNo에서 ws_no : " + ws_no);
		int p_no = Integer.parseInt(request.getParameter("p_no"));  System.out.println("WS_MemberSearchNo에서 p_no : " + p_no);
		
		dto_ws.setWs_no(ws_no);
		dto_p.setP_no(p_no);
		
		out.println(dao.ws_noMem(dto_p, dto_ws)); //검색된 회원 정보 보내기 
		
	}

}
