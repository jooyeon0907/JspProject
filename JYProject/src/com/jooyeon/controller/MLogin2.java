package com.jooyeon.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_Member_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class MLogin2 implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		/**
		 @Action (jyp_workspace-  workspace 정보)
		-로그인된 회원이 소속된 워크스페이스가 있는지 확인하기
		select count(*) from jyp_workspace where jyp_no = 로그인되어있는 회원번호
		return값>0이면 소속된 워크스페이스가 있음. 
		 */
		HttpSession session = request.getSession(true);
		JYP_Member_DAO dao = new JYP_Member_DAO();
		PrintWriter out = response.getWriter();
		
		 //로그인한 회원이 소속된 워크스페이스가 있는지 검사 
		int result = dao.login_ws((int)session.getAttribute("jyp_no"));  
		
		if(result>0) { 
			//소속된 워크스페이스가 있으면 워크스페이스로 이동 (만약 여러개의 워크스페이스에 속해있다면 작은 번호로 이동) // 아니면 나중에 대표 워크스페이스 설정해두기 
			//-> 일단 임시로 마이페이지로 이동시키기 
			out.println("<script>alert('반갑습니다 "+session.getAttribute("jyp_email")+"님!');"+
					"location.href='"+request.getContextPath()+"/ws_list.ws?result=1';</script>"); 
		}else {
			//소속된 워크스페이스가 없다면 워크스페이스 생성하기 페이지로 이동 
			out.println("<script>location.href='"+request.getContextPath()+"/ws_list.ws?result=2';</script>");
		}
	/*	
		if(result>0) { 
			//소속된 워크스페이스가 있으면 워크스페이스로 이동 (만약 여러개의 워크스페이스에 속해있다면 작은 번호로 이동) // 아니면 나중에 대표 워크스페이스 설정해두기 
			//-> 일단 임시로 마이페이지로 이동시키기 
			out.println("<script>alert('반갑습니다 "+session.getAttribute("jyp_email")+"님!');"+
					"location.href='"+request.getContextPath()+"/ws_list.ws';</script>"); 
		}else {
			//소속된 워크스페이스가 없다면 워크스페이스 생성하기 페이지로 이동 
			out.println("<script>location.href='"+request.getContextPath()+"/ws_add_view.ws';</script>");
		}
		
	*/	

	}

}
