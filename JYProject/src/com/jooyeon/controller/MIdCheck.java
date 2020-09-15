package com.jooyeon.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_Member_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class MIdCheck implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		///카카오 연동로그인- > 등록이 안된  아이디면 회원가입페이지로, 있으면 그 정보러 워크스페이스 홈으로 이동 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		String id = (String) request.getAttribute("id");				System.out.println("MIdCheck에서 id : " + id);	
		String nickname = (String) request.getAttribute("nickname"); System.out.println("MIdCheck에서 nickname : " + nickname);
		request.setAttribute("jyp_email", id);
		request.setAttribute("jyp_name", nickname);
		//id체크
		JYP_Member_DTO dto = new JYP_Member_DTO();
		JYP_Member_DAO dao = new JYP_Member_DAO();
		dto.setJyp_email(id); 
		int idCheck = dao.idCheck(dto);//없으면 0 있으면 1이상 
		System.out.println("MIdCheck에서 idCheck : " + idCheck);
		if(idCheck>0) { //있는 아이디면 워크스페이스리스트 페이지로 
			//정보 빼기
			JYP_Member_DTO dto_mem = new JYP_Member_DTO();
			JYP_Member_DAO dao_mem = new JYP_Member_DAO();
			dto_mem.setJyp_email(id);
			dto_mem = dao_mem.userinfo(dto_mem);
			//로그인할 정보 넘기기
			request.setAttribute("jyp_email", dto_mem.getJyp_email());
			request.setAttribute("jyp_pass", dto_mem.getJyp_pass());
			
			request.getRequestDispatcher("/login.members").forward(request, response);
		}else{ //연동되지 않는 아이디면 회원가입 페이지로 
			System.out.println("MIdCheck : 카카오 연동되어있지 않음");
			request.getRequestDispatcher("/join_view.members?loginType=1").forward(request, response);
		}
		
		
		
	}

}
