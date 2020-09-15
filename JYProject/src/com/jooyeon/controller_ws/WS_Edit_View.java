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

public class WS_Edit_View implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		
		JYP_WS_DTO dto = new JYP_WS_DTO();
		JYP_WS_DAO dao = new JYP_WS_DAO();
		
		int ws_no = (int)session.getAttribute("ws_no");
		dto.setWs_no(ws_no);
		dto = dao.ws_info(dto); /// 워크스페이스 정보빼오기 
		System.out.println("WS_Edit_View 에서  dto.getJyp_no():" +  dto.getJyp_no());
		request.setAttribute("ws_jypno", dto.getJyp_no());
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("workspace/ws_edit.jsp").forward(request, response);

	}

}
