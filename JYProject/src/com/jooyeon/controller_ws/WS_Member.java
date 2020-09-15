package com.jooyeon.controller_ws;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_WS_Member_DAO;
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.dto.WS_Memeber_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WS_Member implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//워크스페이스 멤버 추가하기 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		
		WS_Memeber_DTO dto = new WS_Memeber_DTO();
		JYP_WS_Member_DAO dao = new JYP_WS_Member_DAO();
		
	 	
	//	int ws_no = (int)session.getAttribute("ws_no");  
	 	int ws_no = Integer.parseInt(request.getParameter("ws_no"));
		System.out.println("WS_Member에서 ws_no : " + ws_no);
		int jyp_no = (int)session.getAttribute("jyp_no");
	 	String ws_Mip = (String)session.getAttribute("jyp_ip");
	 	
	 	System.out.println("WS_Member에서 ws_no : " + ws_no);
	 	//워크스페이스 번호
	 	dto.setWs_no(ws_no);
	 	//회원번호
	 	dto.setJyp_no(jyp_no);
	 	//회원아이피
	 	dto.setWs_Mip(ws_Mip);
	 	
	 	int result = dao.member(dto);
	 	
	 	if(result>0) {
			out.println("<script>" //alert('워크스페이스 멤버 추가 완료! ');
				//	+ "location.href='"+request.getContextPath()+"/ws_list.ws';</script>");
					+ "location.href='"+request.getContextPath()+"/ws_list.ws?result=1';</script>");
		}else {
			out.println("<script>alert('워크스페이스 멤버 추가 ERROR\n관리자에게 문의하세요. ');"
					+ "history.go(-1);</script>");
		}
	 	
	 	
	 	
	 	
		
	}

}
