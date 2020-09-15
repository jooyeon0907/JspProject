package com.jooyeon.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_Member_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class MLogin implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);  //리턴값 : the HttpSession associatedwith this request
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		JYP_Member_DTO dto = new JYP_Member_DTO();
		JYP_Member_DAO dao = new JYP_Member_DAO();
		PrintWriter out = response.getWriter();
		//데이터 받아서 로그인(mid)
		String jyp_email = request.getParameter("jyp_email");
		String jyp_pass = request.getParameter("jyp_pass");
		//카카오 연동로그인으로 타고 온 경우 
		if(jyp_email==null){jyp_email = (String)request.getAttribute("jyp_email"); }
		if(jyp_pass==null) {jyp_pass = (String)request.getAttribute("jyp_pass"); }
		System.out.println("jyp_email /jyp_pass: " +jyp_email+"/"+jyp_pass);
		
		//로그인 할 정보 넣기 
		dto.setJyp_email(jyp_email);  
		dto.setJyp_pass(jyp_pass);
 		
		int result = dao.login(dto); //로그인   (해당아이디와 비밀번호가 같은 유저가 1명인지 체크)
	//	System.out.println("login : " + result);
		
		//쿠키
		String remember = request.getParameter("remember"); //checkbox (on // null)
		//

		//로그인 검사  
		if(result==1) {
//			out.println("<script>alert('반갑습니다 "+request.getParameter("jyp_email")+"님!');"+
//						"location.href='"+request.getContextPath()+"/mypage_view.members';</script>"); 
			//
			
			out.println("<script>location.href='"+request.getContextPath()+"/login2.members';</script>"); 
			//세션에 아이디,비번 저장 + 회원 번호도 저장
			session.setAttribute("jyp_email", jyp_email);
			session.setAttribute("jyp_pass", jyp_pass);
			session.setAttribute("jyp_no", dao.userinfo(dto).getJyp_no());//회원번호 뽑아오기
			session.setAttribute("jyp_ip", dao.userinfo(dto).getJyp_ip()); //ip
			session.setAttribute("jyp_profile", dao.userinfo(dto).getJyp_profile()); //프로필사진
			
			//아이디 기억하기 눌렀으면 쿠키남기기  (remember)
			if(remember!=null) { //on이라면 기억하기 눌렀다면
				Cookie cookie1 = new Cookie("remember","remember");
				Cookie cookie2 = new Cookie("jyp_email",request.getParameter("jyp_email"));
				cookie1.setMaxAge(60*60*24);
				cookie2.setMaxAge(60*60*24);
				response.addCookie(cookie1);
				response.addCookie(cookie2);
			}else { //기억하기 안눌렀다면 그 전에 남아 있던 쿠키 없애기
				String cookie = request.getHeader("Cookie");
				if(cookie!=null) {
				Cookie[] cookies = request.getCookies();
				for(int i=0; i<cookies.length; i++) {
					if(cookies[i].getName().equals("remember")||cookies[i].getName().equals("jyp_email")) {
						cookies[i].setMaxAge(0); response.addCookie(cookies[i]);
					}//if
				}//for
				} //if(cookie!=null) {
			}//else
			
		}else { out.println("<script>alert('로그인 실패!');"+ " history.go(-1);</script>"); }
		
	}

		
		
		
	

}
