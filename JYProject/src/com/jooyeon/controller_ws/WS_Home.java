package com.jooyeon.controller_ws;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_WS_DAO;
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.dto.WS_Memeber_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WS_Home implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		HttpSession session = request.getSession(true);
	//	int jyp_no = (int)session.getAttribute("jyp_no");
		//session에 ws_no 남기기 
	//	JYP_WS_DTO dto = new JYP_WS_DTO();
	//	JYP_WS_DAO dao = new JYP_WS_DAO();
		int ws_no =  Integer.parseInt(request.getParameter("ws_no"));
		String ws_name = request.getParameter("ws_name");
		session.setAttribute("ws_no",ws_no);
		session.setAttribute("ws_name", ws_name);
	//	request.setAttribute("ws_name", ws_name);
		System.out.println("WS_Home에서 ws_no : " + ws_no);
	//	dto = dao.ws_info(jyp_no); //입장하려는 워크스페이스의 정보불러오기  -> ws_list에서 해결 
	
		
//		//이따 사용하기 
//		session.setAttribute("ws_no",dto.getWs_no());
//		session.setAttribute("ws_name",dto.getWs_name());


		request.getRequestDispatcher("/update_home.up").forward(request, response);
	}

	
	
	
	
	
	
}
