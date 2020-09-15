package com.jooyeon.controller_ws;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_WS_DAO;
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WS_Address implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		
		int ws_no = (int)session.getAttribute("ws_no");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		System.out.println("WS_Address에서 주소 : " + address1 + " / " + address2);
		
		String address = address1 +" " + address2;
		
		JYP_WS_DTO dto = new JYP_WS_DTO();
		JYP_WS_DAO dao = new JYP_WS_DAO();
		
		dto.setWs_no(ws_no); dto.setAddress(address);
		int result = dao.address(dto); //주소 등록하기 
		
		if(result>0){
			out.println("성공");
		}else {out.println("ERROR");}
		
		

	}

}
