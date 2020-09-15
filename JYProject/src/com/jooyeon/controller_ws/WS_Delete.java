package com.jooyeon.controller_ws;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_Member_DAO;
import com.jooyeon.dao.JYP_WS_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WS_Delete implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		
		JYP_WS_DAO dao_ws = new JYP_WS_DAO();
		JYP_WS_DTO dto_ws = new JYP_WS_DTO();
		JYP_Member_DTO dto_mem = new JYP_Member_DTO();
		JYP_Member_DAO dao_mem = new JYP_Member_DAO();
		
		//워크스페이스 관리자번호와 유저번호가 일치하는지, 그 번호가의 아이디비번이 입력한 아이디비번이랑 일치하는지 
		
		//유저 정보 빼오기
		String jyp_email = (String)session.getAttribute("jyp_email");   
		
		dto_mem.setJyp_email(jyp_email);
		dto_mem = dao_mem.userinfo(dto_mem);
		
		int jyp_no = dto_mem.getJyp_no();
		String jyp_pass =dto_mem.getJyp_pass(); //System.out.println("WS_Delete 에서 jyp_pass: " + jyp_pass);
		
		//워크스페이스 정보빼오기 
		int ws_no = (int)session.getAttribute("ws_no");
		dto_ws.setWs_no(ws_no);
		dto_ws = dao_ws.ws_info(dto_ws);
		
		int jyp_no_ws = dto_ws.getJyp_no(); System.out.println("jyp_no_ws: " + jyp_no_ws); 
		
		String emailCheck = request.getParameter("jyp_email"); //request 받은 값
			System.out.println("WS_Delete에서 emailCheck: "+emailCheck) ;
	    String passCheck = request.getParameter("jyp_pass"); //resquest 받은값 
	    	System.out.println("WS_Delete에서 passCheck: "+passCheck) ;
		//워크스페이스 관리자번호와 유저번호가 일치하는지, 그 번호가의 아이디비번이 입력한 아이디비번이랑 일치하는지 
	    if(jyp_no==jyp_no_ws && jyp_email.equals(emailCheck) && jyp_pass.equals(passCheck) ){
	    	System.out.println("IF 통과");
	  		int result = dao_ws.ws_delete(dto_ws);
	    	if(result>0){ 
	    				session.removeAttribute("ws_no"); //session에 저장된 ws_no지우기
	    				out.println("<script>alert('워크스페이스 삭제 완료');"
	    				+ " location.href='"+request.getContextPath()+"/login2.members';</script>"); }
	    		//	+ " location.href='"+request.getContextPath()+"/ws_list.ws';</script>"); }
	     	else {  out.println("<script>alert('워크스페이스 삭제 ERROR');history.go(-1);</script>"); }

	    }
	    else { out.println("<script>alert('일치되는 정보가 없습니다.'); history.go(-1);</script>"); }


		
		
	}

}
