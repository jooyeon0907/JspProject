package com.jooyeon.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jooyeon.dao.JYP_Member_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class MJoin implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		JYP_Member_DTO dto = new JYP_Member_DTO();
		JYP_Member_DAO dao = new JYP_Member_DAO();
		
		
		String jyp_email = request.getParameter("jyp_email");
		String jyp_email2 = request.getParameter("jyp_email2");
		jyp_email += "@"+jyp_email2;
		
		//카카오연동으로 회원가입일 경우 (숫자로만 받아져서 @형식이 아님)
		if(request.getParameter("jyp_email2") == null)
		{jyp_email =  request.getParameter("jyp_email"); }
		 
		System.out.println("MJoin에서 jyp_email : " + jyp_email);
		//1. 아이디 중복 검사   (뷰단에 jquery로 사용했음>
//		dto.setJyp_email(jyp_email);
//		int result = dao.idCheck(dto);
//		//존재하는 아이디가 있다면 1, 없다면 0 
//		if(result>0) {out.println("<script>alert('사용중인 이메일이 있습니다'); histroy.go(-1);</script>"); return; }
		
		//2. 회원가입
		//데이터 넣기 
		dto.setJyp_email(jyp_email);
		dto.setJyp_name(request.getParameter("jyp_name"));
		dto.setJyp_pass(request.getParameter("jyp_pass"));
		dto.setJyp_tel(request.getParameter("jyp_tel"));  System.out.println("MJoin에서 jyp_tel : " + request.getParameter("jyp_tel"));
		dto.setGender(request.getParameter("gender"));
		dto.setBirth(request.getParameter("birth"));
		dto.setJyp_ip(InetAddress.getLocalHost().getHostAddress());
		
		
		int result = dao.join(dto);
		if(result>0) { 
			out.println("<script> location.href='"
					+ request.getContextPath()+"/join_com.members?jyp_email="+jyp_email+"'; </script>"); 
		}
		else {out.println("<script>alert('회원가입 오류.'); history.go(-1); </script>");  }
		
		
	}

}
