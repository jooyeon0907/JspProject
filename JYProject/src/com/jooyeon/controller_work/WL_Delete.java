package com.jooyeon.controller_work;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_P_Member_DAO;
import com.jooyeon.dao.JYP_WL_DAO;
import com.jooyeon.dao.JYP_WS_DAO;
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.dto.P_Member_DTO;
import com.jooyeon.dto.P_WL_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WL_Delete implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session =request.getSession(true);
		
		 //권한체크  - 워크스페이스 관리자, 프로젝트관리자, 업무리스트 생성자
		
		//워크스페이스 관리자번호 체크
		//워크스페이스 정보빼오기 
		JYP_WS_DAO dao_ws = new JYP_WS_DAO();
		JYP_WS_DTO dto_ws = new JYP_WS_DTO();
		
		int ws_no = (int)session.getAttribute("ws_no");
		dto_ws.setWs_no(ws_no);
		dto_ws = dao_ws.ws_info(dto_ws);
		int jyp_no_ws = dto_ws.getJyp_no(); System.out.println("jyp_no_ws: " + jyp_no_ws); 
		
		
		 //프로젝트 관리자 권한체크
		 JYP_P_Member_DAO dao_pMem = new JYP_P_Member_DAO();
		 P_Member_DTO dto_pMem = new P_Member_DTO();
		 
		 int p_no = (int)session.getAttribute("p_no"); // session 값 받기    
		 int jyp_no = (int)session.getAttribute("jyp_no"); // session 값 받기
		 dto_pMem.setP_no(p_no);
		 dto_pMem.setJyp_no(jyp_no);
		 
		 //프로젝트 권한 확인 -return값: 관리자 =1, 일반멤버  =2 
		 int p_access = dao_pMem.p_access(dto_pMem); 
		
		// 업무리스트 작성자인지 체크
		JYP_WL_DAO dao_wl = new JYP_WL_DAO();
		P_WL_DTO dto_wl = new P_WL_DTO();

		int wl_no = Integer.parseInt(request.getParameter("wl_no")); // parameter 값 넘겨 받기 
		dto_wl.setWl_no(wl_no);
		int jypno_Check = dao_wl.wl_jypNo(dto_wl); //작성자 회원의 번호 받기 
		
		//관리자 권한이거나 업무리스트 작성자이면 삭제실행
		if(jyp_no_ws==jyp_no || p_access==1 || jypno_Check == jyp_no){
			System.out.println("삭제권한있음 ");
			int result = dao_wl.wl_delete(dto_wl); //업무리스트 삭제
			if(result>0){ 
		 		out.println("<script>alert('업무리스트 삭제 성공'); "
		 				+ "location.href='"+request.getContextPath()+"/list.work?p_no="+p_no+"';</script>");
		 	}else {out.println("<script>alert('업무리스트 삭제 ERROR'); history.go(-1);</script>");}
		}//if
		else {
			 out.println("<script>alert('권한없음'); history.go(-1);</script>");
		}
		
		
	}

	
}
