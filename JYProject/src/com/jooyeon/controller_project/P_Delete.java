package com.jooyeon.controller_project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_P_Member_DAO;
import com.jooyeon.dao.JYP_Project_DAO;
import com.jooyeon.dto.JYP_Project_DTO;
import com.jooyeon.dto.P_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class P_Delete implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);		
		
		 //권한체크 
		 JYP_P_Member_DAO dao_pMem = new JYP_P_Member_DAO();
		 P_Member_DTO dto_pMem = new P_Member_DTO();
		 
		 int p_no = Integer.parseInt(request.getParameter("p_no")); // parameter 넘겨받기
		 int jyp_no = (int)session.getAttribute("jyp_no"); // session 값 받기
		 dto_pMem.setP_no(p_no);
		 dto_pMem.setJyp_no(jyp_no);
		 
		 //프로젝트 권한 확인 -return값: 관리자 =1, 일반멤버  =2 
		 int p_access = dao_pMem.p_access(dto_pMem);  System.out.println("P_Delete에서 p_access: " + p_access);
		 
		 //관리자 권한이면 삭제실행
		 JYP_Project_DAO dao_pro = new JYP_Project_DAO();
		 JYP_Project_DTO dto_pro = new JYP_Project_DTO();
		 dto_pro.setP_no(p_no);
		 if(p_access==1){
			 System.out.println("삭제권한있음");
			 int result = dao_pro.pro_delete(dto_pro); //프로젝트 삭제 
			 	if(result>0){ 
			 		out.println("<script>alert('프로젝트 삭제 성공'); "
			 				+ "location.href='"+request.getContextPath()+"/pro_list.pro';</script>");
			 	}else {out.println("<script>alert('프로젝트 삭제 ERROR'); history.go(-1);</script>");}
			 
			 
		 }//if
		
		
		
		
	}

}
