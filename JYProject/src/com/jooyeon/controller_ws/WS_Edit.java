package com.jooyeon.controller_ws;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_WS_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WS_Edit implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		
		JYP_WS_DAO dao = new JYP_WS_DAO();
		JYP_WS_DTO dto1 = new JYP_WS_DTO();
		JYP_Member_DTO dto2 = new JYP_Member_DTO();
		
		String ws_name = request.getParameter("ws_name");
		int ws_no = (int)session.getAttribute("ws_no");
		int jyp_no2 = (int)session.getAttribute("jyp_no");
		
		dto1.setWs_name(ws_name);
		dto1.setWs_no(ws_no);
		int jyp_no1=dao.ws_info(dto1).getJyp_no(); //ws 정보불러오기 (워크스페이스의 관리자 번호를 빼오기 위해)
		dto1.setJyp_no(jyp_no1);
		dto2.setJyp_no(jyp_no2);
		
		int result = dao.ws_editName(dto1, dto2);
		
		if(result>0){ out.println("<script>alert('워크스페이스 이름 변경 완료');"
		//		+ "location.href='"+request.getContextPath()+"/ws_edit_view.ws';</script>"); 
				+ "location.href='"+request.getContextPath()+"/ws_home.ws?ws_no="+ws_no+"&&ws_name="+ws_name+"';</script>"); 
				//ws_edit_home.ws으로 보낼지 고민 (그러면 ws_no도 보내줘야됨 ) 그럼 ws_name는 여기서 저장안해도됨 
		//	session.setAttribute("ws_name", ws_name);//변경된 ws_name다시 저장####ㄴsession에?
		}
	 	else {  out.println("<script>alert('권한 없음 ');"
	 			+ "history.go(-1);</script>"); }
		
	}

}
