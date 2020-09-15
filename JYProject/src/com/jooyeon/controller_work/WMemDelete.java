package com.jooyeon.controller_work;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jooyeon.dao.JYP_PW_Member_DAO;
import com.jooyeon.dto.PW_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WMemDelete implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		
		int work_no = Integer.parseInt(request.getParameter("work_no")); System.out.println("WMemDelete에서 work_no : " + work_no);
		int jyp_no =  Integer.parseInt(request.getParameter("jyp_no"));  System.out.println("WMemDelete에서 jyp_no : " + jyp_no);
		 
		PW_Member_DTO dto = new PW_Member_DTO();
		JYP_PW_Member_DAO dao = new JYP_PW_Member_DAO();
		//삭제하려는 업무에 배정된 인원 수 구하기 (1명만 있으면 인원 삭제 불가) 
		dto.setWork_no(work_no);
		int pw_memberCnt = dao.pw_memberCnt(dto); //업무에 배정된 인원수 
		System.out.println("WMemDelete에서  pw_memberCnt  : "  + pw_memberCnt);
		if(pw_memberCnt>1) { //인원수가 1명보다 많은 때 삭제됨
			//pw_member 
			dto.setJyp_no(jyp_no);
			int result = dao.work_memDel(dto); // 삭제하기
			if(result>0) { out.println("SUCCESS");}
			else{ out.println("ERROR1");}
		}else{ out.println("ERROR2");}
		
		
		
		
		
		
		
		
		
	}

}
