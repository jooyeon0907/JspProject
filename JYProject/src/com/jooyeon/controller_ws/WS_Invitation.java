package com.jooyeon.controller_ws;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_Member_DAO;
import com.jooyeon.dao.JYP_WS_DAO;
import com.jooyeon.dao.JYP_WS_Member_DAO;
import com.jooyeon.dao.WS_Invitation_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.dto.WS_Invitation_DTO;
import com.jooyeon.dto.WS_Memeber_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WS_Invitation implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		
		JYP_Member_DTO dto_send = new JYP_Member_DTO();
		JYP_Member_DTO dto_get = new JYP_Member_DTO();
		JYP_Member_DAO dao_mem = new JYP_Member_DAO();
		
		JYP_WS_DTO dto_ws = new JYP_WS_DTO();
		JYP_WS_DAO dao_ws = new JYP_WS_DAO();
		WS_Invitation_DAO dao_invi = new WS_Invitation_DAO();
		
		//초대보내는 유저 정보(현재 유저)
		String send_email = (String)session.getAttribute("jyp_email"); //session에 저장된 데이터 불러오기
		dto_send.setJyp_email(send_email);
		dto_send = dao_mem.userinfo(dto_send);
		//초대된 유저 정보 (검색한 이메일로 데이터 넣기 )
		String get_email = request.getParameter("jyp_email"); System.out.println("WS_Invitation에서 초대된 유저 jyp_email: " +get_email);
		dto_get.setJyp_email(get_email);
		dto_get = dao_mem.userinfo(dto_get);
		//워크스페이스 유저
		int ws_no = (int)session.getAttribute("ws_no"); //session에 저장된 데이터 불러오기
		dto_ws.setWs_no(ws_no);
		dto_ws = dao_ws.ws_info(dto_ws);
		
		//등록되어 있는 유저인지 체크 (수락 안한 상태) 
		WS_Invitation_DTO dto_invi = new WS_Invitation_DTO();
		dto_invi.setWs_no(ws_no);
		dto_invi.setGet_email(get_email);
		int check = dao_invi.check(dto_invi);
		
		// 이미 워크스페이스 멤버인지  체크 
		WS_Memeber_DTO dto_wsmem = new WS_Memeber_DTO();
		JYP_WS_Member_DAO dao_wsmem = new JYP_WS_Member_DAO();
		int jyp_no = dto_get.getJyp_no();
		dto_wsmem.setWs_no(ws_no);
		dto_wsmem.setJyp_no(jyp_no); System.out.println("WS_Invitation에서 초대받는 회원의 번호 : "+jyp_no);
		//초대하려는 회원이 워크스페이스에 소속되어있는지 체크
		int ws_memCheck = dao_wsmem.ws_memCheck(dto_wsmem);
	
		
		if(check>0) {
			out.println("<script>alert('이미 초대보낸 회원입니다.'); history.go(-1);</script>");
		}else if(ws_memCheck>0) {
			out.println("<script>alert('이미 소속된 회원입니다.'); history.go(-1);</script>");
		}
		else {
			//초대리스트 등록하기 
			int result = dao_invi.ws_invitation(dto_send, dto_get, dto_ws);  System.out.println("WS_Invitation에서 ws_no : "+ ws_no);
			if(result>0){
				System.out.println("WS_Invitation 초대");
				out.println("<script>"
						+ "alert('초대메시지가 전송되었습니다. 초대메시지는 7일동안 유지됩니다');"
						+ "location.href='"+request.getContextPath()+"/member_list.mem';</script>");}	
			else{out.println("<script>alert('초대리스트등록 ERROR'); history.go(-1);</script>");}
		}
		

	}

}
