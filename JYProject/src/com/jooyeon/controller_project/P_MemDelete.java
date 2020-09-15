package com.jooyeon.controller_project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_PW_Member_DAO;
import com.jooyeon.dao.JYP_P_Member_DAO;
import com.jooyeon.dto.PW_Member_DTO;
import com.jooyeon.dto.P_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class P_MemDelete implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
	//	HttpSession session = request.getSession(true);
		
		//p_member
		P_Member_DTO dto_pmem = new P_Member_DTO();		
		JYP_P_Member_DAO dao_pmem = new JYP_P_Member_DAO();
		
		int p_no = Integer.parseInt(request.getParameter("p_no"));   System.out.println("P_MemDelete에서 p_no: " + p_no);
		int jyp_no = Integer.parseInt(request.getParameter("jyp_no"));   System.out.println("P_MemDelete에서 jyp_no: " + jyp_no);
		dto_pmem.setP_no(p_no);  dto_pmem.setJyp_no(jyp_no);
		
		//pw_member
		JYP_PW_Member_DAO dao_pwm = new JYP_PW_Member_DAO();
		PW_Member_DTO dto_pwm = new PW_Member_DTO();
		dto_pwm.setWs_no(p_no); dto_pwm.setJyp_no(jyp_no);
		
		///삭제하려는 유저가 관리자이고 해당 프로젝트에 관리자가 1명만 있다면 삭제 못함 
		int p_access = dao_pmem.p_access(dto_pmem);  System.out.println("P_MemDelete에서 p_access: " + p_access);
		int pAdmin_cnt = dao_pmem.pAdmin_cnt(dto_pmem);
		System.out.println("P_MemDelete에서 pAdmin_cnt: " + pAdmin_cnt);
		if(pAdmin_cnt<=1) {
		//	out.println("<script>alert('최소 1명이상의 프로젝트 관리자가 존재해야합니다.'); history.go(-1); </script>");
			out.println("ERROR1");
		}else {
			//멤버삭제 
			int pm_delete = dao_pmem.pro_pmDelete(dto_pmem);   System.out.println("P_MemDelete에서 pm_delete: "+pm_delete) ;
			int pwm_delete = dao_pwm.ws_workmemDel(dto_pwm);  System.out.println("P_MemDelete에서 pwm_delete: "+pwm_delete) ;
			if(pm_delete>0) {
//				out.println("<script>alert('프로젝트 탈퇴성공');"
//						+ "location.href='"+request.getContextPath()+"/pro_list.pro';</script>");
				out.println("OK");
			}else{ 
				out.println("ERROR2");
			//	out.println("<script>alert('탈퇴 ERROR'); history.go(-1); </script>");
			}
		}
		
		
		
		
		
		

	}

}
